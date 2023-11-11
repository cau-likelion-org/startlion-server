package com.startlion.startlionserver.service;

import com.startlion.startlionserver.domain.entity.Answer;
import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.dto.request.application.ApplicationPage2PutRequest;
import com.startlion.startlionserver.dto.request.application.ApplicationPage3PutRequest;
import com.startlion.startlionserver.dto.request.application.ApplicationPage4PutRequest;
import com.startlion.startlionserver.repository.AnswerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {

    private final AnswerJpaRepository answerJpaRepository;

    @Transactional
    public Answer createAnswer(Application application, ApplicationPage2PutRequest request){
        Answer answer = answerJpaRepository.findByApplication(application).orElse(new Answer(application, request));
        answer.updateCommonAnswers(request.getCommonAnswer1(), request.getCommonAnswer2(), request.getCommonAnswer3(), request.getCommonAnswer4(), request.getCommonAnswer5());
        return answerJpaRepository.save(answer);
    }

    @Transactional
    public Answer createAnswer(Application application, ApplicationPage3PutRequest request){
        Answer answer = answerJpaRepository.findByApplication(application).orElse(new Answer(application, request));
        answer.updatePartAnswers(request.getPartAnswer1(), request.getPartAnswer2(), request.getPartAnswer3());
        return answerJpaRepository.save(answer);
    }
}
