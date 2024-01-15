package com.startlion.startlionserver.service;


import com.startlion.startlionserver.external.s3.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class InterviewFileUploadService {

    private final S3Service s3Service;
    private final GraduateInterviewService graduateInterviewService;

    public void uploadFile(MultipartFile file, Long interviewId) {
        try {
            val fileName = generateFileName();
            val imageUrl = s3Service.upload(fileName, file);
            graduateInterviewService.updateInterviewImageUrl(interviewId, imageUrl);
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException("파일 업로드에 실패했습니다.");
        }
    }

    private String generateFileName() {
        return UUID.randomUUID().toString();
    }
}