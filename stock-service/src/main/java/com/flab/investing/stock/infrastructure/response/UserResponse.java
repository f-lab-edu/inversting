package com.flab.investing.stock.infrastructure.response;

public record UserResponse(
        String code,
        Long userId,
        String message,
        String name
) {

}
