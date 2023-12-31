package dev.baidu.client;

public class BaiduHttpException extends RuntimeException {
    private final int code;

    public BaiduHttpException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int code() {
        return this.code;
    }
}
