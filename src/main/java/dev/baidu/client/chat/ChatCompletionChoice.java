package dev.baidu.client.chat;

import java.util.Objects;

public final class ChatCompletionChoice {
    private final Integer index;
    private final Message message;
    private final Delta delta;
    private final String finishReason;

    private ChatCompletionChoice(Builder builder) {
        this.index = builder.index;
        this.message = builder.message;
        this.delta = builder.delta;
        this.finishReason = builder.finishReason;
    }

    public Integer index() {
        return this.index;
    }

    public Message message() {
        return this.message;
    }

    public Delta delta() {
        return this.delta;
    }

    public String finishReason() {
        return this.finishReason;
    }

    public boolean equals(Object another) {
        if (this == another) {
            return true;
        } else {
            return another instanceof ChatCompletionChoice
                    && this.equalTo((ChatCompletionChoice)another);
        }
    }

    private boolean equalTo(ChatCompletionChoice another) {
        return Objects.equals(this.index, another.index) && Objects.equals(this.message, another.message) && Objects.equals(this.delta, another.delta) && Objects.equals(this.finishReason, another.finishReason);
    }

    public int hashCode() {
        int h = 5381;
        h += (h << 5) + Objects.hashCode(this.index);
        h += (h << 5) + Objects.hashCode(this.message);
        h += (h << 5) + Objects.hashCode(this.delta);
        h += (h << 5) + Objects.hashCode(this.finishReason);
        return h;
    }

    public String toString() {
        return "ChatCompletionChoice{index=" + this.index + ", message=" + this.message + ", delta=" + this.delta + ", finishReason=" + this.finishReason + "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer index;
        private Message message;
        private Delta delta;
        private String finishReason;

        private Builder() {
        }

        public Builder index(Integer index) {
            this.index = index;
            return this;
        }

        public Builder message(Message message) {
            this.message = message;
            return this;
        }

        public Builder delta(Delta delta) {
            this.delta = delta;
            return this;
        }

        public Builder finishReason(String finishReason) {
            this.finishReason = finishReason;
            return this;
        }

        public ChatCompletionChoice build() {
            return new ChatCompletionChoice(this);
        }
    }
}

