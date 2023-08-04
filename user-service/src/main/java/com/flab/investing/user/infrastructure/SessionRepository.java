package com.flab.investing.user.infrastructure;

public interface SessionRepository {

    void save(String name, String token, long time);

}
