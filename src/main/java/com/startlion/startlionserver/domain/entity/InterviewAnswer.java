package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "interview_answer")
public class InterviewAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long interviewAnswerId;

    @ManyToOne
    @JoinColumn(name = "interview_id")
    private Interview interview;

    @Column(length = 200)
    private String answer;

}