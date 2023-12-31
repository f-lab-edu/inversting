package com.flab.investing.user.acceptance;

import com.flab.investing.global.error.constant.ExceptionCode;
import com.flab.investing.user.acceptance.step.UserControllerStep;
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

    @BeforeEach
    void setup() {
        super.setUp();

        this.registerRequest = new RegisterRequest("flab@naver.com", "flab", "flab", "에프랩");
        UserControllerStep.register(registerRequest);

    }

    @DisplayName("회원정보를 가져온다.")
    @Test
    void vaildate() {
        ExtractableResponse<Response> response = UserControllerStep.login(new LoginRequest("flab@naver.com", "flab"));
        String accessToken = response.body().jsonPath().getString("accessToken");

        ResponseBodyExtractionOptions result = UserControllerStep.validate(accessToken).body();

        assertThat(result.jsonPath().getString("code")).isEqualTo(ExceptionCode.SUCCESS.getCode());
        assertThat(result.jsonPath().getString("message")).isEqualTo(ExceptionCode.SUCCESS.getDescription());
        assertThat(result.jsonPath().getString("userEmail")).isEqualTo(registerRequest.userId());
    }

    @DisplayName("레디스에 로그인 되어 있지 않을때, 에러를 반환한다.")
    @Test
    void isNotLoginException() {
        String accessToken = UserControllerStep.login(new LoginRequest(registerRequest.userId(), registerRequest.password())).body().jsonPath().getString("accessToken");
        super.redisCleanup();

        ResponseBodyExtractionOptions result = UserControllerStep.validate(accessToken).body();

        assertThat(result.jsonPath().getString("code")).isEqualTo(ExceptionCode.NOT_FOUND_SESSION.getCode());
        assertThat(result.jsonPath().getString("message")).isEqualTo(ExceptionCode.NOT_FOUND_SESSION.getDescription());
    }

}
