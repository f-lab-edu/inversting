package com.flab.investing.user.application.dto;

import lombok.Getter;

@Getter
public class RegisterResponse {

    private final String message;

    public RegisterResponse(String message) {
        this.message = message;
    }

    public static RegisterResponse ok() {
        return new RegisterResponse("이메일 인증후, 사용해 주세요.");
    }
}
