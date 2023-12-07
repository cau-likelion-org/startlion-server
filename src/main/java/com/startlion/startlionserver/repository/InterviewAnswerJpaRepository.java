package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Interview;
import com.startlion.startlionserver.domain.entity.InterviewContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewAnswerJpaRepository extends JpaRepository<InterviewContent, Long> {

    List<InterviewContent> findByInterview(Interview interview);
}
