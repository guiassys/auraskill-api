package com.apexlone.auraskill_api.controller;

import com.apexlone.auraskill_api.dto.AchievementRequest;
import com.apexlone.auraskill_api.dto.AchievementResponse;
import com.apexlone.auraskill_api.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/achievements")
@RequiredArgsConstructor
public class AchievementController {

    private final AchievementService service;

    @PostMapping
    public AchievementResponse create(@RequestBody AchievementRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<AchievementResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AchievementResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public AchievementResponse update(@PathVariable Long id,
                                      @RequestBody AchievementRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}