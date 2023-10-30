package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long answer_id;

    @OneToOne
    @JoinColumn(name = "application_id")
    private Application applicationEntity;

    @Column(length = 500)
    private String commonanswer1;

    @Column(length = 500)
    private String commonanswer2;

    @Column(length = 500)
    private String commonanswer3;

    @Column(length = 500)
    private String commonanswer4;

    @Column(length = 500)
    private String commonanswer5;

    @Column(length = 500)
    private String partanswer1;

    @Column(length = 500)
    private String partanswer2;

    @Column(length = 500)
    private String partanswer3;
}