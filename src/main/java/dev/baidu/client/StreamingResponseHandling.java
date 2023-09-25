package dev.baidu.client;

public interface StreamingResponseHandling extends AsyncResponseHandling {
    StreamingCompletionHandling onComplete(Runnable var1);
}
