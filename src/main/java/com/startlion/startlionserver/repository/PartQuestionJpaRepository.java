package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Part;
import com.startlion.startlionserver.domain.entity.PartQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartQuestionJpaRepository extends JpaRepository<PartQuestion, Long> {
    List<PartQuestion> findByPart(Part part);
}
