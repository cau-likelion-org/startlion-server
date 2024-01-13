package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartJpaRepository extends JpaRepository<Part, Long> {
    Optional<Part> findByName(String name);
    Optional<Part> findByKoreanName(String koreanName);

    default Part findByPartNameOrThrow(String name) {
        return findByName(name).orElseThrow(() -> new IllegalArgumentException("해당하는 Part가 없습니다."));
    }
}
