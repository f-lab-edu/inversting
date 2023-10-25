package com.flab.investing.user.acceptance;

import com.flab.investing.global.error.constant.ExceptionCode;
import com.flab.investing.global.error.exception.constant.ExceptionMessage;
import com.flab.investing.user.acceptance.step.UserControllerStep;
import com.flab.investing.user.controller.request.RegisterRequest;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.flab.investing.global.error.exception.constant.ExceptionMessage.ALREADY_EMAIL;
import static org.assertj.core.api.Assertions.assertThat;

public class UserRegisterAcceptanceTest extends AcceptanceTest {

    private RegisterRequest registerRequest;
    private RegisterRequest isNotMatchRequest;
    private RegisterRequest emptyRequest;

    @BeforeEach
    void setup() {
        super.setUp();

        this.registerRequest = new RegisterRequest("flab@naver.com", "flab", "flab", "에프랩");
        this.isNotMatchRequest = new RegisterRequest("flab@naver.com", "flab", "llab", "에프랩");
        this.emptyRequest = new RegisterRequest("", "", "", "");
    }

    @DisplayName("회원가입을 한다.")
    @Test
    void register() {
        ResponseBodyExtractionOptions body = UserControllerStep.register(registerRequest).body();

        assertThat(body.jsonPath().getString("code")).isEqualTo(ExceptionCode.SUCCESS.getCode());
        assertThat(body.jsonPath().getString("message")).isEqualTo(ExceptionCode.SUCCESS.getDescription());
    }

    @DisplayName("회원가입시, 확인 비밀번호와 비밀번호가 다르면 에러를 반환한다.")
    @Test
    void isNotMatchPasswordException() {
        ResponseBodyExtractionOptions body = UserControllerStep.register(isNotMatchRequest).body();

        assertThat(body.jsonPath().getString("code")).isEqualTo(ExceptionCode.CUSTOM_EXCEPTION.getCode());
        assertThat(body.jsonPath().getString("message")).isEqualTo(ExceptionMessage.NOT_MATCH_PASSWORD.getMessage());
    }

    @DisplayName("회원가입시, 이미 회원이 있으면 에러를 반환한다.")
    @Test
    void isExistsByUserIdException() {
        UserControllerStep.register(registerRequest).body();
        ResponseBodyExtractionOptions body = UserControllerStep.register(registerRequest).body();

        assertThat(body.jsonPath().getString("code")).isEqualTo(ExceptionCode.CUSTOM_EXCEPTION.getCode());
        assertThat(body.jsonPath().getString("message")).isEqualTo(ALREADY_EMAIL.getMessage());
    }

    @DisplayName("회원가입시, 파라미터 빈값이 있을시, 에러를 반환한다.")
    @Test
    void validateParameterException() {
        ResponseBodyExtractionOptions body = UserControllerStep.register(emptyRequest).body();

        assertThat(body.jsonPath().getString("code")).isEqualTo(ExceptionCode.BAD_REQUEST.getCode());
    }

}
