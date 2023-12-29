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
        Curriculum curriculum = curriculumJpaRepository.findByPartId(part)
                .orElseThrow(() -> new NoSuchElementException("해당 파트의 커리큘럼이 없습니다."));
        List<PartQuestion> partQuestions = partQuestionJpaRepository.findByPart(part);
        //List<Curriculum> curriculums = curriculumJpaRepository.findByPartId(part);

        if (partQuestions.isEmpty()) {
            throw new NoSuchElementException("PartQuestion이 비어 있습니다.");
        }
        // 임시로 이렇게 구현했습니다. 후에 파트에 generation을 추가하거나 해야할 듯 합니다.
        Long generation = partQuestions.isEmpty() ? curriculum.getGeneration() : partQuestions.get(0).getGeneration();
        CommonQuestion commonQuestion = commonQuestionJpaRepository.findByGeneration(generation)
                .orElseThrow(() -> new NoSuchElementException("해당 세대의 CommonQuestion이 없습니다."));


        return PartResponse.of(part, partQuestions, curriculum, commonQuestion);
    }
}