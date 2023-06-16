package com.mystore.wishlist.apprest.config;

import com.mystore.shared.core.exception.ApplicationException;
import com.mystore.shared.core.exception.BusinessException;
import com.mystore.shared.core.exception.ValidationBusinessException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.Map;

@RestControllerAdvice
public class RestControllersGlobalConfig {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ExceptionHandler
    public ResponseEntity<Response<?>> handleException(Exception e) {
        return ResponseEntity.internalServerError().body(
                getErrorBodyBuilder(e, true).build()
        );
    }

    @ExceptionHandler
    public ResponseEntity<Response<?>> handleEntityValidationBusinessException(ApplicationException e) {
        return ResponseEntity.unprocessableEntity().body(
                getErrorBodyBuilder(e, false)
                        .data(Map.of("errorType", e.getClass().getSimpleName(), "errorCode", e.getErrorCode()))
                        .build()
        );
    }

    @ExceptionHandler
    public ResponseEntity<Response<?>> handleEntityValidationBusinessException(BusinessException e) {
        return ResponseEntity.unprocessableEntity().body(
                getErrorBodyBuilder(e, false)
                        .data(Map.of("errorType", e.getClass().getSimpleName(), "errorCode", e.getErrorCode()))
                        .build()
        );
    }

    @ExceptionHandler
    public ResponseEntity<Response<?>> handleEntityValidationBusinessException(ValidationBusinessException e) {
        return ResponseEntity.unprocessableEntity().body(
                getErrorBodyBuilder(e, false)
                        .data(e.getValidationResult())
                        .build()
        );
    }

    private static Response.ResponseBuilder<Object> getErrorBodyBuilder(Exception e, boolean includeTrace) {
        return Response.builder()
                .ok(false)
                .timestamp(ZonedDateTime.now())
                .message(e.getMessage())
                .trace(includeTrace ? ExceptionUtils.getStackTrace(e) : null);
    }

}
