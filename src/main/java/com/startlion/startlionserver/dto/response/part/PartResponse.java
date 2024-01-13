package com.startlion.startlionserver.dto.response.part;

import com.startlion.startlionserver.domain.entity.CommonQuestion;
import com.startlion.startlionserver.domain.entity.Curriculum;
import com.startlion.startlionserver.domain.entity.Part;
import com.startlion.startlionserver.domain.entity.PartQuestion;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;


@Builder
@Schema(description = "파트 상세 페이지 데이터")
public record PartResponse(

        @Schema(description = "파트 이름", example = "기획")
        String part,
        @Schema(description = "파트 내용", example = "기획 파트는 기획을 합니다.")
        String partContent,
        @Schema(description = "파트 타입", example = "기획")
        String typeOfTalent,
        @Schema(description = "파트 이미지 url", example = "https://startlion.s3.ap-northeast-2.amazonaws.com/part/plan.png")
        String imageUrl,
        @Schema(description = "커리큘럼 내용", example = "기획 파트는 기획을 합니다.")
        String curriculumContents,
        @Schema(description = "파트 기수", example = "12")
        Long curriculumGeneration,
        List<String> commonQuestions,
        List<String> partQuestions
) {

    public static PartResponse of(Part part,
                                  PartQuestion partQuestion,
                                  Curriculum curriculum,
                                  CommonQuestion commonQuestion
                                  ) {
        return new PartResponse(
                part.getName(),
                part.getPartContent(),
                part.getTypeOfTalent(),
                part.getImageUrl(),
                curriculum.getContent(),
                part.getGeneration(),
                List.of(
                        commonQuestion.getCommonQuestion1(),
                        commonQuestion.getCommonQuestion2(),
                        commonQuestion.getCommonQuestion3(),
                        commonQuestion.getCommonQuestion4(),
                        commonQuestion.getCommonQuestion5()),
                List.of(
                        partQuestion.getPartQuestion1(),
                        partQuestion.getPartQuestion2(),
                        partQuestion.getPartQuestion3(),
                        partQuestion.getPartQuestion4())
        );
    }
}
