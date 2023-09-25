package dev.baidu.client.completion;

import dev.baidu.client.Experimental;
import dev.baidu.client.Model;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class CompletionRequest {
    private final String prompt;
    private final Boolean stream;
    private final String userId;

    private CompletionRequest(Builder builder) {
        this.prompt = builder.prompt;
        this.stream = builder.stream;
        this.userId = builder.userId;
    }


    public String prompt() {
        return this.prompt;
    }



    public Boolean stream() {
        return this.stream;
    }




    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String prompt;
        private Boolean stream;
        private String userId;

        private Builder() {

        }

        @Experimental
        public Builder from(
                CompletionRequest request) {
            this.prompt(request.prompt);
            this.stream(request.stream);
            this.user(request.userId);
            return this;
        }



        public Builder prompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public Builder stream(Boolean stream) {
            this.stream = stream;
            return this;
        }

        public Builder user(String userId) {
            this.userId = userId;
            return this;
        }

        public CompletionRequest build() {
            return new CompletionRequest(this);
        }
    }
}
