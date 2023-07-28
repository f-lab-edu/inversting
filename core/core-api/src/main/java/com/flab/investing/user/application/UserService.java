package com.flab.investing.user.application;

import com.flab.investing.user.application.dto.RegisterRequest;
import com.flab.investing.user.domain.User;
import com.flab.investing.user.domain.UserRepository;
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
                .userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .build();

        userRepository.save(user);
    }

}
