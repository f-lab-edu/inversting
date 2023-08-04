package com.flab.investing.user.application;

import com.flab.investing.user.infrastructure.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    public void save(String name, String token, long time) {
        sessionRepository.save(name, token, time);
    }

}
