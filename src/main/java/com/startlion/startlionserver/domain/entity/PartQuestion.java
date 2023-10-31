package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "part_question")
public class PartQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id")
    private Part partId;

    @Column(nullable = false)
    private Long generation;

    @Column(length = 100)
    private String partQuestion1;

    @Column(length = 100)
    private String partQuestion2;

    @Column(length = 100)
    private String partQuestion3;
}
