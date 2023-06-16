package com.mystore.shared.core.util;

import java.util.Collection;
import java.util.Collections;

public record ValidationResult(Collection<Error> error) {

    public boolean isValid() {
        return error == null || error.isEmpty();
    }

    public Collection<String> getErrorMessages() {
        if (isValid()) return Collections.emptyList();

        return error.stream()
                .map(Error::message)
                .toList();
    }

    public record Error(String field, String errorCode, String message) {
    }

}
