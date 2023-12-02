package com.flab.investing.user.documentation;

import com.flab.investing.global.error.constant.ExceptionCode;
import com.flab.investing.support.jwt.JwtTokenProvider;
import com.flab.investing.user.application.AuthenticationService;
import com.flab.investing.user.application.UserService;
import com.flab.investing.user.controller.request.LoginRequest;
import com.flab.investing.user.controller.request.RegisterRequest;
import com.flab.investing.user.controller.response.LoginResponse;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static com.flab.investing.user.documentation.step.UserInfoStep.userInfoDocumentationFilter;
import static com.flab.investing.user.documentation.step.UserLoginStep.userLoginDocumentationFilter;
import static com.flab.investing.user.documentation.step.UserRegisterStep.userRegisterDocumentationFilter;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class UserDocumentationTest extends Documentation {

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    private RegisterRequest registerRequest;
    private LoginResponse loginResponse;
    private LoginRequest loginRequest;
    private String accessToken;

    @BeforeEach
    void setup() {
        this.registerRequest = new RegisterRequest("flab@flab.com", "flab", "flab", "에프랩");
        this.loginRequest = new LoginRequest("flab@flab.com", "flab");
        this.loginResponse = new LoginResponse(ExceptionCode.SUCCESS.getCode(), ExceptionCode.SUCCESS.getDescription(), "accessToken", "refreshToken");
        this.accessToken = "accessToken";
    }

    @DisplayName("신규 회원을 등록한다.")
    @Test
    void register() {
        when(userService.existsByUserId(any())).thenReturn(false);
        doNothing().when(userService).register(any());

        RestAssured
                .given(specification).log().all()
                .filter(userRegisterDocumentationFilter("회원가입"))
                .body(registerRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/users")
                .then().log().all().extract();
    }

    @DisplayName("로그인 한다.")
    @Test
    void login() {
        when(authenticationService.login(any())).thenReturn(loginResponse);

        RestAssured
                .given(specification).log().all()
                .filter(userLoginDocumentationFilter("로그인"))
                .body(loginRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/users/login")
                .then().log().all().extract();
    }

    @DisplayName("로그인 정보를 가져온다.")
    @Test
    void validate() {
        when(jwtTokenProvider.validateToken(any())).thenReturn(true);
        when(jwtTokenProvider.getRedisSession(any())).thenReturn("flab@flab.com");
        when(userService.getUserId(any())).thenReturn(1L);

        RestAssured
                .given(specification).log().all()
                .filter(userInfoDocumentationFilter("회원정보 조회"))
                .header("accessToken", accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/users")
                .then().log().all().extract();
    }

}
