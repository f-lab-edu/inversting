package com.flab.investing.user.controller;

import com.flab.investing.global.error.exception.ExistsEmailException;
import com.flab.investing.global.error.exception.NotMatchPasswordException;
import com.flab.investing.user.application.AuthenticationService;
import com.flab.investing.user.application.UserService;
import com.flab.investing.user.controller.request.LoginRequest;
import com.flab.investing.user.controller.response.LoginResponse;
import com.flab.investing.user.controller.request.RegisterRequest;
import com.flab.investing.user.controller.response.RegisterResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> logout(@Valid @RequestBody RegisterRequest request) {
        if (!request.password().equals(request.confirmPassword())) {
            throw new NotMatchPasswordException();
        }

        if (userService.existsByUserId(request.userId())) {
            throw new ExistsEmailException();
        }
        userService.register(request);

        //TODO 이메일 보내는 로직 추가 예정

        return ResponseEntity.created(URI.create("/user/" + request.userId()))
                .body(new RegisterResponse("이메일 인증후, 사용해 주세요."));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
