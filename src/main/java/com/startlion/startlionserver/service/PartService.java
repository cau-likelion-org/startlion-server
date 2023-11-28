package com.startlion.startlionserver.service;


import com.startlion.startlionserver.domain.entity.Part;
import com.startlion.startlionserver.dto.response.part.PartResponse;
import com.startlion.startlionserver.repository.PartJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartService {

    private final PartJpaRepository partJpaRepository;

    public PartResponse getPartById(Long partId) {
        Part part = partJpaRepository.findById(partId)
                .orElseThrow( () -> new IllegalArgumentException("해당하는 파트가 없습니다."));
        return PartResponse.of(part);
    }
}
