package dev.baidu.client;

import java.io.IOException;
import retrofit2.Response;

public class Utils {
    Utils() {
    }

    static RuntimeException toException(Response<?> response) throws IOException {
        return new BaiduHttpException(response.code(), response.errorBody().string());
    }

    static RuntimeException toException(okhttp3.Response response) throws IOException {
        return new BaiduHttpException(response.code(), response.body().string());
    }
}