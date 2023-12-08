package com.startlion.startlionserver.dto.response.part;

import com.startlion.startlionserver.domain.entity.CommonQuestion;
import com.startlion.startlionserver.domain.entity.Curriculum;
import com.startlion.startlionserver.domain.entity.Part;
import com.startlion.startlionserver.domain.entity.PartQuestion;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public record PartResponse(
        String partContent,
        String typeOfTalent,

        String imageUrl,

        List<String> partQuestions,
        List<String> curriculumContents,
        List<String> commonQuestions,
        Long curriculumGeneration
) {

//    public static PartResponse of(Part part) {
//        return new PartResponse(
//                part.getPartContent(),
//                part.getTypeOfTalent(),
//                part.getImageUrl()
//        );

    public static PartResponse of(Part part, List<PartQuestion> partQuestions, List<Curriculum> curriculums, CommonQuestion commonQuestion) {
        return new PartResponse(
                part.getPartContent(),
                part.getTypeOfTalent(),
                part.getImageUrl(),
                partQuestions.stream()
                        .map(partQuestion -> partQuestion.getPartQuestion1() + ", " + partQuestion.getPartQuestion2() + ", " + partQuestion.getPartQuestion3())
                        .collect(Collectors.toList()),
                curriculums.stream()
                        .map(Curriculum::getContent)
                        .collect(Collectors.toList()),
                Arrays.asList(
                        commonQuestion.getCommonQuestion1(),
                        commonQuestion.getCommonQuestion2(),
                        commonQuestion.getCommonQuestion3(),
                        commonQuestion.getCommonQuestion4(),
                        commonQuestion.getCommonQuestion5()
                ),
                part.getGeneration() // part의 Generation 정보
        );
    }
}
