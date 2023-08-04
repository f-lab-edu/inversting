package com.flab.investing.user.controller.response;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {}