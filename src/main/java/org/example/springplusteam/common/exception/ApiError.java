package org.example.springplusteam.common.exception;

public record ApiError(
        String message,
        int statusCode
) {
}
