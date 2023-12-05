package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long interviewId;

    @Column(length = 100)
    private String title;

    @Column(nullable = false)
    private Long generation;

    @Column(length = 30)
    private String part;

    @Column(length = 30)
    private String major;

    @Column(length = 30)
    private String name;

    @Column(length = 200)
    private String oneLineContent;

    @Column(length = 200)
    private String oneLineAnswer;

    private String imageUrl;

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}