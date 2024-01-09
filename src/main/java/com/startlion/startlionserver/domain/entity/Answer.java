package com.startlion.startlionserver.domain.entity;

import com.startlion.startlionserver.dto.request.application.ApplicationPage2PutRequest;
import com.startlion.startlionserver.dto.request.application.ApplicationPage3PutRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long answerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @ColumnDefault("''")
    private String commonAnswer1;

    @ColumnDefault("''")
    private String commonAnswer2;

    @ColumnDefault("''")
    private String commonAnswer3;

    @ColumnDefault("''")
    private String commonAnswer4;

    @ColumnDefault("''")
    private String commonAnswer5;

    @ColumnDefault("''")
    private String partAnswer1;

    @ColumnDefault("''")
    private String partAnswer2;

    @ColumnDefault("''")
    private String partAnswer3;

    @ColumnDefault("''")
    private String partAnswer4;

    // 공동 답변 생성자
    public Answer(Application application, ApplicationPage2PutRequest request) {
        // 필드 초기화
        this.application = application;
        this.commonAnswer1 = request.commonAnswer1();
        this.commonAnswer2 = request.commonAnswer2();
        this.commonAnswer3 = request.commonAnswer3();
        this.commonAnswer4 = request.commonAnswer4();
        this.commonAnswer5 = request.commonAnswer5();
    }

    // 파트 답변 생성자
    public Answer(Application application, ApplicationPage3PutRequest request) {
        // 필드 초기화
        this.application = application;
        this.partAnswer1 = request.getPartAnswer1();
        this.partAnswer2 = request.getPartAnswer2();
        this.partAnswer3 = request.getPartAnswer3();
        this.partAnswer4 = request.getPartAnswer4();
    }

    // 공통 답변 업데이트
    public void updateCommonAnswers(String commonAnswer1, String commonAnswer2, String commonAnswer3, String commonAnswer4, String commonAnswer5) {
        this.commonAnswer1 = commonAnswer1;
        this.commonAnswer2 = commonAnswer2;
        this.commonAnswer3 = commonAnswer3;
        this.commonAnswer4 = commonAnswer4;
        this.commonAnswer5 = commonAnswer5;
    }

    // 파트 답변 업데이트
    public void updatePartAnswers(String partAnswer1, String partAnswer2, String partAnswer3, String partAnswer4) {
        this.partAnswer1 = partAnswer1;
        this.partAnswer2 = partAnswer2;
        this.partAnswer3 = partAnswer3;
        this.partAnswer4 = partAnswer4;
    }

    @Builder
    public Answer(Application application) {
        this.application = application;
    }

    public static Answer createBasicAnswer(Application application) {
        return Answer.builder()
                .application(application)
                .build();
    }
}