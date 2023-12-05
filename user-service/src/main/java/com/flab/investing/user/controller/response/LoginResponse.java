package com.flab.investing.user.controller.response;

public record LoginResponse(
        String code,
        String message,
        String accessToken,
        String refreshToken
) {}