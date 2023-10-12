package dev.baidu.client.plugin;

import com.google.gson.JsonObject;
import dev.baidu.client.Experimental;
import dev.baidu.client.chat.Function;
import dev.baidu.client.chat.Message;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.Builder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class PluginRequest {

    private final String query;
    private final List<String> plugins;
    private final LlmConfig llm;
    private final JsonObject input_variables;
    private final Boolean stream;
    private final List<Message> history;
    private final Boolean verbose;
    private final String fileurl;
    @Builder(toBuilder=true)
    private PluginRequest(String query, List<String> plugins, LlmConfig llm, JsonObject input_variables, Boolean stream,
            List<Message> history, Boolean verbose, String fileurl) {
        this.query = query;
        this.plugins = plugins;
        this.llm = llm;
        this.input_variables = input_variables;
        this.stream = stream;
        this.history = history;
        this.verbose = verbose;
        this.fileurl = fileurl;
    }

    public String getQuery() {
        return query;
    }

    public List<String> getPlugins() {
        return plugins;
    }

    public LlmConfig getLlm() {
        return llm;
    }

    public JsonObject getInput_variables() {
        return input_variables;
    }

    public Boolean getStream() {
        return stream;
    }

    public List<Message> getHistory() {
        return history;
    }

    public Boolean getVerbose() {
        return verbose;
    }

    public String getFileurl() {
        return fileurl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PluginRequest that = (PluginRequest) o;

        return new EqualsBuilder().append(query, that.query).append(plugins, that.plugins)
                .append(llm, that.llm).append(input_variables, that.input_variables).append(stream, that.stream)
                .append(history, that.history).append(verbose, that.verbose).append(fileurl, that.fileurl).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(query).append(plugins).append(llm).append(input_variables)
                .append(stream)
                .append(history).append(verbose).append(fileurl).toHashCode();
    }

    @Override
    public String toString() {
        return "PluginRequest{" +
                "query='" + query + '\'' +
                ", plugins=" + plugins +
                ", llm=" + llm +
                ", input_variables=" + input_variables +
                ", stream=" + stream +
                ", history=" + history +
                ", verbose=" + verbose +
                ", fileurl='" + fileurl + '\'' +
                '}';
    }

}
