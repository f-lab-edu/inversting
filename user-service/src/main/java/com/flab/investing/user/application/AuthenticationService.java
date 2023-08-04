package com.flab.investing.user.application;

import com.flab.investing.support.jwt.JwtTokenProvider;
import com.flab.investing.user.controller.request.LoginRequest;
import com.flab.investing.user.controller.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.userId(),
                        request.password()
                )
        );

        return new LoginResponse(
                jwtTokenProvider.createAccessToken(authentication),
                jwtTokenProvider.createRefreshToken(authentication));
    }

}
