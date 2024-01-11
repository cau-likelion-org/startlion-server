package com.startlion.startlionserver.domain.entity;

import com.startlion.startlionserver.domain.BaseTimeEntity;
import com.startlion.startlionserver.domain.enums.ApplyPart;
import com.startlion.startlionserver.domain.enums.Gender;
import com.startlion.startlionserver.domain.enums.Semester;
import com.startlion.startlionserver.domain.enums.SubmitStatus;
import com.startlion.startlionserver.dto.request.application.ApplicationPage1Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage2Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage3Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage4Request;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)  @Getter
public class Application extends BaseTimeEntity {

    private static final boolean DEFAULT_AGREEMENT = true;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    private boolean isPersonalInformationAgreed = DEFAULT_AGREEMENT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(length = 30)
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int generation;
    private String studentNumber;
    @Column(length = 30)
    private String major;
    @Column(length = 30)
    private String multiMajor;
    @Enumerated(EnumType.STRING)
    private Semester semester;
    private String phone;
    private String email;
    @Enumerated(EnumType.STRING)
    private ApplyPart part;
    @Column(columnDefinition = "TEXT")
    private String portfolioUrl;
    private SubmitStatus status;
    private String availableInterviewTime;

    @Column(columnDefinition = "TEXT")
    private String commonAnswer1;
    @Column(columnDefinition = "TEXT")
    private String commonAnswer2;
    @Column(columnDefinition = "TEXT")
    private String commonAnswer3;
    @Column(columnDefinition = "TEXT")
    private String commonAnswer4;
    @Column(columnDefinition = "TEXT")
    private String commonAnswer5;
    @Column(columnDefinition = "TEXT")
    private String partAnswer1;
    @Column(columnDefinition = "TEXT")
    private String partAnswer2;
    @Column(columnDefinition = "TEXT")
    private String partAnswer3;
    @Column(columnDefinition = "TEXT")
    private String partAnswer4;

    @Builder
    private Application(boolean isPersonalInformationAgreed,
                       User user,
                       String name,
                       Gender gender,
                       String studentNumber,
                       String major,
                       String multiMajor,
                       Semester semester,
                       String phone,
                       String email,
                       ApplyPart part,
                       int generation
    ) {
        this.isPersonalInformationAgreed = isPersonalInformationAgreed;
        this.user = user;
        this.name = name;
        this.gender = gender;
        this.studentNumber = studentNumber;
        this.major = major;
        this.multiMajor = multiMajor;
        this.semester = semester;
        this.phone = phone;
        this.email = email;
        this.part = part;
        this.status = SubmitStatus.N;
        this.generation = generation;
    }

    public static Application create(ApplicationPage1Request request, User user, int generation) {
        return Application.builder()
                .isPersonalInformationAgreed(request.isPersonalInformationAgreed())
                .email(request.email())
                .gender(request.gender())
                .major(request.major())
                .multiMajor(request.multiMajor())
                .part(ApplyPart.valueOf(request.part()))
                .name(request.name())
                .phone(request.phone())
                .semester(Semester.valueOf(request.semester()))
                .studentNumber(request.studentNumber())
                .user(user)
                .generation(generation)
                .build();
    }

    public void updateApplicationPage2(ApplicationPage2Request request) {
        this.commonAnswer1 = request.commonAnswer1();
        this.commonAnswer2 = request.commonAnswer2();
        this.commonAnswer3 = request.commonAnswer3();
        this.commonAnswer4 = request.commonAnswer4();
        this.commonAnswer5 = request.commonAnswer5();
    }

    public void updateApplicationPage3(ApplicationPage3Request request) {
        this.partAnswer1 = request.partAnswer1();
        this.partAnswer2 = request.partAnswer2();
        this.partAnswer3 = request.partAnswer3();
        this.partAnswer4 = request.partAnswer4();
        this.portfolioUrl = request.portfolioUrl();
    }

    public void updateApplicationPage4(ApplicationPage4Request request) {
        this.availableInterviewTime = request.availableInterviewTime();
    }

    public void completeApplication() {
        this.status = SubmitStatus.Y;
    }

}

