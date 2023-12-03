package com.flab.investing.stock.infrastructure.response;

public record UserResponse(
        String code,
        String message,
        Long id,
        String userEmail
) {
}
