package dev.baidu;

import dev.baidu.client.BaiduClient;
import dev.baidu.client.SyncOrAsyncOrStreaming;

public class TestExample {

    public static final String WENXIN_CLIENT_ID = "demo";
    public static final String WENXIN_CLIENT_SECRET = "demo";
    public static void main(String[] args) {
        BaiduClient client = new BaiduClient(WENXIN_CLIENT_ID, WENXIN_CLIENT_SECRET);
        SyncOrAsyncOrStreaming<String> completions = client.chatCompletion("三体人是什么？",false);
        String response=completions.execute();
        System.out.println(response);
    }
}
