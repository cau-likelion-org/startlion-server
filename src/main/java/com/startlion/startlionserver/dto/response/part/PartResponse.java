package com.startlion.startlionserver.dto.response.part;

import com.startlion.startlionserver.domain.entity.Part;
import lombok.Data;


public record PartResponse(
        String partContent,
        String typeOfTalent,
        String imageUrl
) {

    public static PartResponse of(Part part) {
        return new PartResponse(
                part.getPartContent(),
                part.getTypeOfTalent(),
                part.getImageUrl() // 이미지 URL 추가
        );
    }
}
