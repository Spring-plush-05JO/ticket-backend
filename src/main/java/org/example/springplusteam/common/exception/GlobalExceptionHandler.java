package org.example.springplusteam.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<ApiError> apiException(CustomApiException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(new ApiError(e.getErrorCode().getMessage(), e.getErrorCode().getStatus()));
    }
}