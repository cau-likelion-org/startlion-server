package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table
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

    @OneToMany(mappedBy = "generation")
    private List<Application> applicationEntityList = new ArrayList<>();
}