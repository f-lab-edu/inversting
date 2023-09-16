package com.flab.investing.user.acceptance;

import com.flab.investing.global.error.constant.ExceptionCode;
import com.flab.investing.user.acceptance.step.UserControllerStep;
import com.flab.investing.user.controller.request.JwtRequest;
import com.flab.investing.user.controller.request.LoginRequest;
import com.flab.investing.user.controller.request.RegisterRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserValidateAcceptanceTest extends AcceptanceTest{

    private RegisterRequest registerRequest;
    private String accessToken;

    @BeforeEach
    void setup() {
        super.setUp();

        this.registerRequest = new RegisterRequest("flab@naver.com", "flab", "flab", "에프랩");
        this.accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmbGFiQG5hdmVyLmNvbSIsImlhdCI6MTY5NDg1NDI3OSwiZXhwIjoxNjk0ODk3NDc5fQ.mrKnTiCE6uMMnhQVdypk0g3cTFVjKbm3xpBG6cLjG2s";
        UserControllerStep.register(registerRequest);

    }

    @DisplayName("회원정보를 가져온다.")
    @Test
    void vaildate() {
        ExtractableResponse<Response> response = UserControllerStep.login(new LoginRequest("flab@naver.com", "flab"));
        String accessToken = response.body().jsonPath().getString("accessToken");

        ResponseBodyExtractionOptions result = UserControllerStep.validate(new JwtRequest(accessToken)).body();

        assertThat(result.jsonPath().getString("code")).isEqualTo(ExceptionCode.SUCCESS.getCode());
        assertThat(result.jsonPath().getString("message")).isEqualTo(ExceptionCode.SUCCESS.getDescription());
        assertThat(result.jsonPath().getString("userId")).isEqualTo(registerRequest.userId());
    }

    @DisplayName("레디스에 로그인 되어 있지 않을때, 에러를 반환한다.")
    @Test
    void isNotLoginException() {
        ResponseBodyExtractionOptions result = UserControllerStep.validate(new JwtRequest(accessToken)).body();

        assertThat(result.jsonPath().getString("code")).isEqualTo(ExceptionCode.NOT_FOUND_SESSION.getCode());
        assertThat(result.jsonPath().getString("message")).isEqualTo(ExceptionCode.NOT_FOUND_SESSION.getDescription());
    }

}
