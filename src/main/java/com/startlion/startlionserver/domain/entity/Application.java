package com.startlion.startlionserver.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.startlion.startlionserver.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

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
    @JsonIgnore // 무한 참조 에러 방지
    private Answer answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "generation")
    @JsonIgnore // 무한 참조 에러 방지
    private CommonQuestion generation; // generation이라고 매핑했지만 실제로는 CommonQuestion의 id를 가리킴

    // application page 1 start
    @ColumnDefault("''")
    private Boolean isAgreed;

    @Column(length = 30)
    @ColumnDefault("''")
    private String name;

    @Column(length = 1)
    @ColumnDefault("''")
    private String gender;

    @ColumnDefault("0")
    private Integer studentNum;

    @Column(length = 30)
    @ColumnDefault("''")
    private String major;

    @Column(length = 30)
    private String multiMajor;

    @ColumnDefault("''")
    private String semester;

    @ColumnDefault("''")
    private String phone;

    @Column(unique = true, length = 100)
    @ColumnDefault("''")
    private String email;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "applicationId") // application 저장 시, pathToKnows도 함께 저장
    @JsonIgnore // 무한 참조 에러 방지
    private List<PathToKnow> pathToKnows = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id")
    @JsonIgnore // 무한 참조 에러 방지
    private Part part;
    // application page 1 end


    @ColumnDefault("''")
    private String portfolio;

    @ColumnDefault("''")
    private String interview;

    @ColumnDefault("'N'")
    private String status;

    //builder
    @Builder
    public Application(Answer answer, User user, CommonQuestion generation, boolean isAgreed, String name, String gender, Integer studentNum, String major, String multiMajor, String semester, String phone, String email, List<PathToKnow> pathToKnows, Part part, String portfolio, String interview, String status){
        this.answer = answer;
        this.user = user;
        this.generation = generation;
        this.isAgreed = isAgreed;
        this.name = name;
        this.gender = gender;
        this.studentNum = studentNum;
        this.major = major;
        this.multiMajor = multiMajor;
        this.semester = semester;
        this.phone = phone;
        this.email = email;
        this.pathToKnows = pathToKnows;
        this.part = part;
        this.portfolio = portfolio;
        this.interview = interview;
        this.status = status;
    }

    public void updateApplication(boolean isAgreed, User user,String name, String gender, Integer studentNum, String major, String multiMajor, String semester, String phone, String email, List<PathToKnow> pathToKnows, Part part, String status, CommonQuestion generation){
        this.isAgreed = isAgreed;
        this.name = name;
        this.gender = gender;
        this.studentNum = studentNum;
        this.major = major;
        this.multiMajor = multiMajor;
        this.semester = semester;
        this.phone = phone;
        this.email = email;
        this.pathToKnows = pathToKnows;
        this.part = part;
        this.status = status;
        this.generation = generation;
        this.user = user;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public void updateInterview(String interview, String status) {
        this.interview = interview;
        this.status = status; // interview가 있다면 제출한 것이므로 status = 'Y'
    }

    public void updatePortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public void updateCommonQuestion(CommonQuestion generation) {
        this.generation = generation;
    }
}

