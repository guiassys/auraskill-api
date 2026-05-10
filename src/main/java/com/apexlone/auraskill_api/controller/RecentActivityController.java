package com.apexlone.auraskill_api.controller;

import com.apexlone.auraskill_api.dto.RecentActivityRequest;
import com.apexlone.auraskill_api.dto.RecentActivityResponse;
import com.apexlone.auraskill_api.service.RecentActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recent-activities")
@RequiredArgsConstructor
public class RecentActivityController {

    private final RecentActivityService service;

    @PostMapping
    public RecentActivityResponse create(@RequestBody RecentActivityRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<RecentActivityResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RecentActivityResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public RecentActivityResponse update(@PathVariable Long id,
                                         @RequestBody RecentActivityRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}