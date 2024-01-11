package com.startlion.startlionserver.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long partId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String koreanName;

    @OneToMany(mappedBy = "part")
    @JsonIgnore
    private List<PartQuestion> partQuestions;

    @Column(length = 200)
    private String partContent;

    @Column(length = 200)
    private String typeOfTalent;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @Column(nullable = false)
    private Long generation;
}