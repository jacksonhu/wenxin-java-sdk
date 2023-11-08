package dev.baidu;

import static dev.baidu.client.chat.Role.ASSISTANT;
import static dev.baidu.client.chat.Role.FUNCTION;
import static dev.baidu.client.chat.Role.SYSTEM;
import static dev.baidu.client.chat.Role.USER;
import static java.time.Duration.ofSeconds;
import static java.util.stream.Collectors.toList;

import dev.baidu.client.chat.Responses;
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
    public static final String WENXIN_URL = "https://aip.baidubce.com/";

    public static final String GRANT_TYPE = "client_credentials";
    public static Duration defaultTimeoutFor() {
        return ofSeconds(7);
    }


}
