package com.mystore.shared.core.exception;

public class MyStoreRuntimeException extends RuntimeException {

    public MyStoreRuntimeException(String message) {
        super(message);
    }

    public MyStoreRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
