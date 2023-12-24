package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Curriculum;
import com.startlion.startlionserver.domain.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurriculumJpaRepository extends JpaRepository<Curriculum, Long> {
    List<Curriculum> findByPartId(Part part);
}

