package com.flab.investing.user.controller.response;

public record UserResponse(
        String code,
        String message,
        Long id,
        String userEmail
) {

}
