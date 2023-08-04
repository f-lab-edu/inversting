package com.flab.investing.user.controller.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "빈값은 넣을 수 없습니다.")
        String userId,
        @NotBlank(message = "빈값은 넣을 수 없습니다.")
        String password) {
}

