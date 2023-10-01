package dev.baidu.client.moderation;

import dev.baidu.client.Experimental;
import dev.baidu.client.Model;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ModerationRequest {
    private final List<String> input;
    private final String model;

    private ModerationRequest(Builder builder) {
        this.input = builder.input;
        this.model = builder.model;
    }

    public List<String> input() {
        return this.input;
    }

    public String model() {
        return this.model;
    }

    public boolean equals(Object another) {
        if (this == another) {
            return true;
        } else {
            return another instanceof ModerationRequest
                    && this.equalTo((ModerationRequest)another);
        }
    }

    private boolean equalTo(ModerationRequest another) {
        return Objects.equals(this.input, another.input) && Objects.equals(this.model, another.model);
    }

    public int hashCode() {
        int h = 5381;
        h += (h << 5) + Objects.hashCode(this.input);
        h += (h << 5) + Objects.hashCode(this.model);
        return h;
    }

    public String toString() {
        return "ModerationRequest{input=" + this.input + ", model=" + this.model + "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<String> input;
        private String model;

        private Builder() {
        }

        public Builder input(List<String> input) {
            this.input = input;
            return this;
        }

        public Builder input(String input) {
            return this.input(Collections.singletonList(input));
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        @Experimental
        public Builder model(Model model) {
            return this.model(model.stringValue());
        }

        public ModerationRequest build() {
            return new ModerationRequest(this);
        }
    }
}
