package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Interview;
import com.startlion.startlionserver.domain.entity.InterviewAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewAnswerJpaRepository extends JpaRepository<InterviewAnswer, Long> {

    List<InterviewAnswer> findByInterview(Interview interview);
}
