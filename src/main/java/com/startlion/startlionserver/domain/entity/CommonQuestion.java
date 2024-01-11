package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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
    private int generation;

    @Column(columnDefinition = "TEXT")
    private String commonQuestion1;
    @Column(columnDefinition = "TEXT")
    private String commonQuestion2;
    @Column(columnDefinition = "TEXT")
    private String commonQuestion3;
    @Column(columnDefinition = "TEXT")
    private String commonQuestion4;
    @Column(columnDefinition = "TEXT")
    private String commonQuestion5;

    @Builder
    private CommonQuestion(
            int generation,
            String commonQuestion1,
            String commonQuestion2,
            String commonQuestion3,
            String commonQuestion4,
            String commonQuestion5) {
        this.generation = generation;
        this.commonQuestion1 = commonQuestion1;
        this.commonQuestion2 = commonQuestion2;
        this.commonQuestion3 = commonQuestion3;
        this.commonQuestion4 = commonQuestion4;
        this.commonQuestion5 = commonQuestion5;
    }
}