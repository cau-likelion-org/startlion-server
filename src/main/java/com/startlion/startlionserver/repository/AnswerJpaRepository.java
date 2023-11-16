package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Answer;
import com.startlion.startlionserver.domain.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerJpaRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByApplication(Application application);
}
