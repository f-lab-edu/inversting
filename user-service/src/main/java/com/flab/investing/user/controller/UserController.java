package com.flab.investing.user.controller;

import com.flab.investing.global.error.constant.ExceptionCode;
import com.flab.investing.global.error.exception.ExistsEmailException;
import com.flab.investing.global.error.exception.InvalidJwtException;
import com.flab.investing.global.error.exception.NotFoundSessionException;
import com.flab.investing.global.error.exception.NotMatchPasswordException;
import com.flab.investing.support.jwt.JwtTokenProvider;
import com.flab.investing.user.application.AuthenticationService;
import com.flab.investing.user.application.UserService;
import com.flab.investing.user.controller.request.LoginRequest;
import com.flab.investing.user.controller.request.RegisterRequest;
import com.flab.investing.user.controller.response.LoginResponse;
import com.flab.investing.user.controller.response.RegisterResponse;
import com.flab.investing.user.controller.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping(value = "/users")
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
    @PostMapping
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        if (!request.password().equals(request.confirmPassword())) {
            throw new NotMatchPasswordException();
        }

        if (userService.existsByUserId(request.userId())) {
            throw new ExistsEmailException();
        }
        userService.register(request);

        return ResponseEntity.created(URI.create("/user/" + request.userId()))
                .body(new RegisterResponse(ExceptionCode.SUCCESS.getCode(), ExceptionCode.SUCCESS.getDescription()));
    }

    /**
     * 로그인을 합니다.
     *
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authenticationService.login(request);

        System.out.println(response);

        return ResponseEntity.ok(response);
    }

    /**
     * 로그인이 되어 있으면 회원 이름을 반환한다.
     *
     * @param accessToken
     * @return
     */
    @GetMapping
    public ResponseEntity<UserResponse> validate(@RequestHeader String accessToken) {
        if (!jwtTokenProvider.validateToken(accessToken)) {
            throw new InvalidJwtException();
        }

        String userId = jwtTokenProvider.getRedisSession(accessToken);

        if (Objects.isNull(userId)) {
            throw new NotFoundSessionException();
        }

        return ResponseEntity.ok(new UserResponse(
                ExceptionCode.SUCCESS.getCode(),
                ExceptionCode.SUCCESS.getDescription(),
                userService.getUserId(userId),
                userId));
    }

}
