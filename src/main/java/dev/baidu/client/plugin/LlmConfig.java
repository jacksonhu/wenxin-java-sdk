package dev.baidu.client.plugin;

import lombok.Builder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class LlmConfig {
    private final Double temperature;
    private final Double top_p;
    private final Double penalty_score;
    private final String user_id;
    @Builder
    private LlmConfig(Double temperature, Double top_p, Double penalty_score, String user_id) {
        this.temperature = temperature;
        this.top_p = top_p;
        this.penalty_score = penalty_score;
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LlmConfig llmConfig = (LlmConfig) o;

        return new EqualsBuilder().append(temperature, llmConfig.temperature)
                .append(top_p, llmConfig.top_p).append(penalty_score, llmConfig.penalty_score)
                .append(user_id, llmConfig.user_id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(temperature).append(top_p).append(penalty_score).append(user_id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "llm:{" +
                "temperature=" + temperature +
                ", top_p=" + top_p +
                ", penalty_score=" + penalty_score +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
