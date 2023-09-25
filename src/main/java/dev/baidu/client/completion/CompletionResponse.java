package dev.baidu.client.completion;

import dev.baidu.client.Experimental;
import dev.baidu.client.Usage;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class CompletionResponse {

    private final String id;
    private final String object;
    private final String result;
    private final boolean isEnd;
    private final boolean isSafe;
    private final Integer created;
    private final String sentenceId;
    private final Usage usage;

    private CompletionResponse(Builder builder) {
        this.id = builder.id;
        this.created = builder.created;
        this.object = builder.object;
        this.result = builder.result;
        this.sentenceId = builder.sentenceId;
        this.isSafe = builder.isSafe;
        this.isEnd = builder.isEnd;
        this.usage = builder.usage;
    }

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public String getResult() {
        return result;
    }


    public Integer getCreated() {
        return created;
    }

    public Usage getUsage() {
        return usage;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public boolean isSafe() {
        return isSafe;
    }

    public String getSentenceId() {
        return sentenceId;
    }

    @Override
    public String toString() {
        return "CompletionResponse{" +
                "id='" + id + '\'' +
                ", object='" + object + '\'' +
                ", result='" + result + '\'' +
                ", isEnd=" + isEnd +
                ", isSafe=" + isSafe +
                ", created=" + created +
                ", sentenceId='" + sentenceId + '\'' +
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

        CompletionResponse that = (CompletionResponse) o;

        return new EqualsBuilder().append(isEnd, that.isEnd).append(isSafe, that.isSafe)
                .append(id, that.id).append(object, that.object).append(result, that.result)
                .append(created, that.created)
                .append(sentenceId, that.sentenceId).append(usage, that.usage).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(object).append(result).append(isEnd).append(isSafe)
                .append(created).append(sentenceId).append(usage).toHashCode();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String id;
        private String object;
        private String result;
        private boolean isEnd;
        private boolean isSafe;
        private Integer created;
        private String sentenceId;
        private Usage usage;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder created(Integer created) {
            this.created = created;
            return this;
        }

        public Builder object(String object) {
            this.object = object;
            return this;
        }

        public Builder result(String result) {
            this.result = result;
            return this;
        }

        public Builder isEnd(boolean isEnd) {
            this.isEnd = isEnd;
            return this;
        }

        public Builder isSafe(boolean isSafe) {
            this.isSafe = isSafe;
            return this;
        }

        public Builder usage(Usage usage) {
            this.usage = usage;
            return this;
        }

        public Builder sentenceId(String sentenceId) {
            this.sentenceId = sentenceId;
            return this;
        }

        public CompletionResponse build() {
            return new CompletionResponse(this);
        }
    }
}
