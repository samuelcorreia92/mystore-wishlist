package com.mystore.shared.core.exception;

import com.mystore.shared.core.util.MessageUtil;

public class BusinessException extends MyStoreRuntimeException {

    private final String errorCode;

    BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    BusinessException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public static BusinessException fromErrorCode(String errorCode, Object... args) {
        return new BusinessException(errorCode, MessageUtil.getMessageByCode(errorCode, args));
    }

    public static BusinessException fromErrorCode(Exception e, String errorCode, Object... args) {
        return new BusinessException(errorCode, MessageUtil.getMessageByCode(errorCode, args), e);
    }

    public String getErrorCode() {
        return errorCode;
    }

}
