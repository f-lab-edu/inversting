package com.flab.investing.user.infrastructure;

import com.flab.investing.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId);

    Optional<User> findByUserId(String userId);

    Optional<User> findByName(String name);
}
