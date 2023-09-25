package dev.baidu.client.exception;

public class LllmEmptyResponseException extends RuntimeException {

    public LllmEmptyResponseException() {
        super();
    }

    public LllmEmptyResponseException(String message) {
        super(message);
    }
}
