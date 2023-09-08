package com.flab.investing.user.controller.response;

import com.flab.investing.user.common.constant.UserRole;

public record UserResponse(
        String code,
        String message,
        Long userId,
        String name
) {

}
