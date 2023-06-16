package com.mystore.shared.core.util;

import com.mystore.shared.core.IMessageSource;

public final class MessageUtil {

    private MessageUtil() {
    }

    private static IMessageSource messageSource;

    public static String getMessageByCode(String code, Object... args) {
        return messageSource.getMessageByCode(code, args);
    }

    public static String getMessageByCode(String defaultMessage, String code, Object[] args) {
        return messageSource.getMessageByCode(defaultMessage, code, args);
    }

    public static void setMessageSource(IMessageSource messageSource) {
        MessageUtil.messageSource = messageSource;
    }

}
