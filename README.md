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

public static void pluginRquest() {
        BaiduClient client = new BaiduClient(WENXIN_CLIENT_ID, WENXIN_CLIENT_SECRET);
        List<String> plugins = new ArrayList<>();
        plugins.add("uuid-weatherforecast");
        plugins.add("uuid-6c666c5b3b27566");
        plugins.add("uuid-websearch");
        PluginRequest pluginRequest = PluginRequest.builder().query("深圳天气怎么样").plugins(plugins).build();
        SyncOrAsyncOrStreaming<PluginResponse> completions = client.chatPlugin(pluginRequest,"servicename");
        String response=completions.execute().getResult();
        System.out.println(response);
    }
}
```
