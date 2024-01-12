package com.startlion.startlionserver.domain.entity;

import com.startlion.startlionserver.domain.BaseTimeEntity;
import com.startlion.startlionserver.domain.enums.*;
import com.startlion.startlionserver.dto.request.application.ApplicationPage1Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage2Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage3Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage4Request;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

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

    /*
   이름 (필수)
    */
    @Column(length = 30)
    private String name;

    /*
    성별 (필수)
    */
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /*
    기수 (필수)
    */
    private int generation;

    /*
   학번 (필수)
   */
    private String studentNumber;

    /*
    전공 (필수)
     */
    @Column(length = 30)
    private String major;

    /*
    다중 전공 (선택)
     */
    @Column(length = 30)
    private String multiMajor;

    /*
    학기 (필수)
     */
    @Enumerated(EnumType.STRING)
    private Semester semester;

    /*
    휴대폰 번호 (필수)
     */
    private String phone;

    /*
    이메일 (필수)
     */
    private String email;

    /*
    지원파트 (필수)
     */
    @Enumerated(EnumType.STRING)
    private ApplyPart part;

    /*
    포트폴리오 Url (선택)
     */
    @Column(columnDefinition = "TEXT")
    private String portfolioUrl;

    /*
    제출 상태
     */
    private SubmitStatus status;

    /*
    인터뷰 가능 시간
     */
    private String availableInterviewTime;

    @Enumerated(EnumType.STRING)
    private PathType pathToKnow;

    private String etcDetail;

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
                       int generation,
                        PathType pathToKnow,
                        String etcDetail
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
        this.pathToKnow = pathToKnow;
        this.etcDetail = etcDetail;
    }

    public static Application create(ApplicationPage1Request request, User user, int generation) {
        return Application.builder()
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
                .pathToKnow(PathType.valueOf(request.pathToKnow()))
                .build();
    }

    public void updateApplicationPage1(ApplicationPage1Request request) {
        validateSubmitStatus();
        this.email = request.email();
        this.gender = request.gender();
        this.major = request.major();
        this.multiMajor = request.multiMajor();
        this.part = ApplyPart.valueOf(request.part());
        this.name = request.name();
        this.phone = request.phone();
        this.semester = Semester.valueOf(request.semester());
        this.studentNumber = request.studentNumber();
        this.isPersonalInformationAgreed = request.isPersonalInformationAgreed();
    }

    public void updateApplicationPage2(ApplicationPage2Request request) {
        validateSubmitStatus();
        this.commonAnswer1 = request.commonAnswer1();
        this.commonAnswer2 = request.commonAnswer2();
        this.commonAnswer3 = request.commonAnswer3();
        this.commonAnswer4 = request.commonAnswer4();
        this.commonAnswer5 = request.commonAnswer5();
    }

    public void updateApplicationPage3(ApplicationPage3Request request) {
        validateSubmitStatus();
        this.partAnswer1 = request.partAnswer1();
        this.partAnswer2 = request.partAnswer2();
        this.partAnswer3 = request.partAnswer3();
        this.partAnswer4 = request.partAnswer4();
        this.portfolioUrl = request.portfolioUrl();
    }

    public void updateApplicationPage4(ApplicationPage4Request request) {
        validateSubmitStatus();
        this.availableInterviewTime = request.availableInterviewTime();
    }

    public void completeApplication() {
        validateSubmitStatus();
        isCompleteAnswer();
        this.status = SubmitStatus.Y;
    }

    private void isCompleteAnswer() {
        Assert.notNull(this.name, "이름이 입력되지 않았습니다.");
        Assert.notNull(this.gender, "성별이 입력되지 않았습니다.");
        Assert.notNull(this.email, "성별이 입력되지 않았습니다.");
        Assert.notNull(this.major, "전공이 입력되지 않았습니다.");
        Assert.notNull(this.studentNumber, "학번이 입력되지 않았습니다.");
        Assert.notNull(this.phone, "전화번호가 입력되지 않았습니다.");
        Assert.notNull(this.semester, "학기가 입력되지 않았습니다.");
        Assert.notNull(this.part, "지원 파트가 입력되지 않았습니다.");
    }

    private void validateSubmitStatus() {
        if (this.status == SubmitStatus.Y) {
            throw new IllegalArgumentException("이미 제출된 지원서입니다.");
        }
    }
}

