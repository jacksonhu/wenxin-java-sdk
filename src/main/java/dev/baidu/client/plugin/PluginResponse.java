package dev.baidu.client.plugin;

import dev.baidu.client.Usage;
import dev.baidu.client.chat.FunctionCall;
import lombok.Builder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class PluginResponse {
    private final String log_id;
    private final String id;
    private final Integer errorCode;
    private final String errorMsg;
    private final String object;
    private final Integer created;
    private final Integer sentence_id;
    private final Boolean is_end;
    private final Boolean is_truncated;
    private final String result;
    private final Boolean need_clear_history;
    private final Integer ban_round;
    private final Usage usage;
    private final String meta_info;

    @Builder
    private PluginResponse(String log_id, String id, Integer errorCode, String errorMsg, String object, Integer created,
            Integer sentence_id, Boolean is_end, Boolean is_truncated, String result, Boolean need_clear_history,
            Integer ban_round, Usage usage, String meta_info) {
        this.log_id = log_id;
        this.id = id;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.object = object;
        this.created = created;
        this.sentence_id = sentence_id;
        this.is_end = is_end;
        this.is_truncated = is_truncated;
        this.result = result;
        this.need_clear_history = need_clear_history;
        this.ban_round = ban_round;
        this.usage = usage;
        this.meta_info = meta_info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PluginResponse that = (PluginResponse) o;

        return new EqualsBuilder().append(id, that.id).append(errorCode, that.errorCode)
                .append(errorMsg, that.errorMsg).append(object, that.object).append(created, that.created)
                .append(sentence_id, that.sentence_id).append(is_end, that.is_end)
                .append(is_truncated, that.is_truncated)
                .append(result, that.result).append(need_clear_history, that.need_clear_history)
                .append(ban_round, that.ban_round).append(usage, that.usage).append(meta_info, that.meta_info)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(errorCode).append(errorMsg).append(object).append(created)
                .append(sentence_id).append(is_end).append(is_truncated).append(result).append(need_clear_history)
                .append(ban_round).append(usage).append(meta_info).toHashCode();
    }

    @Override
    public String toString() {
        return "PluginResponse{" +
                "id='" + id + '\'' +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", object='" + object + '\'' +
                ", created=" + created +
                ", sentence_id=" + sentence_id +
                ", is_end=" + is_end +
                ", is_truncated=" + is_truncated +
                ", result='" + result + '\'' +
                ", need_clear_history=" + need_clear_history +
                ", ban_round=" + ban_round +
                ", usage=" + usage +
                ", meta_info=" + meta_info +
                '}';
    }

    public String getLog_id() {
        return log_id;
    }

    public String getMeta_info() {
        return meta_info;
    }

    public String getId() {
        return id;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getObject() {
        return object;
    }

    public Integer getCreated() {
        return created;
    }

    public Integer getSentence_id() {
        return sentence_id;
    }

    public Boolean getIs_end() {
        return is_end;
    }

    public Boolean getIs_truncated() {
        return is_truncated;
    }

    public String getResult() {
        return result;
    }

    public Boolean getNeed_clear_history() {
        return need_clear_history;
    }

    public Integer getBan_round() {
        return ban_round;
    }

    public Usage getUsage() {
        return usage;
    }


}

