package dev.baidu;

import dev.baidu.client.BaiduClient;
import dev.baidu.client.SyncOrAsyncOrStreaming;
import dev.baidu.client.chat.ChatCompletionResponse;

public class TestExample {
    public static final String BAIDU_SECRET_KEY = "demo";
    public static final String WENXIN_CLIENT_ID = "demo";
    public static void main(String[] args) throws InterruptedException {
        BaiduClient client =BaiduClient.builder().wenXinApiKey(WENXIN_CLIENT_ID).wenXinSecretKey(BAIDU_SECRET_KEY).logStreamingResponses(true).build();
        SyncOrAsyncOrStreaming<ChatCompletionResponse> completions = client.chatCompletion("三体人是什么？", false);
        String response = completions.execute().toString();
        System.out.println(response);

        SyncOrAsyncOrStreaming<ChatCompletionResponse> completionsStreaming = client.chatCompletion("三体人是什么？", true);

        completionsStreaming.onPartialResponse(r -> {
            System.out.println(r.toString());
        }).onError(throwable -> {throwable.printStackTrace();}).execute();
    }
}
