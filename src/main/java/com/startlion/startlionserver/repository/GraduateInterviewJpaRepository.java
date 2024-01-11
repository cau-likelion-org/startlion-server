package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.GraduateInterview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GraduateInterviewJpaRepository extends JpaRepository<GraduateInterview, Long> {
    List<GraduateInterview> findByPart(String part);
}
