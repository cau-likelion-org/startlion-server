package com.startlion.startlionserver.domain.entity;

import com.startlion.startlionserver.domain.BaseTimeEntity;
import com.startlion.startlionserver.domain.enums.*;
import com.startlion.startlionserver.dto.request.application.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.Assert;

import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Application extends BaseTimeEntity {

    private static final int DEFAULT_ANSWER_LIMIT = 700;
    private static final int PLAN_PART_ANSWER_LIMIT = 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    /*
    개인정보 이용 동의 여부 (필수)
    */
    private boolean isPersonalInformationAgreed;

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
    @Enumerated(EnumType.STRING)
    private SubmitStatus status;

    /*
    인터뷰 가능 시간
     */
    private String availableInterviewTime1;
    private String availableInterviewTime2;
    private String availableInterviewTime3;

    /*
    알게 된 경로 (필수)
    */
    @Enumerated(EnumType.STRING)
    private PathType pathToKnow;

    /*
    기타 선택했을 때, 알게 된 경로
    */
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

    private boolean lastCheck = false;

    private int commonAnswer1Limit = DEFAULT_ANSWER_LIMIT;
    private int commonAnswer2Limit = DEFAULT_ANSWER_LIMIT;
    private int commonAnswer3Limit = DEFAULT_ANSWER_LIMIT;
    private int commonAnswer4Limit = DEFAULT_ANSWER_LIMIT;
    private int commonAnswer5Limit = DEFAULT_ANSWER_LIMIT;
    private int partAnswer1Limit = DEFAULT_ANSWER_LIMIT;
    private int partAnswer2Limit = DEFAULT_ANSWER_LIMIT;
    private int partAnswer3Limit = DEFAULT_ANSWER_LIMIT;
    private int partAnswer4Limit = DEFAULT_ANSWER_LIMIT;

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
        this.status = SubmitStatus.PROGRESSING;
        this.generation = generation;
        this.pathToKnow = pathToKnow;
        this.etcDetail = etcDetail;
    }

    public static Application create(ApplicationCreateRequest request, User user, int generation) {
        return Application.builder()
                .isPersonalInformationAgreed(false)
                .email(request.email())
                .gender(Gender.DEFAULT)
                .major(request.major())
                .multiMajor(request.multiMajor())
                .part(ApplyPart.valueOf(request.part()))
                .name("")
                .phone(request.phone())
                .semester(Semester.valueOf(request.semester()))
                .studentNumber(null)
                .user(user)
                .generation(generation)
                .pathToKnow(PathType.valueOf(request.pathToKnow()))
                .build();
    }

    public void updateApplicationPage1(ApplicationPage1Request request) {
        validateSubmitStatus();
        this.email = request.email();
        this.gender = Gender.valueOf(request.gender());
        this.major = request.major();
        this.multiMajor = request.multiMajor();
        this.part = ApplyPart.valueOf(request.part());
        this.name = request.name();
        this.phone = request.phone();
        this.semester = Semester.valueOf(request.semester());
        this.studentNumber = request.studentNumber();
        this.isPersonalInformationAgreed = request.isAgreed();

        if (ApplyPart.valueOf(request.part()) == ApplyPart.PM) {
            this.partAnswer1Limit = PLAN_PART_ANSWER_LIMIT;
            this.partAnswer2Limit = PLAN_PART_ANSWER_LIMIT;
            this.partAnswer3Limit = PLAN_PART_ANSWER_LIMIT;
            this.partAnswer4Limit = PLAN_PART_ANSWER_LIMIT;
        }
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
        val availableInterviewTime1 = request.firstDay()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        val availableInterviewTime2 = request.secondDay()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        val availableInterviewTime3 = request.thirdDay()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        this.availableInterviewTime1 = availableInterviewTime1;
        this.availableInterviewTime2 = availableInterviewTime2;
        this.availableInterviewTime3 = availableInterviewTime3;
        this.lastCheck = request.lastCheck();
    }

    public void completeApplication() {
        validateSubmitStatus();
        isCompleteAnswer();
        this.status = SubmitStatus.COMPLETE;
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
        if (this.status == SubmitStatus.COMPLETE) {
            throw new IllegalArgumentException("이미 제출된 지원서입니다.");
        }
    }
}

