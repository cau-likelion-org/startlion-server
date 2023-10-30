package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "curriculum")
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long curriculum_id;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part_id;

    @Column(nullable = false)
    private Long generation;

    @Column(length = 300)
    private String content;
}