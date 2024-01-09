package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommonQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long commonQuestionId;

    @Column(nullable = false, unique = true)
    private Long generation;

    private String commonQuestion1;
    private String commonQuestion2;
    private String commonQuestion3;
    private String commonQuestion4;
    private String commonQuestion5;
}