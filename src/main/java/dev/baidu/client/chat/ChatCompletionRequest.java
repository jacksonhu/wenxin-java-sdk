package dev.baidu.client.chat;

import dev.baidu.client.Experimental;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class ChatCompletionRequest {

    private final List<Message> messages;
    private final Double temperature;
    private final Double top_p;
    private final Boolean stream;
    private final Double penalty_score;
    private final String user_id;
    private final List<Function> functions;

    private ChatCompletionRequest(Builder builder) {
        this.messages = builder.messages;
        this.temperature = builder.temperature;
        this.top_p = builder.top_p;
        this.stream = builder.stream;
        this.penalty_score = builder.penalty_score;
        this.user_id = builder.user_id;
        this.functions = builder.functions;
    }


    public List<Message> messages() {
        return this.messages;
    }

    public Double temperature() {
        return this.temperature;
    }


    public Double top_p() {
        return this.top_p;
    }

    public Boolean stream() {
        return this.stream;
    }


    public Double penalty_score() {
        return this.penalty_score;
    }



    public String user_id() {
        return this.user_id;
    }

    public List<Function> functions() {
        return this.functions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChatCompletionRequest that = (ChatCompletionRequest) o;

        return new EqualsBuilder().append(messages, that.messages)
                .append(temperature, that.temperature).append(top_p, that.top_p).append(stream, that.stream)
                .append(penalty_score, that.penalty_score)
                .append(user_id, that.user_id).append(functions, that.functions).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(messages).append(temperature).append(top_p).append(stream)
                .append(penalty_score).append(user_id).append(functions).toHashCode();
    }

    @Override
    public String toString() {
        return "ChatCompletionRequest{" +
                ", messages=" + messages +
                ", temperature=" + temperature +
                ", top_p=" + top_p +
                ", stream=" + stream +
                ", penalty_score=" + penalty_score +
                ", user_id='" + user_id + '\'' +
                ", functions=" + functions +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private  List<Message> messages;
        private  Double temperature;
        private  Double top_p;
        private  Boolean stream;
        private  Double penalty_score;
        private  String user_id;
        private  List<Function> functions;

        private Builder() {
        }

        public Builder from(
                ChatCompletionRequest instance) {
            this.messages(instance.messages);
            this.temperature(instance.temperature);
            this.top_p(instance.top_p);
            this.stream(instance.stream);
            this.penalty_score(instance.penalty_score);
            this.user_id(instance.user_id);
            this.functions(instance.functions);
            return this;
        }


        public Builder messages(List<Message> messages) {
            if (messages == null) {
                return this;
            } else {
                this.messages = Collections.unmodifiableList(messages);
                return this;
            }
        }

        @Experimental
        public Builder messages(Message... messages) {
            return this.messages(Arrays.asList(messages));
        }

        @Experimental
        public Builder addSystemMessage(String systemMessage) {
            if (this.messages == null) {
                this.messages = new ArrayList();
            }

            this.messages.add(Message.systemMessage(systemMessage));
            return this;
        }

        @Experimental
        public Builder adduserMessage(String userMessage) {
            if (this.messages == null) {
                this.messages = new ArrayList();
            }

            this.messages.add(Message.userMessage(userMessage));
            return this;
        }

        @Experimental
        public Builder addAssistantMessage(String assistantMessage) {
            if (this.messages == null) {
                this.messages = new ArrayList();
            }

            this.messages.add(Message.assistantMessage(assistantMessage));
            return this;
        }

        @Experimental
        public Builder addFunctionMessage(String name, String content) {
            if (this.messages == null) {
                this.messages = new ArrayList();
            }

            this.messages.add(Message.functionMessage(name, content));
            return this;
        }

        public Builder temperature(Double temperature) {
            this.temperature = temperature;
            return this;
        }


        public Builder top_p(Double top_p) {
            this.top_p = top_p;
            return this;
        }

        public Builder stream(Boolean stream) {
            this.stream = stream;
            return this;
        }




        public Builder penalty_score(Double penalty_score) {
            this.penalty_score = penalty_score;
            return this;
        }




        public Builder user_id(String user_id) {
            this.user_id = user_id;
            return this;
        }

        public Builder functions(List<Function> functions) {
            if (functions == null) {
                return this;
            } else {
                this.functions = Collections.unmodifiableList(functions);
                return this;
            }
        }

        @Experimental
        public Builder functions(Function... functions) {
            return this.functions(Arrays.asList(functions));
        }

        @Experimental
        public Builder addFunction(Function function) {
            if (this.functions == null) {
                this.functions = new ArrayList();
            }

            this.functions.add(function);
            return this;
        }

        public ChatCompletionRequest build() {
            return new ChatCompletionRequest(this);
        }
    }
}
