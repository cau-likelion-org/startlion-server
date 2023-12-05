package com.startlion.startlionserver.dto.response.partQuestion;

import com.startlion.startlionserver.domain.entity.Part;
import com.startlion.startlionserver.domain.entity.PartQuestion;
import com.startlion.startlionserver.dto.response.part.PartIdResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PartQuestionResponse {
    private Long questionId;

    private PartIdResponse partId;

    private Long generation;

    private String partQuestion1;

    private String partQuestion2;

    private String partQuestion3;

    public PartQuestionResponse(PartQuestion partQuestion) {
        this.questionId = partQuestion.getQuestionId();
        this.partId = new PartIdResponse(partQuestion.getPart());
        this.generation = partQuestion.getGeneration();
        this.partQuestion1 = partQuestion.getPartQuestion1();
        this.partQuestion2 = partQuestion.getPartQuestion2();
        this.partQuestion3 = partQuestion.getPartQuestion3();
    }
}
