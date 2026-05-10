package com.apexlone.auraskill_api.controller;

import com.apexlone.auraskill_api.dto.SkillRequest;
import com.apexlone.auraskill_api.dto.SkillResponse;
import com.apexlone.auraskill_api.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService service;

    @PostMapping
    public SkillResponse create(@RequestBody SkillRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<SkillResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public SkillResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public SkillResponse update(@PathVariable Long id,
                                  @RequestBody SkillRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}