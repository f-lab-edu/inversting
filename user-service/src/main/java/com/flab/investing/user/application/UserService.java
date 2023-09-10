package com.flab.investing.user.application;

import com.flab.investing.user.controller.request.RegisterRequest;
import com.flab.investing.user.domain.User;
import com.flab.investing.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean existsByUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }

    @Transactional
    public void register(RegisterRequest request) {
        User user = User.builder()
                .userId(request.userId())
                .password(passwordEncoder.encode(request.password()))
                .name(request.name())
                .build();

        userRepository.save(user);
    }

    public Long getUserId(String userName) {
        return userRepository.findByName(userName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."))
                .getId();
    }

}
