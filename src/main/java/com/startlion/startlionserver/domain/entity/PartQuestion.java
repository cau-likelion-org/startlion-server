package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PartQuestion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id")
    private Part part;

    @Column(nullable = false)
    private Long generation;
    private String partQuestion1;
    private String partQuestion2;
    private String partQuestion3;
    private String partQuestion4;
}