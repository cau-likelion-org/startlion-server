package com.startlion.startlionserver.controller.swagger;


import com.startlion.startlionserver.dto.response.part.PartResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "[Part] 파트 관련 API")
public interface PartApi {

    @GetMapping("/{partName}")
    ResponseEntity<PartResponse> getPart(
            @Parameter(description = "파트 이름", example = "BE/FE/DESIGN/PLAN")
            @PathVariable String partName);
}
