package com.startlion.startlionserver.domain.entity;

import com.startlion.startlionserver.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(unique = true, length = 100)
    private String email;

    private String password; // 단방향 암호화 필요

    @Column(length = 30)
    private String username;

    private String socialId;

    private String imageUrl;

    private String accessToken;

    private String refreshToken;

    private LocalDateTime expiredIn;

    private boolean status;
}