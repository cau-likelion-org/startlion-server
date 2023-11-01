package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long answerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @Column(length = 500)
    private String commonAnswer1;

    @Column(length = 500)
    private String commonAnswer2;

    @Column(length = 500)
    private String commonAnswer3;

    @Column(length = 500)
    private String commonAnswer4;

    @Column(length = 500)
    private String commonAnswer5;

    @Column(length = 500)
    private String partAnswer1;

    @Column(length = 500)
    private String partAnswer2;

    @Column(length = 500)
    private String partAnswer3;
}