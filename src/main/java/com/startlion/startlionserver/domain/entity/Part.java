package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long partId;

    // BE FE DESIGN PM
    @Column(nullable = false)
    private String name;

    // 백엔드 프론트엔드 디자인 기획
    @Column(nullable = false)
    private String koreanName;

    @Column(columnDefinition = "TEXT")
    private String partContent;

    @Column(length = 200)
    private String typeOfTalent;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @Column(nullable = false)
    private Long generation;

}