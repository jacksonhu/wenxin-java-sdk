package dev.baidu.client.chat;

import java.util.Objects;

public final class Delta {
    private final Role role;
    private final String content;
    private final FunctionCall functionCall;

    private Delta(Builder builder) {
        this.role = builder.role;
        this.content = builder.content;
        this.functionCall = builder.functionCall;
    }

    public Role role() {
        return this.role;
    }

    public String content() {
        return this.content;
    }

    public FunctionCall functionCall() {
        return this.functionCall;
    }

    public boolean equals(Object another) {
        if (this == another) {
            return true;
        } else {
            return another instanceof Delta && this.equalTo((Delta)another);
        }
    }

    private boolean equalTo(Delta another) {
        return Objects.equals(this.role, another.role) && Objects.equals(this.content, another.content) && Objects.equals(this.functionCall, another.functionCall);
    }

    public int hashCode() {
        int h = 5381;
        h += (h << 5) + Objects.hashCode(this.role);
        h += (h << 5) + Objects.hashCode(this.content);
        h += (h << 5) + Objects.hashCode(this.functionCall);
        return h;
    }

    public String toString() {
        return "Delta{role=" + this.role + ", content=" + this.content + ", functionCall=" + this.functionCall + "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Role role;
        private String content;
        private FunctionCall functionCall;

        private Builder() {
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder functionCall(FunctionCall functionCall) {
            this.functionCall = functionCall;
            return this;
        }

        public Delta build() {
            return new Delta(this);
        }
    }
}

