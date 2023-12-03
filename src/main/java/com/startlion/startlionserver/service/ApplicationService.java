package com.startlion.startlionserver.service;

import com.startlion.startlionserver.domain.entity.Answer;
import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.domain.entity.CommonQuestion;
import com.startlion.startlionserver.domain.entity.PathToKnow;
import com.startlion.startlionserver.dto.request.application.*;
import com.startlion.startlionserver.dto.response.application.ApplicationPage2GetResponse;
import com.startlion.startlionserver.dto.response.application.ApplicationPage4GetResponse;
import com.startlion.startlionserver.dto.response.application.ApplicationPage3GetResponse;
import com.startlion.startlionserver.dto.response.application.ApplicationPage1GetResponse;;
import com.startlion.startlionserver.global.exception.EmailAlreadyInUseException;
import com.startlion.startlionserver.repository.ApplicationJpaRepository;
import com.startlion.startlionserver.repository.CommonQuestionRepository;
import com.startlion.startlionserver.repository.PathToKnowJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationService {

    private final ApplicationJpaRepository applicationJpaRepository;
    private final CommonQuestionRepository commonQuestionRepository;
    private final PathToKnowJpaRepository pathToKnowJpaRepository;

    private final AnswerService answerService;

    // 저장된 지원서 없을 시, 지원서 1페이지 정보 가져오기
    public ApplicationPage1GetResponse getApplicationPersonalInformation() {
        return ApplicationPage1GetResponse.builder().build();
    }

    // 저장된 지원서 있을 시, 지원서 정보 가져오기
    public ResponseEntity<?> getById(Long applicationId, int page) {
        Application application = applicationJpaRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 applicationId를 가진 지원서가 존재하지 않습니다."));
        switch (page) {
            case 1:
                return ResponseEntity.ok(ApplicationPage1GetResponse.of(application));
            case 2:
                return ResponseEntity.ok(ApplicationPage2GetResponse.of(application.getAnswer()
                        , commonQuestionRepository.findByGeneration(application.getGeneration().getGeneration())
                                .orElseThrow(() -> new IllegalArgumentException("해당 generation을 가진 commonQuestion이 존재하지 않습니다."))));
            case 3:
                return ResponseEntity.ok(ApplicationPage3GetResponse.of(application.getAnswer(), application.getPart().getPartQuestions(), application.getPortfolio()));
            case 4:
                return ResponseEntity.ok(ApplicationPage4GetResponse.of(application.getInterview()));
            default:
                throw new IllegalArgumentException("페이지 번호가 잘못되었습니다.");
        }
    }

    // 지원서 1페이지 저장
    @Transactional
    public Long updateApplicationPage1(Long applicationId, ApplicationPage1PutRequest request, Long generationId) {
        // isAgreed 필드 null 체크
        if (request.getIsAgreed() == null) {
            throw new IllegalArgumentException("isAgreed 필드가 null입니다.");
        }

        // email 중복 확인
        Optional<Application> optionalApplicationWithEmail = applicationJpaRepository.findByEmail(request.getEmail());
        if (optionalApplicationWithEmail.isPresent() && !optionalApplicationWithEmail.get().getApplicationId().equals(applicationId)) {
            throw new EmailAlreadyInUseException("email is already in use '" + request.getEmail() + "'");
        }

        Application application;
        Optional<Application> optionalApplication = applicationJpaRepository.findById(applicationId);

        // generationId로 common question 찾기
        CommonQuestion commonQuestion = commonQuestionRepository.findById(generationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 CommonQuestion이 없습니다. id=" + generationId));


        // applicationId가 존재하면 update, 존재하지 않으면 create
        if (optionalApplication.isPresent()) {
            application = optionalApplication.get();
            application.updateApplication(request.getIsAgreed(), request.getUser(),request.getName(), request.getGender(), request.getStudentNum(), request.getMajor(), request.getMultiMajor(), request.getSemester(), request.getPhone(), request.getEmail(), request.getPathToKnows(), request.getPart(), "S", commonQuestion);
            applicationJpaRepository.save(application); //Application 저장

            pathToKnowJpaRepository.deleteByApplicationId(application); // 기존의 path to know 삭제
            List<PathToKnow> pathToKnows = new ArrayList<>();
            for(PathToKnow pathToKnow : request.getPathToKnows()){
                pathToKnow.setApplicationId(application);
                pathToKnows.add(pathToKnow);
                pathToKnowJpaRepository.save(pathToKnow);
            }
        } else {
            application = Application.builder()
                    .generation(commonQuestionRepository.findById(generationId)
                            .orElseThrow(() -> new IllegalArgumentException("해당 commonQuestionId를 가진 commonQuestion이 존재하지 않습니다.")))
                    .user(request.getUser())
                    .isAgreed(request.getIsAgreed())
                    .name(request.getName())
                    .gender(request.getGender())
                    .studentNum(request.getStudentNum())
                    .major(request.getMajor())
                    .multiMajor(request.getMultiMajor())
                    .semester(request.getSemester())
                    .phone(request.getPhone())
                    .email(request.getEmail())
                    .pathToKnows(request.getPathToKnows())
                    .part(request.getPart())
                    .status("S")
                    .build();

            applicationJpaRepository.save(application);

            // path to know 저장
            pathToKnowJpaRepository.deleteByApplicationId(application);
            List<PathToKnow> pathToKnows = new ArrayList<>();
            for(PathToKnow pathToKnow : request.getPathToKnows()){
                pathToKnow.setApplicationId(application);
                pathToKnows.add(pathToKnow);
                pathToKnowJpaRepository.save(pathToKnow);
            }

            application.updateCommonQuestion(commonQuestion);
            applicationJpaRepository.save(application);
        }

        return application.getApplicationId();
    }

    // 지원서 2페이지 저장
    @Transactional
    public Long updateApplicationPage2(Long applicationId, ApplicationPage2PutRequest request) {
        Application application = applicationJpaRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 applicationId를 가진 지원서가 존재하지 않습니다."));

        if (application.getAnswer() == null) {
            Answer newAnswer = answerService.createAnswer(application, request);
            application.setAnswer(newAnswer);
        } else {
            application.getAnswer().updateCommonAnswers(request.getCommonAnswer1(), request.getCommonAnswer2(), request.getCommonAnswer3(), request.getCommonAnswer4(), request.getCommonAnswer5());
        }

        return application.getApplicationId();
    }


    // 지원서 3페이지 저장
    @Transactional
    public Long updateApplicationPage3(Long applicationId, ApplicationPage3PutRequest request) {
        Application application = applicationJpaRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 applicationId를 가진 지원서가 존재하지 않습니다."));

        if (application.getAnswer() == null) {
            Answer newAnswer = answerService.createAnswer(application, request);
            application.setAnswer(newAnswer);
        } else {
            application.getAnswer().updatePartAnswers(request.getPartAnswer1(), request.getPartAnswer2(), request.getPartAnswer3());
        }

        // portfolio 업데이트
        application.updatePortfolio(request.getPortfolio());

        return application.getApplicationId();
    }

    // 지원서 4페이지 저장(제출)
    @Transactional
    public Long updateApplicationPage4(Long applicationId, ApplicationPage4PutRequest request) {
        Application application = applicationJpaRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 applicationId를 가진 지원서가 존재하지 않습니다."));

        application.updateInterview(request.getInterview(), "Y");

        return application.getApplicationId();
    }

}
