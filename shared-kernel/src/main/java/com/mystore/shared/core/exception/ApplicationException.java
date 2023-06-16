package com.mystore.shared.core.exception;

import com.mystore.shared.core.util.MessageUtil;

public class ApplicationException extends MyStoreRuntimeException {

    private final String errorCode;

    ApplicationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    ApplicationException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public static ApplicationException fromErrorCode(String errorCode, Object... args) {
        return new ApplicationException(errorCode, MessageUtil.getMessageByCode(errorCode, args));
    }

    public static ApplicationException fromErrorCode(Exception e, String errorCode, Object... args) {
        return new ApplicationException(errorCode, MessageUtil.getMessageByCode(errorCode, args), e);
    }

    public String getErrorCode() {
        return errorCode;
    }

}
