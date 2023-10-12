package dev.baidu.client;

import dev.baidu.client.chat.ChatCompletionRequest;
import dev.baidu.client.chat.ChatCompletionResponse;
import dev.baidu.client.chat.ChatTokenResponse;
import dev.baidu.client.completion.CompletionRequest;
import dev.baidu.client.completion.CompletionResponse;
import dev.baidu.client.embedding.EmbeddingRequest;
import dev.baidu.client.embedding.EmbeddingResponse;
import dev.baidu.client.plugin.PluginRequest;
import dev.baidu.client.plugin.PluginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaiduApi {
        @POST("rpc/2.0/ai_custom/v1/wenxinworkshop/plugin/{serviceName}/")
        @Headers({"Content-Type: application/json"})
        Call<PluginResponse> chatPlugin(@Path(value = "serviceName",encoded = false) String serviceName,@Body PluginRequest var1, @Query("access_token") String accessToken);
        @POST("rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions")
        @Headers({"Content-Type: application/json"})
        Call<ChatCompletionResponse> chatCompletions(@Body ChatCompletionRequest var1, @Query("access_token") String accessToken);
        @POST("rpc/2.0/ai_custom/v1/wenxinworkshop/completions/{serviceName}")
        @Headers({"Content-Type: application/json"})
        Call<CompletionResponse> completions(@Path(value = "serviceName",encoded = false) String serviceName,@Body CompletionRequest var1, @Query("access_token") String accessToken);
        @POST("rpc/2.0/ai_custom/v1/wenxinworkshop/embeddings/{modelName}")
        @Headers({"Content-Type: application/json"})
        Call<EmbeddingResponse> embeddings(@Path(value = "modelName",encoded = false) String modelName,@Body EmbeddingRequest var1,@Query("access_token") String accessToken);
        @GET("oauth/2.0/token")
        @Headers({"Content-Type: application/json"})
        Call<ChatTokenResponse> getToken(@Query("grant_type") String grantType,@Query("client_id") String clientId,@Query("client_secret") String clientSecret);

    }

