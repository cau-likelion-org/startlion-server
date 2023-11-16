package com.startlion.startlionserver.service;

import com.startlion.startlionserver.domain.entity.Answer;
import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.dto.request.application.ApplicationPage2PutRequest;
import com.startlion.startlionserver.dto.request.application.ApplicationPage3PutRequest;
import com.startlion.startlionserver.repository.AnswerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {

    private final AnswerJpaRepository answerJpaRepository;

    // 지원서 2페이지 Answer 탐색 -> 있으면 업데이트, 없으면 생성
    @Transactional
    public Answer createAnswer(Application application, ApplicationPage2PutRequest request){
        Answer answer = answerJpaRepository.findByApplication(application).orElse(new Answer(application, request));
        answer.updateCommonAnswers(request.getCommonAnswer1(), request.getCommonAnswer2(), request.getCommonAnswer3(), request.getCommonAnswer4(), request.getCommonAnswer5());
        return answerJpaRepository.save(answer);
    }

    // 지원서 3페이지 Answer 탐색 -> 있으면 업데이트, 없으면 생성
    @Transactional
    public Answer createAnswer(Application application, ApplicationPage3PutRequest request){
        Answer answer = answerJpaRepository.findByApplication(application).orElse(new Answer(application, request));
        answer.updatePartAnswers(request.getPartAnswer1(), request.getPartAnswer2(), request.getPartAnswer3());
        return answerJpaRepository.save(answer);
    }
}
