package com.startlion.startlionserver.domain.entity;

import com.startlion.startlionserver.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 50)
    private String password; // 단방향 암호화 필요

    @Column(length = 30)
    private String username;

    @Column(length = 50)
    private String socialId;

    private String imageUrl;

    private String access_Token;

    private String refreshToken;

    private LocalDateTime expiredIn;

    private boolean status;
}