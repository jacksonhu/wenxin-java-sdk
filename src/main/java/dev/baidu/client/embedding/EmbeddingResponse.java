package dev.baidu.client.embedding;

import dev.baidu.client.Experimental;
import dev.baidu.client.Usage;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class EmbeddingResponse {
    private final String object;
    private final String id;
    private final Integer created;
    private final List<EmbeddingData> data;
    private final Usage usage;

    private EmbeddingResponse(Builder builder) {
        this.object = builder.object;
        this.data = builder.data;
        this.usage = builder.usage;
        this.id=builder.id;
        this.created=builder.created;
    }

    public String object() {
        return this.object;
    }

    public List<EmbeddingData> data() {
        return this.data;
    }

    public Usage usage() {
        return this.usage;
    }
    public String id() {
        return this.id;
    }
    public Integer created() {
        return this.created;
    }

    @Experimental
    public List<Float> embedding() {
        return ((EmbeddingData)this.data.get(0)).embedding();
    }

    @Override
    public String toString() {
        return "EmbeddingResponse{" +
                "object='" + object + '\'' +
                ", id='" + id + '\'' +
                ", created=" + created +
                ", data=" + data +
                ", usage=" + usage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmbeddingResponse that = (EmbeddingResponse) o;

        return new EqualsBuilder().append(object, that.object).append(id, that.id)
                .append(created, that.created).append(data, that.data).append(usage, that.usage).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(object).append(id).append(created).append(data).append(usage)
                .toHashCode();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private  String object;
        private  String id;
        private  Integer created;
        private  List<EmbeddingData> data;
        private  Usage usage;

        private Builder() {
        }

        public Builder object(String object) {
            this.object = object;
            return this;
        }

        public Builder data(List<EmbeddingData> data) {
            if (data == null) {
                return this;
            } else {
                this.data = Collections.unmodifiableList(data);
                return this;
            }
        }

        public Builder usage(Usage usage) {
            this.usage = usage;
            return this;
        }
        public Builder id(String id) {
            this.id = id;
            return this;
        }
        public Builder created(Integer created) {
            this.created = created;
            return this;
        }
        public EmbeddingResponse build() {
            return new EmbeddingResponse(this);
        }
    }
}

