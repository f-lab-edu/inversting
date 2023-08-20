package com.flab.investing.user.controller;

import com.flab.investing.global.error.constant.ExceptionCode;
import com.flab.investing.global.error.exception.ExistsEmailException;
import com.flab.investing.global.error.exception.InvalidJwtException;
import com.flab.investing.global.error.exception.NotFoundSessionException;
import com.flab.investing.global.error.exception.NotMatchPasswordException;
import com.flab.investing.support.jwt.JwtTokenProvider;
import com.flab.investing.user.application.AuthenticationService;
import com.flab.investing.user.application.UserService;
import com.flab.investing.user.controller.request.JwtRequest;
import com.flab.investing.user.controller.request.LoginRequest;
import com.flab.investing.user.controller.request.RegisterRequest;
import com.flab.investing.user.controller.response.LoginResponse;
import com.flab.investing.user.controller.response.RegisterResponse;
import com.flab.investing.user.controller.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원테이블에 저장합니다.
     *
     * @param request
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> logout(@Valid @RequestBody RegisterRequest request) {
        if (!request.password().equals(request.confirmPassword())) {
            throw new NotMatchPasswordException();
        }

        if (userService.existsByUserId(request.userId())) {
            throw new ExistsEmailException();
        }
        userService.register(request);

        return ResponseEntity.created(URI.create("/user/" + request.userId()))
                .body(new RegisterResponse("이메일 인증후, 사용해 주세요."));
    }

    /**
     * 로그인을 합니다.
     *
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    /**
     * 로그인이 되어 있으면 회원 이름을 반환한다.
     *
     * @param request
     * @return
     */
    @PostMapping("/validate")
    public ResponseEntity<UserResponse> validate(@RequestBody JwtRequest request) {
        if (!jwtTokenProvider.validateToken(request.accessToken())) {
            throw new InvalidJwtException();
        }

        String userName = jwtTokenProvider.getRedisSession(request.accessToken());

        if (Objects.isNull(userName)) {
            throw new NotFoundSessionException();
        }

        return ResponseEntity.ok(new UserResponse(ExceptionCode.SUCCESS.getCode(), ExceptionCode.SUCCESS.getDescription(), userName));
    }

}
