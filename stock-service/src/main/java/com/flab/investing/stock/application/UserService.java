package com.flab.investing.stock.application;

import com.flab.investing.stock.dao.UserTokenDao;
import com.flab.investing.stock.infrastructure.request.UserRequest;
import com.flab.investing.stock.infrastructure.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserTokenDao userTokenDao;

    public UserResponse tokenSend(final String accessToken) {
        return userTokenDao.tokenSend(new UserRequest(accessToken));
    }

}
