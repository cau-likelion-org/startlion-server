package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.GraduateInterview;
import com.startlion.startlionserver.domain.enums.IntervieweePart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewJpaRepository extends JpaRepository<GraduateInterview, Long> {
    List<GraduateInterview> findAllByPart(IntervieweePart part);
}
