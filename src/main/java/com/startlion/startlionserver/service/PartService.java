package com.startlion.startlionserver.service;


import com.startlion.startlionserver.domain.entity.CommonQuestion;
import com.startlion.startlionserver.domain.entity.Curriculum;
import com.startlion.startlionserver.domain.entity.Part;
import com.startlion.startlionserver.domain.entity.PartQuestion;
import com.startlion.startlionserver.dto.response.part.PartResponse;
import com.startlion.startlionserver.repository.CommonQuestionJpaRepository;
import com.startlion.startlionserver.repository.CurriculumJpaRepository;
import com.startlion.startlionserver.repository.PartJpaRepository;
import com.startlion.startlionserver.repository.PartQuestionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartService {

    private final PartJpaRepository partJpaRepository;
    private final PartQuestionJpaRepository partQuestionJpaRepository;
    private final CurriculumJpaRepository curriculumJpaRepository;
    private final CommonQuestionJpaRepository commonQuestionJpaRepository;

    public PartResponse getPartByName(String name) {
        Part part = partJpaRepository.findByName(name)
                .orElseThrow( () -> new IllegalArgumentException("해당하는 파트가 없습니다."));
        List<PartQuestion> partQuestions = partQuestionJpaRepository.findByPart(part);
        List<Curriculum> curriculums = curriculumJpaRepository.findByPartId(part);

        if (partQuestions.isEmpty() && curriculums.isEmpty()) {
            throw new NoSuchElementException("PartQuestion과 Curriculum이 모두 비어 있습니다.");
        }

        Long generation;
        if (partQuestions.isEmpty()) {
            // partQuestions가 비어있을 경우 curriculums에서 generation 정보 가져오기
            generation = curriculums.get(0).getGeneration();
        } else {
            // partQuestions에서 generation 정보 가져오기
            generation = partQuestions.get(0).getGeneration();
        }

        // 해당 세대의 CommonQuestion 가져오기
        CommonQuestion commonQuestion = commonQuestionJpaRepository.findByGeneration(generation)
                .orElseThrow(() -> new NoSuchElementException("해당 기수의 CommonQuestion이 없습니다."));

        return PartResponse.of(part, partQuestions, curriculums, commonQuestion);
    }
}
