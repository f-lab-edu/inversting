package com.flab.investing.stock.dao;

import com.flab.investing.stock.infrastructure.UserServiceClient;
import com.flab.investing.stock.infrastructure.request.UserRequest;
import com.flab.investing.stock.infrastructure.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserTokenDao {

    private final UserServiceClient userServiceClient;

    public UserResponse tokenSend(final UserRequest request) {
        log.info("user token 확인을 위한 ===> {}", request);
        UserResponse userResponse = userServiceClient.tokenValidate(request);
        log.info("user token 결과 =====> {} ", userResponse);
        return userResponse;
    }


}
