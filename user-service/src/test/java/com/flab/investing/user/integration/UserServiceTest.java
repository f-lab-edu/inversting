package com.flab.investing.user.integration;

import com.flab.investing.user.application.UserService;
import com.flab.investing.user.controller.request.RegisterRequest;
import com.flab.investing.user.domain.User;
import com.flab.investing.user.infrastructure.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class UserServiceTest extends ServiceTest {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserServiceTest(UserService userService,
                           UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @DisplayName("회원가입을 한다.")
    @Test
    void register() {
        RegisterRequest request = new RegisterRequest("flab@naver.com", "flab", "flab", "에프랩");

        userService.register(request);

        User user = userRepository.findByUserId(request.userId()).get();
        assertThat(user.getUserId()).isEqualTo(request.userId());
    }

    @DisplayName("회원아이디를 주면, 아이디값을 반환한다.")
    @Test
    void getUserId() {
        userService.register(new RegisterRequest("flab@naver.com", "flab", "flab", "에프랩"));

        Long userId = userService.getUserId("flab@naver.com");

        assertThat(userId).isNotNull();
    }

    @DisplayName("회원이 존재하지 않으면 에러를 반환한다.")
    @Test
    void notFoundMemberException() {

        assertThatThrownBy(() -> {
                    userService.getUserId("flab@naver.com");
                })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 아이디입니다.");

    }

}
