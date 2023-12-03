package com.flab.investing.stock.infrastructure;

import com.flab.investing.stock.infrastructure.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(name = "userService", url = "${userService.url}")
public interface UserServiceClient {

    @GetMapping(value = "/users")
    UserResponse tokenValidate(@RequestHeader String accessToken);

}
