package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PartQuestion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    @Enumerated(EnumType.STRING)
    private String part;
    @Column(nullable = false)
    private int generation;
    @Column(columnDefinition = "TEXT")
    private String partQuestion1;
    @Column(columnDefinition = "TEXT")
    private String partQuestion2;
    @Column(columnDefinition = "TEXT")
    private String partQuestion3;
    @Column(columnDefinition = "TEXT")
    private String partQuestion4;

    @Builder
    private PartQuestion(String part, int generation, String partQuestion1, String partQuestion2, String partQuestion3, String partQuestion4) {
        this.part = part;
        this.generation = generation;
        this.partQuestion1 = partQuestion1;
        this.partQuestion2 = partQuestion2;
        this.partQuestion3 = partQuestion3;
        this.partQuestion4 = partQuestion4;
    }
}