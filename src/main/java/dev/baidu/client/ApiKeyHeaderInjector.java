package dev.baidu.client;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyHeaderInjector implements Interceptor {
    private final String apiKey;

    ApiKeyHeaderInjector(String apiKey) {
        this.apiKey = apiKey;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder().addHeader("api-key", this.apiKey).build();
        return chain.proceed(request);
    }
}