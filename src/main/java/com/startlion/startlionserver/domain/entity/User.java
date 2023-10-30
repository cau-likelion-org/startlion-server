package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 50)
    private String password; // 단방향 암호화 필요

    @Column(length = 30)
    private String username;

    @Column(length = 50)
    private String google_id;

    private String image_url;

    private String access_token;

    private String refresh_token;

    private LocalDateTime expired_in;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private boolean status;
}