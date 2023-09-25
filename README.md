# wenxin-java-sdk
百度文心一言的Java SDK
# 使用代码参考
```
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
```
