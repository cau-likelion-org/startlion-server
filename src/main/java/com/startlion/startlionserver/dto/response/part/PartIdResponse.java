package com.startlion.startlionserver.dto.response.part;

import com.startlion.startlionserver.domain.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PartIdResponse {
    private String partName;

    public PartIdResponse(Part part){
        this.partName = part.getKoreanName();
    }
}
