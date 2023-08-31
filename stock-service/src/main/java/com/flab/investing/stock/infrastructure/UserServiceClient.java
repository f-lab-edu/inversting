package com.flab.investing.stock.infrastructure;

import com.flab.investing.stock.infrastructure.request.UserRequest;
import com.flab.investing.stock.infrastructure.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "userService", url="${userService.url}")
public interface UserServiceClient {

    @PostMapping("/user/validate")
    UserResponse tokenValidate(@RequestBody UserRequest request);

}

