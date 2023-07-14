package com.court.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public interface Messages {
    static String getMessage(MessageSource messageSource,  String messageKey, Object[] arguments) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(messageKey, arguments, currentLocale);
    }

    static String getMessageWithDefault(MessageSource messageSource,  String messageKey,  String defaultMessage) {
        Locale currentLocale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(messageKey, null, defaultMessage, currentLocale);
    }

}
