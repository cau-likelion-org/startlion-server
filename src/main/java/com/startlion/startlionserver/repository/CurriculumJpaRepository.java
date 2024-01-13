package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Curriculum;
import com.startlion.startlionserver.domain.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurriculumJpaRepository extends JpaRepository<Curriculum, Long> {
    Optional<Curriculum> findByPart(Part part); // Part를 기반으로 Curriculum을 조회

    default Curriculum findByPartOrThrow(Part part) {
        return findByPart(part).orElseThrow(() -> new IllegalArgumentException("해당하는 Curriculum이 없습니다."));
    }
}

