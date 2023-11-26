package com.flab.investing.stock.infrastructure;

import com.flab.investing.stock.infrastructure.request.UserRequest;
import com.flab.investing.stock.infrastructure.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "userService", url="${userService.url}")
public interface UserServiceClient {

    @GetMapping("/users")
    UserResponse tokenValidate(@RequestBody UserRequest request);

}

