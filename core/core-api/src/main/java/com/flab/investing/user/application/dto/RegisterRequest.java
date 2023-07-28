package com.flab.investing.user.application.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class RegisterRequest {

    @NotBlank(message = "빈값은 넣을 수 없습니다.")
    @Email(message = "이메일 형식으로 입력해 주세요.")
    private String userId;

    @NotBlank(message = "빈값은 넣을 수 없습니다.")
    private String password;
    private String confirmPassword;

    @NotBlank(message = "빈값은 넣을 수 없습니다.")
    private String name;

    public RegisterRequest(String userId, String password, String confirmPassword, String name) {
        this.userId = userId;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
    }

    public boolean isEqualsPassword() {
        return password.equals(confirmPassword);
    }
}
