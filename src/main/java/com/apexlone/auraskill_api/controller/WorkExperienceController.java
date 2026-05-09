package com.apexlone.auraskill_api.controller;

import com.apexlone.auraskill_api.dto.WorkExperienceRequest;
import com.apexlone.auraskill_api.dto.WorkExperienceResponse;
import com.apexlone.auraskill_api.service.WorkExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/work-experiences")
@RequiredArgsConstructor
public class WorkExperienceController {

    private final WorkExperienceService service;

    @PostMapping
    public WorkExperienceResponse create(@RequestBody WorkExperienceRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<WorkExperienceResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public WorkExperienceResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public WorkExperienceResponse update(@PathVariable Long id,
                                         @RequestBody WorkExperienceRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}