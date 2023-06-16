package com.mystore.shared.core.exception;

import com.mystore.shared.core.util.ValidationResult;

//@SuppressWarnings("squid:S110")
public class ValidationBusinessException extends BusinessException {

    private final ValidationResult validationResult;

    public ValidationBusinessException(ValidationResult validationResult) {
        super("EntityValidationBusinessException", "Entity validation error... " + validationResult.getErrorMessages());
        this.validationResult = validationResult;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }

}
