package dev.baidu.client;

import java.io.IOException;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

public class SyncRequestExecutor<Response, ResponseContent> {
    private final static Logger LOG = LoggerFactory.getLogger(SyncRequestExecutor.class);
    private final Call<Response> call;
    private final Function<Response, ResponseContent> responseContentExtractor;

    SyncRequestExecutor(Call<Response> call, Function<Response, ResponseContent> responseContentExtractor) {
        this.call = call;
        this.responseContentExtractor = responseContentExtractor;
    }

    public ResponseContent execute() {
        try {
            retrofit2.Response<Response> retrofitResponse =  this.call.execute();
            if (retrofitResponse.isSuccessful()) {
                Response response = retrofitResponse.body();
                LOG.debug("response body:{}",response.toString());
                return this.responseContentExtractor.apply(response);
            } else {
                throw Utils.toException(retrofitResponse);
            }
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }
}
