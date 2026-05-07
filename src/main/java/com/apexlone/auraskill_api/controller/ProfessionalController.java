package com.apexlone.auraskill_api.controller;

import com.apexlone.auraskill_api.dto.ProfessionalRequestDTO;
import com.apexlone.auraskill_api.dto.ProfessionalResponseDTO;
import com.apexlone.auraskill_api.service.ProfessionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professionals")
@RequiredArgsConstructor
public class ProfessionalController {

    private final ProfessionalService service;

    @GetMapping
    public List<ProfessionalResponseDTO> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessionalResponseDTO create(
            @RequestBody ProfessionalRequestDTO dto
    ) {
        return service.create(dto);
    }
}