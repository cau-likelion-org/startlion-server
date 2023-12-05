package com.startlion.startlionserver.dto.response.part;

import com.startlion.startlionserver.domain.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PartIdResponse {
    private Long partId;

    public PartIdResponse(Part part){
        this.partId = part.getPartId();
    }
}
