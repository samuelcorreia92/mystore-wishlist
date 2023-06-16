package com.mystore.shared.core.util;

import com.mystore.shared.core.exception.ValidationBusinessException;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Pattern;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    private static final Map<String, Pattern> CACHE_REGEX_PATTERN = new HashMap<>();

    public static EntityValidationBuilder init() {
        return new EntityValidationBuilder();
    }

    public static class EntityValidationBuilder {
        private final Collection<ValidationResult.Error> error = new HashSet<>();

        public ValidationResult build(boolean throwExceptionIfInvalid) {
            var validationResult = new ValidationResult(error);
            if (!validationResult.isValid() && throwExceptionIfInvalid) {
                throw new ValidationBusinessException(validationResult);
            }
            return validationResult;
        }

        public EntityValidationBuilder notNullOrEmpty(String value, String field, String errorCode, Object... messageArgs) {
            if (value == null || value.trim().isBlank()) addError(field, errorCode, messageArgs);
            return this;
        }

        public EntityValidationBuilder minLength(String value, String field, int minLength, String errorCode, Object... messageArgs) {
            if (value != null && value.length() < minLength) addError(field, errorCode, messageArgs);
            return this;
        }

        public EntityValidationBuilder maxLength(Collection<?> value, String field, int maxLength, String errorCode, Object... messageArgs) {
            if (value != null && value.size() > maxLength) addError(field, errorCode, messageArgs);
            return this;
        }

        public EntityValidationBuilder regex(String value, String field, String regexp, String errorCode, Object... messageArgs) {
            var pattern = CACHE_REGEX_PATTERN.computeIfAbsent(regexp, Pattern::compile);
            if (value != null && !pattern.matcher(value).matches()) addError(field, errorCode, messageArgs);
            return this;
        }

        private void addError(String field, String errorCode, Object... messageArgs) {
            var message = MessageUtil.getMessageByCode(errorCode, messageArgs);
            error.add(new ValidationResult.Error(field, errorCode, message));
        }
    }

}
