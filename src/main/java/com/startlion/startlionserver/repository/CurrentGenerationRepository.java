package com.startlion.startlionserver.repository;


import com.startlion.startlionserver.domain.entity.CurrentGeneration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentGenerationRepository extends JpaRepository<CurrentGeneration, Long> {
    default CurrentGeneration findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 CurrentGeneration이 없습니다."));
    }
}
