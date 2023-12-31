package dev.baidu.client;

import dev.baidu.InternalWenXinHelper;
import dev.baidu.client.chat.ChatTokenResponse;
import dev.baidu.client.chat.ChatCompletionRequest;
import dev.baidu.client.chat.ChatCompletionResponse;
import dev.baidu.client.completion.CompletionRequest;
import dev.baidu.client.completion.CompletionResponse;
import dev.baidu.client.embedding.EmbeddingRequest;
import dev.baidu.client.embedding.EmbeddingResponse;
import dev.baidu.client.plugin.PluginRequest;
import dev.baidu.client.plugin.PluginResponse;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.List;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaiduClient {

    private static final Logger log = LoggerFactory.getLogger(BaiduClient.class);
    private final String baseUrl;
    private String token;
    private final OkHttpClient okHttpClient;
    private final BaiduApi baiduApi;
    private final String wenxinClientId;
    private final String wenxinClientSecret;
    private final boolean logStreamingResponses;

    public BaiduClient(String apiKey, String secretKey) {
        this(builder().wenXinApiKey(apiKey).wenXinSecretKey(secretKey));
    }

    private BaiduClient(Builder serviceBuilder) {
        this.baseUrl = serviceBuilder.baseUrl;
        OkHttpClient.Builder okHttpClientBuilder = (new OkHttpClient.Builder()).callTimeout(serviceBuilder.callTimeout)
                .connectTimeout(serviceBuilder.connectTimeout).readTimeout(serviceBuilder.readTimeout)
                .writeTimeout(serviceBuilder.writeTimeout);
        if (serviceBuilder.wenXinApiKey == null) {
            throw new IllegalArgumentException("wenXinApiKey must be defined");
        } else if (serviceBuilder.wenXinSecretKey == null) {
            throw new IllegalArgumentException("wenXinSecretKey must be defined");
        } else {
            if (serviceBuilder.wenXinApiKey != null) {
                okHttpClientBuilder.addInterceptor(new AuthorizationHeaderInjector(serviceBuilder.wenXinApiKey));
            }

            if (serviceBuilder.proxy != null) {
                okHttpClientBuilder.proxy(serviceBuilder.proxy);
            }

            if (serviceBuilder.logRequests) {
                okHttpClientBuilder.addInterceptor(new RequestLoggingInterceptor());
            }

            if (serviceBuilder.logResponses) {
                okHttpClientBuilder.addInterceptor(new ResponseLoggingInterceptor());
            }

            this.logStreamingResponses = serviceBuilder.logStreamingResponses;
            this.wenxinClientId = serviceBuilder.wenXinApiKey;
            this.wenxinClientSecret = serviceBuilder.wenXinSecretKey;
            this.okHttpClient = okHttpClientBuilder.build();
            Retrofit retrofit = (new Retrofit.Builder()).baseUrl(serviceBuilder.baseUrl).client(this.okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(Json.GSON)).build();
            this.baiduApi = retrofit.create(BaiduApi.class);
        }
    }

    public void shutdown() {
        this.okHttpClient.dispatcher().executorService().shutdown();
        this.okHttpClient.connectionPool().evictAll();
        Cache cache = this.okHttpClient.cache();
        if (cache != null) {
            try {
                cache.close();
            } catch (IOException var3) {
                log.error("Failed to close cache", var3);
            }
        }

    }

    public static Builder builder() {
        return new Builder();
    }

    public SyncOrAsyncOrStreaming<PluginResponse> chatPlugin(PluginRequest request, String serviceName) {
        refreshToken();
        return new RequestExecutor(this.baiduApi.chatPlugin(serviceName, request, this.token), (r) -> {
            return r;
        }, () -> {
            return request.toBuilder().stream(true).build();
        }, PluginResponse.class, (r) -> {
            return r;
        });
    }

    public SyncOrAsyncOrStreaming<ChatCompletionResponse> chatCompletion(ChatCompletionRequest request) {
        refreshToken();

        return new RequestExecutor(this.baiduApi.chatCompletions(request, this.token), (r) -> {
            return r;
        }, this.okHttpClient, this.formatUrl("rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions_pro?access_token="+this.token), () -> {
            return ChatCompletionRequest.builder().from(request).stream(true).build();
        }, ChatCompletionResponse.class, (r) -> {
            return r;
        }, this.logStreamingResponses);

    }

    @Experimental
    public SyncOrAsyncOrStreaming<ChatCompletionResponse> chatCompletion(String prompt, boolean stream) {
        refreshToken();
        ChatCompletionRequest request = ChatCompletionRequest.builder().adduserMessage(prompt).stream(stream).build();

        return new RequestExecutor(this.baiduApi.chatCompletions(request, this.token), (r) -> {
            return r;
        }, this.okHttpClient, this.formatUrl("rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions_pro?access_token="+this.token), () -> {
            return ChatCompletionRequest.builder().from(request).stream(true).build();
        }, ChatCompletionResponse.class, (r) -> {
            return r;
        }, this.logStreamingResponses);
    }

    public SyncOrAsyncOrStreaming<CompletionResponse> completion(CompletionRequest request, boolean stream,
            String serviceName) {
        refreshToken();
        CompletionRequest syncRequest = CompletionRequest.builder().from(request).stream(stream).build();
        return new RequestExecutor(this.baiduApi.completions(serviceName, syncRequest, this.token), (r) -> {
            return r;
        }, () -> {
            return CompletionRequest.builder().from(request).stream(true).build();
        }, CompletionResponse.class, (r) -> {
            return r;
        });
    }

    @Experimental
    public SyncOrAsyncOrStreaming<String> completion(String prompt, boolean stream, String serviceName) {
        refreshToken();
        CompletionRequest syncRequest = CompletionRequest.builder().stream(stream).prompt(prompt).build();
        return new RequestExecutor<>(this.baiduApi.completions(serviceName, syncRequest, this.token),
                CompletionResponse::getResult,
                () -> {
                    return CompletionRequest.builder().from(syncRequest).stream(true).build();
                }, CompletionResponse.class, CompletionResponse::getResult);
    }

    public SyncOrAsync<EmbeddingResponse> embedding(EmbeddingRequest request, String modelName) {
        refreshToken();
        return new RequestExecutor(this.baiduApi.embeddings(modelName, request, this.token), (r) -> {
            return r;
        });
    }

    @Experimental
    public SyncOrAsync<List<Float>> embedding(String input, String modelName) {
        refreshToken();
        EmbeddingRequest request = EmbeddingRequest.builder().input(new String[]{input}).build();
        return new RequestExecutor<>(this.baiduApi.embeddings(modelName, request, this.token),
                EmbeddingResponse::embedding);
    }

    private String refreshToken() {
        RequestExecutor<String, ChatTokenResponse, String> executor = new RequestExecutor<>(
                this.baiduApi.getToken(InternalWenXinHelper.GRANT_TYPE, this.wenxinClientId,
                        this.wenxinClientSecret), ChatTokenResponse::getAccess_token);
        String response = executor.execute();
        log.debug("response token is :{}", response);
        this.token = response;
        return this.token;

    }

    private String formatUrl(String endpoint) {
        return this.baseUrl + endpoint;
    }


    public static class Builder {

        private String baseUrl;
        private String wenXinApiKey;
        private String wenXinSecretKey;
        private Duration callTimeout;
        private Duration connectTimeout;
        private Duration readTimeout;
        private Duration writeTimeout;
        private Proxy proxy;
        private boolean logRequests;
        private boolean logResponses;
        private boolean logStreamingResponses;

        private Builder() {
            this.baseUrl = "https://aip.baidubce.com/";
            this.callTimeout = Duration.ofSeconds(60L);
            this.connectTimeout = Duration.ofSeconds(60L);
            this.readTimeout = Duration.ofSeconds(60L);
            this.writeTimeout = Duration.ofSeconds(60L);
        }

        public Builder baseUrl(String baseUrl) {
            if (baseUrl != null && !baseUrl.trim().isEmpty()) {
                this.baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
                return this;
            } else {
                throw new IllegalArgumentException("baseUrl cannot be null or empty");
            }
        }


        public Builder wenXinApiKey(String wenXinApiKey) {
            if (wenXinApiKey != null && !wenXinApiKey.trim().isEmpty()) {
                this.wenXinApiKey = wenXinApiKey;
                return this;
            } else {
                throw new IllegalArgumentException("wenXinApiKey cannot be null or empty. ");
            }
        }

        public Builder wenXinSecretKey(String wenXinSecretKey) {
            if (wenXinSecretKey != null && !wenXinSecretKey.trim().isEmpty()) {
                this.wenXinSecretKey = wenXinSecretKey;
                return this;
            } else {
                throw new IllegalArgumentException("wenXinSecretKey cannot be null or empty. ");
            }
        }


        public Builder callTimeout(Duration callTimeout) {
            if (callTimeout == null) {
                throw new IllegalArgumentException("callTimeout cannot be null");
            } else {
                this.callTimeout = callTimeout;
                return this;
            }
        }

        public Builder connectTimeout(Duration connectTimeout) {
            if (connectTimeout == null) {
                throw new IllegalArgumentException("connectTimeout cannot be null");
            } else {
                this.connectTimeout = connectTimeout;
                return this;
            }
        }

        public Builder readTimeout(Duration readTimeout) {
            if (readTimeout == null) {
                throw new IllegalArgumentException("readTimeout cannot be null");
            } else {
                this.readTimeout = readTimeout;
                return this;
            }
        }

        public Builder writeTimeout(Duration writeTimeout) {
            if (writeTimeout == null) {
                throw new IllegalArgumentException("writeTimeout cannot be null");
            } else {
                this.writeTimeout = writeTimeout;
                return this;
            }
        }

        public Builder proxy(Proxy.Type type, String ip, int port) {
            this.proxy = new Proxy(type, new InetSocketAddress(ip, port));
            return this;
        }

        public Builder proxy(Proxy proxy) {
            this.proxy = proxy;
            return this;
        }

        public Builder logRequests() {
            return this.logRequests(true);
        }

        public Builder logRequests(Boolean logRequests) {
            if (logRequests == null) {
                logRequests = false;
            }

            this.logRequests = logRequests;
            return this;
        }

        public Builder logResponses() {
            return this.logResponses(true);
        }

        public Builder logResponses(Boolean logResponses) {
            if (logResponses == null) {
                logResponses = false;
            }

            this.logResponses = logResponses;
            return this;
        }

        public Builder logStreamingResponses() {
            return this.logStreamingResponses(true);
        }

        public Builder logStreamingResponses(Boolean logStreamingResponses) {
            if (logStreamingResponses == null) {
                logStreamingResponses = false;
            }

            this.logStreamingResponses = logStreamingResponses;
            return this;
        }

        public BaiduClient build() {
            return new BaiduClient(this);
        }
    }
}
