package com.flab.investing.user.application.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequest {

    @NotBlank(message = "빈값은 넣을 수 없습니다.")
    private String userId;

    @NotBlank(message = "빈값은 넣을 수 없습니다.")
    private String password;

    public LoginRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
