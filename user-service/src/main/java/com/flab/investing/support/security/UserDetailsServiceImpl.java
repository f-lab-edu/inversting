package com.flab.investing.support.security;

import com.flab.investing.global.error.exception.NotFoundUserIdException;
import com.flab.investing.user.domain.User;
import com.flab.investing.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) {
        User user = userRepository.findByUserId(id)
                .orElseThrow(() -> new NotFoundUserIdException());

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        return new org
                .springframework
                .security
                .core
                .userdetails
                .User(user.getUserId(), user.getPassword(), grantedAuthorities);
    }
}
