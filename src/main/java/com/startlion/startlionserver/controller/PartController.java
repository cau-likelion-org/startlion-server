package com.startlion.startlionserver.controller;


import com.startlion.startlionserver.controller.swagger.PartApi;
import com.startlion.startlionserver.dto.response.part.PartResponse;
import com.startlion.startlionserver.service.PartService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/part")
@RequiredArgsConstructor
public class PartController implements PartApi {

    private final PartService partService;

    @GetMapping("/{partName}")
    public ResponseEntity<PartResponse> getPart(@PathVariable String partName) {
        val response = partService.getPartByName(partName);
        return ResponseEntity.ok((response));
    }
}
