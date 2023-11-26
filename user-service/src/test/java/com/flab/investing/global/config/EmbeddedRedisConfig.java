package com.flab.investing.global.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

@Configuration
public class EmbeddedRedisConfig {
    private RedisServer redisServer;

    public EmbeddedRedisConfig() {
        this.redisServer = new RedisServer(6379);
    }

    @PostConstruct
    public void startRedis() {
        try {
            if (!redisServer.isActive()) {
                this.redisServer.start();
            }
        } catch (Exception e) { }
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            this.redisServer.stop();
        }
    }
}
