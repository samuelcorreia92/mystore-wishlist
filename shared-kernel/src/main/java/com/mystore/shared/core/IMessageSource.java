package com.mystore.shared.core;

public interface IMessageSource {

    default String getMessageByCode(String code, Object... args) {
        return getMessageByCode(code, code, args);
    }

    String getMessageByCode(String defaultMessage, String code, Object... args);

}
