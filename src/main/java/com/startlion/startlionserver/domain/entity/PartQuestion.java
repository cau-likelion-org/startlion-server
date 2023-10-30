package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "partquestion")
public class PartQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long question_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id")
    private Part part_id;

    @Column(nullable = false)
    private Long generation;

    @Column(length = 100)
    private String partquestion1;

    @Column(length = 100)
    private String partquestion2;

    @Column(length = 100)
    private String partquestion3;
}
