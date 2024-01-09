package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.response.part.PartResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "[Part] 파트 관련 API")
public interface PartApi {

    @Operation(summary = "part 정보 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "찾을 수 없음"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            }
    )
    ResponseEntity<PartResponse> getPart(@PathVariable String name);


}
