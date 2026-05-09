package com.apexlone.auraskill_api.controller;

import com.apexlone.auraskill_api.dto.ProjectRequest;
import com.apexlone.auraskill_api.dto.ProjectResponse;
import com.apexlone.auraskill_api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @PostMapping
    public ProjectResponse create(@RequestBody ProjectRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ProjectResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ProjectResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ProjectResponse update(@PathVariable Long id,
                                  @RequestBody ProjectRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}