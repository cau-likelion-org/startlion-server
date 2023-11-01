package com.startlion.startlionserver.domain.entity;

import com.startlion.startlionserver.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table
public class Application extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long applicationId;

    @OneToOne(mappedBy = "application")
    private Answer answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generation")
    private CommonQuestion generation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id")
    private Part part;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean isAgreed;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 1)
    private String gender;

    @Column(nullable = false)
    private Integer studentNum;

    @Column(nullable = false, length = 30)
    private String major;

    @Column(length = 30)
    private String multiMajor;

    @Column(nullable = false)
    private Integer semester;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String pathToKnow;

    @Column(nullable = false)
    private String portfolio;

    @Column(nullable = false)
    private String interview;

    @Column(nullable = false)
    private String status;
}