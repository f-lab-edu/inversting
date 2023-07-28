package com.flab.investing.user.domain;

import com.flab.investing.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "Users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private boolean otpRegistration;

    private int amount;

    @Builder
    public User(String userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.status = UserStatus.NOT_IDENTITY;
        this.role = UserRole.USER;
        this.otpRegistration = false;
        this.amount = 0;
    }
}
