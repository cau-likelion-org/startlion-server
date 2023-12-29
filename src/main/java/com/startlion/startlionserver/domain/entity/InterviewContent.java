package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table
public class InterviewContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long interviewAnswerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_id")
    private Interview interview;

    @Column(length = 400)
    private String question;

    @Column(length = 400)
    private String answer;

    @Column
    private String boldAnswer;
}