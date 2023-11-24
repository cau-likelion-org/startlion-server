package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.response.part.PartResponse;
import com.startlion.startlionserver.service.PartService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parts")
public class PartController {

    private final PartService partService;

    @GetMapping("{partId}")
    public ResponseEntity<PartResponse> getPart(@PathVariable Long partId
    ) {
        val response = partService.getPartById(partId);
        return ResponseEntity.ok(response);
    }
}