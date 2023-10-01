package dev.baidu.client;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;

public class RequestExecutor<Request, Response, ResponseContent> implements SyncOrAsyncOrStreaming<ResponseContent> {

    private static final Logger LOG = LoggerFactory.getLogger(RequestExecutor.class);
    private final Call<Response> call;
    private final Function<Response, ResponseContent> responseContentExtractor;
    private final OkHttpClient okHttpClient;
    private final String endpointUrl;
    private final Supplier<Request> requestWithStreamSupplier;
    private final Class<Response> responseClass;
    private final Function<Response, ResponseContent> streamEventContentExtractor;
    private final boolean logStreamingResponses;

    RequestExecutor(Call<Response> call, Function<Response, ResponseContent> responseContentExtractor,
            OkHttpClient okHttpClient, String endpointUrl, Supplier<Request> requestWithStreamSupplier,
            Class<Response> responseClass, Function<Response, ResponseContent> streamEventContentExtractor,
            boolean logStreamingResponses) {
        this.call = call;
        this.responseContentExtractor = responseContentExtractor;
        this.okHttpClient = okHttpClient;
        this.endpointUrl = endpointUrl;
        this.requestWithStreamSupplier = requestWithStreamSupplier;
        this.responseClass = responseClass;
        this.streamEventContentExtractor = streamEventContentExtractor;
        this.logStreamingResponses = logStreamingResponses;
    }

    RequestExecutor(Call<Response> call, Function<Response, ResponseContent> responseContentExtractor,
            Supplier<Request> requestWithStreamSupplier,
            Class<Response> responseClass, Function<Response, ResponseContent> streamEventContentExtractor
    ) {
        this.call = call;
        this.responseContentExtractor = responseContentExtractor;
        this.requestWithStreamSupplier = requestWithStreamSupplier;
        this.responseClass = responseClass;
        this.streamEventContentExtractor = streamEventContentExtractor;
        this.okHttpClient = null;
        this.endpointUrl = null;
        this.logStreamingResponses = false;
    }

    RequestExecutor(Call<Response> call, Function<Response, ResponseContent> responseContentExtractor) {
        this.call = call;
        this.responseContentExtractor = responseContentExtractor;
        this.okHttpClient = null;
        this.endpointUrl = null;
        this.requestWithStreamSupplier = null;
        this.responseClass = null;
        this.streamEventContentExtractor = null;
        this.logStreamingResponses = false;
    }

    public ResponseContent execute() {
        RequestBody body = this.call.request().body();
        if (body != null) {
            final Buffer buffer = new Buffer();
            try {
                body.writeTo(buffer);
                LOG.debug(buffer.readUtf8());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return (new SyncRequestExecutor<>(this.call, this.responseContentExtractor)).execute();
    }

    public AsyncResponseHandling onResponse(Consumer<ResponseContent> responseHandler) {
        return (new AsyncRequestExecutor<>(this.call, this.responseContentExtractor)).onResponse(responseHandler);
    }

    public StreamingResponseHandling onPartialResponse(Consumer<ResponseContent> partialResponseHandler) {
        return (new StreamingRequestExecutor(this.okHttpClient, this.endpointUrl, this.requestWithStreamSupplier,
                this.responseClass, this.streamEventContentExtractor, this.logStreamingResponses)).onPartialResponse(
                partialResponseHandler);
    }
}
