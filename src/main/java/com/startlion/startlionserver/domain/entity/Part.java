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
@Table
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long partId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "part")
    @JsonIgnore
    private List<PartQuestion> partQuestions;

    @Column(length = 200)
    private String partContent;

    @Column(length = 200)
    private String typeOfTalent;

    @Column(length = 500) // 이미지 URL 저장 필드 추가
    private String imageUrl;
}