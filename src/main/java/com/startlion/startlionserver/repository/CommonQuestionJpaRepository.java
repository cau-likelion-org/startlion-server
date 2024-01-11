package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.CommonQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommonQuestionJpaRepository extends JpaRepository<CommonQuestion, Long> {
    Optional<CommonQuestion> findByGeneration(int generation);

    default CommonQuestion findByGenerationOrThrow(int generation) {
        return findByGeneration(generation).orElseThrow(() -> new IllegalArgumentException("해당하는 CommonQuestion이 없습니다."));
    }
}
