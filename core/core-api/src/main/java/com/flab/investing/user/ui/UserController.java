package com.flab.investing.user.ui;

import com.flab.investing.global.exception.custom.ExistsEmailException;
import com.flab.investing.global.exception.custom.NotMatchPasswordException;
import com.flab.investing.user.application.AuthenticationService;
import com.flab.investing.user.application.UserService;
import com.flab.investing.user.application.dto.LoginRequest;
import com.flab.investing.user.application.dto.LoginResponse;
import com.flab.investing.user.application.dto.RegisterRequest;
import com.flab.investing.user.application.dto.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> logout(@Valid @RequestBody RegisterRequest request) {
        if (!request.isEqualsPassword()) {
            throw new NotMatchPasswordException();
        }

        if (userService.existsByUserId(request.getUserId())) {
            throw new ExistsEmailException();
        }
        userService.register(request);

        //TODO 이메일 보내는 로직 추가 예정

        return ResponseEntity.created(URI.create("/user/" + request.getUserId()))
                .body(RegisterResponse.ok());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }


}
