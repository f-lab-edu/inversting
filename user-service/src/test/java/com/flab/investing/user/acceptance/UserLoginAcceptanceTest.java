package com.flab.investing.user.acceptance;

import com.flab.investing.global.error.constant.ExceptionCode;
import com.flab.investing.user.acceptance.step.UserControllerStep;
import com.flab.investing.user.controller.request.LoginRequest;
import com.flab.investing.user.controller.request.RegisterRequest;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.flab.investing.global.error.exception.constant.ExceptionMessage.NOTFOUND_USER;
import static org.assertj.core.api.Assertions.assertThat;

public class UserLoginAcceptanceTest extends AcceptanceTest{

    private LoginRequest loginRequest;
    private LoginRequest emptyLoginRequest;

    @BeforeEach
    void setup() {
        super.setUp();

        this.loginRequest = new LoginRequest("flab@naver.com", "flab");
        this.emptyLoginRequest = new LoginRequest("", "");

        RegisterRequest request = new RegisterRequest("flab@naver.com", "flab", "flab", "에프랩");
        UserControllerStep.register(request);
    }

    @DisplayName("로그인을 한다.")
    @Test
    void login() {
        ResponseBodyExtractionOptions body = UserControllerStep.login(loginRequest).body();

        assertThat(body.jsonPath().getString("code")).isEqualTo(ExceptionCode.SUCCESS.getCode());
        assertThat(body.jsonPath().getString("message")).isEqualTo(ExceptionCode.SUCCESS.getDescription());
        assertThat(body.jsonPath().getString("accessToken")).isNotBlank();
        assertThat(body.jsonPath().getString("refreshToken")).isNotBlank();
    }

    @DisplayName("로그인시, 빈 값의 데이터가 있으면 에러를 반환한다.")
    @Test
    void validateLoginException() {
        ResponseBodyExtractionOptions body = UserControllerStep.login(emptyLoginRequest).body();

        assertThat(body.jsonPath().getString("code")).isEqualTo(ExceptionCode.BAD_REQUEST.getCode());
    }

    @DisplayName("로그인시, 회원가입이 안되어 있으면 에러가 발생한다.")
    @Test
    void isNotRegisterLoginException() {
        ResponseBodyExtractionOptions body = UserControllerStep.login(new LoginRequest("llab@naver.com", "1234")).body();

        assertThat(body.jsonPath().getString("code")).isEqualTo(ExceptionCode.CUSTOM_EXCEPTION.getCode());
        assertThat(body.jsonPath().getString("message")).isEqualTo(NOTFOUND_USER.getMessage());
    }

}
