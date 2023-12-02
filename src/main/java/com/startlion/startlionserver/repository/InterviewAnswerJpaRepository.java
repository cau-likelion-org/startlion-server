package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Interview;
import com.startlion.startlionserver.domain.entity.InterviewAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface InterviewAnswerJpaRepository extends JpaRepository<InterviewAnswer, Long> {

    List<InterviewAnswer> findByInterview(Interview interview);
}
