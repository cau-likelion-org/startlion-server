package com.startlion.startlionserver.domain.entity;

import com.startlion.startlionserver.dto.request.application.ApplicationPage2PutRequest;
import com.startlion.startlionserver.dto.request.application.ApplicationPage3PutRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Table
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long answerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @Column(length = 500)
    @ColumnDefault("")
    private String commonAnswer1;

    @Column(length = 500)
    @ColumnDefault("")
    private String commonAnswer2;

    @Column(length = 500)
    @ColumnDefault("")
    private String commonAnswer3;

    @Column(length = 500)
    @ColumnDefault("")
    private String commonAnswer4;

    @Column(length = 500)
    @ColumnDefault("")
    private String commonAnswer5;

    @Column(length = 500)
    @ColumnDefault("")
    private String partAnswer1;

    @Column(length = 500)
    @ColumnDefault("")
    private String partAnswer2;

    @Column(length = 500)
    @ColumnDefault("")
    private String partAnswer3;

    // 공동 답변 생성자
    public Answer(Application application, ApplicationPage2PutRequest request) {
        // 필드 초기화
        this.application = application;
        this.commonAnswer1 = request.getCommonAnswer1();
        this.commonAnswer2 = request.getCommonAnswer2();
        this.commonAnswer3 = request.getCommonAnswer3();
        this.commonAnswer4 = request.getCommonAnswer4();
        this.commonAnswer5 = request.getCommonAnswer5();
    }

    // 파트 답변 생성자
    public Answer(Application application, ApplicationPage3PutRequest request) {
        // 필드 초기화
        this.application = application;
        this.partAnswer1 = request.getPartAnswer1();
        this.partAnswer2 = request.getPartAnswer2();
        this.partAnswer3 = request.getPartAnswer3();
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
    public void updatePartAnswers(String partAnswer1, String partAnswer2, String partAnswer3) {
        this.partAnswer1 = partAnswer1;
        this.partAnswer2 = partAnswer2;
        this.partAnswer3 = partAnswer3;
    }

    public void updateBlankAnswer(Application application) {
        this.application = application;
    }
}