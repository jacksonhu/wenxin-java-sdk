package dev.baidu.client.completion;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Logprobs {

    private final List<String> tokens;
    private final List<Double> tokenLogprobs;
    private final List<Map<String, Double>> topLogprobs;
    private final List<Integer> textOffset;

    private Logprobs(Builder builder) {
        this.tokens = builder.tokens;
        this.tokenLogprobs = builder.tokenLogprobs;
        this.topLogprobs = builder.topLogprobs;
        this.textOffset = builder.textOffset;
    }

    public List<String> tokens() {
        return this.tokens;
    }

    public List<Double> tokenLogprobs() {
        return this.tokenLogprobs;
    }

    public List<Map<String, Double>> topLogprobs() {
        return this.topLogprobs;
    }

    public List<Integer> textOffset() {
        return this.textOffset;
    }

    public boolean equals(Object another) {
        if (this == another) {
            return true;
        } else {
            return another instanceof Logprobs
                    && this.equalTo((Logprobs) another);
        }
    }

    private boolean equalTo(Logprobs another) {
        return Objects.equals(this.tokens, another.tokens) && Objects.equals(this.tokenLogprobs, another.tokenLogprobs)
                && Objects.equals(this.topLogprobs, another.topLogprobs) && Objects.equals(this.textOffset,
                another.textOffset);
    }

    public int hashCode() {
        int h = 5381;
        h += (h << 5) + Objects.hashCode(this.tokens);
        h += (h << 5) + Objects.hashCode(this.tokenLogprobs);
        h += (h << 5) + Objects.hashCode(this.topLogprobs);
        h += (h << 5) + Objects.hashCode(this.textOffset);
        return h;
    }

    public String toString() {
        return "Logprobs{tokens=" + this.tokens + ", tokenLogprobs=" + this.tokenLogprobs + ", topLogprobs="
                + this.topLogprobs + ", textOffset=" + this.textOffset + "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private List<String> tokens;
        private List<Double> tokenLogprobs;
        private List<Map<String, Double>> topLogprobs;
        private List<Integer> textOffset;

        private Builder() {
        }

        public Builder tokens(List<String> tokens) {
            if (tokens == null) {
                return this;
            } else {
                this.tokens = Collections.unmodifiableList(tokens);
                return this;
            }
        }

        public Builder tokenLogprobs(List<Double> tokenLogprobs) {
            if (tokenLogprobs == null) {
                return this;
            } else {
                this.tokenLogprobs = Collections.unmodifiableList(tokenLogprobs);
                return this;
            }
        }

        public Builder topLogprobs(List<Map<String, Double>> topLogprobs) {
            if (topLogprobs == null) {
                return this;
            } else {
                List<Map<String, Double>> topLogprobsCopy = new ArrayList();
                Iterator var3 = topLogprobs.iterator();

                while (var3.hasNext()) {
                    Map<String, Double> map = (Map) var3.next();
                    topLogprobsCopy.add(Collections.unmodifiableMap(map));
                }

                this.topLogprobs = Collections.unmodifiableList(topLogprobsCopy);
                return this;
            }
        }
    }
}
