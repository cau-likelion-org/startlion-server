package com.startlion.startlionserver.service;


import com.startlion.startlionserver.domain.enums.ApplyPart;
import com.startlion.startlionserver.dto.response.part.PartResponse;
import com.startlion.startlionserver.repository.CommonQuestionJpaRepository;
import com.startlion.startlionserver.repository.CurriculumJpaRepository;
import com.startlion.startlionserver.repository.PartJpaRepository;
import com.startlion.startlionserver.repository.PartQuestionJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartService {

    private final PartJpaRepository partJpaRepository;
    private final PartQuestionJpaRepository partQuestionJpaRepository;
    private final CommonQuestionJpaRepository commonQuestionJpaRepository;
    private final CurriculumJpaRepository curriculumJpaRepository;

    @Value("${current-generation}")
    private int currentGeneration;

    public PartResponse getPartByName(String name) {
        val part = partJpaRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("해당하는 파트가 없습니다."));
        val partQuestion = partQuestionJpaRepository.findByPartAndGenerationOrThrow(ApplyPart.valueOf(name), currentGeneration);
        val commonQuestion = commonQuestionJpaRepository.findByGeneration(currentGeneration).orElseThrow(() -> new EntityNotFoundException("해당하는 공통 질문이 없습니다."));
        val curriculum = curriculumJpaRepository.findByPartOrThrow(part);
    return PartResponse.of(part, partQuestion, curriculum, commonQuestion);

    }
}

