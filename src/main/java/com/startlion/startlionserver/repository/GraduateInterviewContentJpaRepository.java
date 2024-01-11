package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.GraduateInterview;
import com.startlion.startlionserver.domain.entity.GraduateInterviewContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GraduateInterviewContentJpaRepository extends JpaRepository<GraduateInterviewContent, Long> {

    List<GraduateInterviewContent> findByGraduateInterview(GraduateInterview graduateInterview);

}
