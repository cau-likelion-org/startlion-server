package com.startlion.startlionserver.domain.entity;

import com.startlion.startlionserver.domain.enums.IntervieweePart;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GraduateInterview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interviewId;

    @Column(length = 100)
    private String title;

    @Column(nullable = false)
    private Long generation;

    @Enumerated(STRING)
    private IntervieweePart part;

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