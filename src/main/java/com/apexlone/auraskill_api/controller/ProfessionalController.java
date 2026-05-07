package com.apexlone.auraskill_api.controller;

import com.apexlone.auraskill_api.dto.ProfessionalRequest;
import com.apexlone.auraskill_api.dto.ProfessionalResponse;
import com.apexlone.auraskill_api.service.ProfessionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professionals")
@RequiredArgsConstructor
public class ProfessionalController {

    private final ProfessionalService service;

    @PostMapping
    public ProfessionalResponse create(@RequestBody ProfessionalRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ProfessionalResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ProfessionalResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ProfessionalResponse update(@PathVariable Long id,
                                       @RequestBody ProfessionalRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}