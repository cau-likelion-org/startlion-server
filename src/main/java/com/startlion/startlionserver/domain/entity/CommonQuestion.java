package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "common_question")
public class CommonQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long commonquestion_id;

    @Column(nullable = false, unique = true)
    private Long generation;

    private String commonquestion1;
    private String commonquestion2;
    private String commonquestion3;
    private String commonquestion4;
    private String commonquestion5;

    @OneToMany(mappedBy = "generation")
    private List<Application> applicationEntityList;
}