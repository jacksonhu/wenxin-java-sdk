package dev.baidu.client.completion;

import java.util.Objects;

public final class CompletionChoice {
    private final String text;
    private final Integer index;
    private final Logprobs logprobs;
    private final String finishReason;

    private CompletionChoice(Builder builder) {
        this.text = builder.text;
        this.index = builder.index;
        this.logprobs = builder.logprobs;
        this.finishReason = builder.finishReason;
    }

    public String text() {
        return this.text;
    }

    public Integer index() {
        return this.index;
    }

    public Logprobs logprobs() {
        return this.logprobs;
    }

    public String finishReason() {
        return this.finishReason;
    }

    public boolean equals(Object another) {
        if (this == another) {
            return true;
        } else {
            return another instanceof CompletionChoice
                    && this.equalTo((CompletionChoice)another);
        }
    }

    private boolean equalTo(CompletionChoice another) {
        return Objects.equals(this.text, another.text) && Objects.equals(this.index, another.index) && Objects.equals(this.logprobs, another.logprobs) && Objects.equals(this.finishReason, another.finishReason);
    }

    public int hashCode() {
        int h = 5381;
        h += (h << 5) + Objects.hashCode(this.text);
        h += (h << 5) + Objects.hashCode(this.index);
        h += (h << 5) + Objects.hashCode(this.logprobs);
        h += (h << 5) + Objects.hashCode(this.finishReason);
        return h;
    }

    public String toString() {
        return "CompletionChoice{text=" + this.text + ", index=" + this.index + ", logprobs=" + this.logprobs + ", finishReason=" + this.finishReason + "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String text;
        private Integer index;
        private Logprobs logprobs;
        private String finishReason;

        private Builder() {
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder index(Integer index) {
            this.index = index;
            return this;
        }

        public Builder logprobs(Logprobs logprobs) {
            this.logprobs = logprobs;
            return this;
        }

        public Builder finishReason(String finishReason) {
            this.finishReason = finishReason;
            return this;
        }

        public CompletionChoice build() {
            return new CompletionChoice(this);
        }
    }
}

