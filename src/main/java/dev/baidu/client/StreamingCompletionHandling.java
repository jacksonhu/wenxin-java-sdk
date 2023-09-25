package dev.baidu.client;

import java.util.function.Consumer;

public interface StreamingCompletionHandling {

    ErrorHandling onError(Consumer<Throwable> var1);

    ErrorHandling ignoreErrors();
}
