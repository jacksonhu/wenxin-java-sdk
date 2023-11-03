package dev.baidu;

import static dev.langchain4j.data.message.AiMessage.aiMessage;
import static dev.baidu.WenXinModelName.GPT_3_5_TURBO;
import static dev.baidu.WenXinModelName.GPT_4;
import static dev.baidu.client.chat.Role.ASSISTANT;
import static dev.baidu.client.chat.Role.FUNCTION;
import static dev.baidu.client.chat.Role.SYSTEM;
import static dev.baidu.client.chat.Role.USER;
import static java.time.Duration.ofSeconds;
import static java.util.stream.Collectors.toList;

import dev.baidu.client.chat.Responses;
import dev.baidu.client.exception.LllmEmptyResponseException;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.agent.tool.ToolParameters;
import dev.langchain4j.agent.tool.ToolResponses;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.ToolExecutionResultMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.baidu.client.chat.ChatCompletionResponse;
import dev.baidu.client.chat.Function;
import dev.baidu.client.chat.FunctionCall;
import dev.baidu.client.chat.Message;
import dev.baidu.client.chat.Parameters;
import dev.baidu.client.chat.Role;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InternalWenXinHelper {
    private static final Logger LOG = LoggerFactory.getLogger(InternalWenXinHelper.class);
    public static final String WENXIN_URL = "https://aip.baidubce.com/";

    public static final String GRANT_TYPE = "client_credentials";
    public static Duration defaultTimeoutFor() {
        return ofSeconds(7);
    }

    public static List<Message> toWenXinMessages(List<ChatMessage> messages) {

        return messages.stream()
                .map(InternalWenXinHelper::toWenXinMessage)
                .collect(toList());
    }

    public static Message toWenXinMessage(ChatMessage message) {

        return Message.builder()
                .role(roleFrom(message))
                .name(nameFrom(message))
                .content(message.text())
                .functionCall(functionCallFrom(message))
                .build();
    }

    private static String nameFrom(ChatMessage message) {
        if (message instanceof UserMessage) {
            return ((UserMessage) message).name();
        }

        if (message instanceof ToolExecutionResultMessage) {
            return ((ToolExecutionResultMessage) message).toolName();
        }

        return null;
    }

    private static FunctionCall functionCallFrom(ChatMessage message) {
        if (message instanceof AiMessage) {
            AiMessage aiMessage = (AiMessage) message;
            if (aiMessage.toolExecutionRequest() != null) {
                return FunctionCall.builder()
                        .name(aiMessage.toolExecutionRequest().name())
                        .arguments(aiMessage.toolExecutionRequest().arguments())
                        .build();
            }
        }

        return null;
    }

    public static Role roleFrom(ChatMessage message) {
        if (message instanceof AiMessage) {
            return ASSISTANT;
        } else if (message instanceof ToolExecutionResultMessage) {
            return FUNCTION;
        } else if (message instanceof SystemMessage) {
            return SYSTEM;
        } else {
            return USER;
        }
    }

    public static List<Function> toFunctions(Collection<ToolSpecification> toolSpecifications) {
        return toolSpecifications.stream()
                .map(InternalWenXinHelper::toFunction)
                .collect(toList());
    }

    private static Function toFunction(ToolSpecification toolSpecification) {
        return Function.builder()
                .name(toolSpecification.name())
                .description(toolSpecification.description())
                .parameters(toWenXinParameters(toolSpecification.parameters()))
                .responses(toWenXinResponses(toolSpecification.responses()))
                .build();
    }

    private static Parameters toWenXinParameters(ToolParameters toolParameters) {
        if (toolParameters == null) {
            return Parameters.builder().build();
        }
        return Parameters.builder()
                .properties(toolParameters.properties())
                .required(toolParameters.required())
                .build();
    }
    private static Responses toWenXinResponses(ToolResponses toolResponses) {
        if (toolResponses == null) {
            return Responses.builder().build();
        }
        return Responses.builder()
                .properties(toolResponses.properties())
                .build();
    }

    public static AiMessage aiMessageFrom(ChatCompletionResponse response) {

        if (StringUtils.isNotEmpty(response.getResult())) {
            return aiMessage(response.getResult());
        } else if(response.getFunction_call()==null){
            LOG.error("WenXin model response is empty,request Wenxin model error:{}",response.toString());
            return AiMessage.aiMessage("我暂时无法回答");
        }
        else {
            FunctionCall functionCall = response.getFunction_call();

            ToolExecutionRequest toolExecutionRequest = ToolExecutionRequest.builder()
                    .name(functionCall.name())
                    .arguments(functionCall.arguments())
                    .build();

            return aiMessage(toolExecutionRequest);
        }
    }
}
