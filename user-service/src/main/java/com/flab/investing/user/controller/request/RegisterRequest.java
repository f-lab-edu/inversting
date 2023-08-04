package com.flab.investing.user.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "빈값은 넣을 수 없습니다.")
        @Email(message = "이메일 형식으로 입력해 주세요.")
        String userId,

        @NotBlank(message = "빈값은 넣을 수 없습니다.")
        String password,

        @NotBlank(message = "빈값은 넣을 수 없습니다.")
        String confirmPassword,

        @NotBlank(message = "빈값은 넣을 수 없습니다.") String name) {
}