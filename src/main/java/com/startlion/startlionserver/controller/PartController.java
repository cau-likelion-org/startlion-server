package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.response.part.PartResponse;
import com.startlion.startlionserver.service.PartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parts")
@Tag(name = "[Part] 파트 관련 API")
public class PartController {

    private final PartService partService;

    @Operation(summary = "part 정보 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "찾을 수 없음"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            }
    )
    @GetMapping("/{name}")
    public ResponseEntity<PartResponse> getPart(@PathVariable String name) {
        val response = partService.getPartByName(name);
        return ResponseEntity.ok(response);
    }
}
