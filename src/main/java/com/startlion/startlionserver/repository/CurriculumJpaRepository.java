package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Curriculum;
import com.startlion.startlionserver.domain.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CurriculumJpaRepository extends JpaRepository<Curriculum, Long> {
    List<Curriculum> findByPartId(Part part); // Part를 기반으로 Curriculum을 조회
}
