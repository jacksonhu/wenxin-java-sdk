package dev.baidu.client.moderation;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ModerationResponse {
    private final String id;
    private final String model;
    private final List<ModerationResult> results;

    private ModerationResponse(Builder builder) {
        this.id = builder.id;
        this.model = builder.model;
        this.results = builder.results;
    }

    public String id() {
        return this.id;
    }

    public String model() {
        return this.model;
    }

    public List<ModerationResult> results() {
        return this.results;
    }

    public boolean equals(Object another) {
        if (this == another) {
            return true;
        } else {
            return another instanceof ModerationResponse
                    && this.equalTo((ModerationResponse)another);
        }
    }

    private boolean equalTo(ModerationResponse another) {
        return Objects.equals(this.id, another.id) && Objects.equals(this.model, another.model) && Objects.equals(this.results, another.results);
    }

    public int hashCode() {
        int h = 5381;
        h += (h << 5) + Objects.hashCode(this.id);
        h += (h << 5) + Objects.hashCode(this.model);
        h += (h << 5) + Objects.hashCode(this.results);
        return h;
    }

    public String toString() {
        return "EmbeddingResponse{id=" + this.id + ", model=" + this.model + ", results=" + this.results + "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        public String id;
        public String model;
        public List<ModerationResult> results;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder results(List<ModerationResult> results) {
            if (results == null) {
                return this;
            } else {
                this.results = Collections.unmodifiableList(results);
                return this;
            }
        }

        public ModerationResponse build() {
            return new ModerationResponse(this);
        }
    }
}
