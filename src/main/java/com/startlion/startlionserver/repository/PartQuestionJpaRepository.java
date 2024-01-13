package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.PartQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartQuestionJpaRepository extends JpaRepository<PartQuestion, Long> {

    Optional<PartQuestion> findByPartAndGeneration(String part, int generation);
    default PartQuestion findByPartAndGenerationOrThrow(String part, int generation) {
        return findByPartAndGeneration(part, generation).orElseThrow(() -> new IllegalArgumentException("해당하는 PartQuestion이 없습니다."));

    }
}
