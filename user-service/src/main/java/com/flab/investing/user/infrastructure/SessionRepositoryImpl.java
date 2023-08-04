package com.flab.investing.user.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class SessionRepositoryImpl implements SessionRepository {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(String name, String token, long time) {
        redisTemplate.opsForValue().set(
                name,
                token,
                time,
                TimeUnit.MILLISECONDS
        );
    }
}
