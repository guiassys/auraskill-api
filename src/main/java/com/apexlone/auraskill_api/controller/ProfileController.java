package com.apexlone.auraskill_api.controller;

import com.apexlone.auraskill_api.dto.ProfileRequest;
import com.apexlone.auraskill_api.dto.ProfileResponse;
import com.apexlone.auraskill_api.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService service;

    @PostMapping
    public ProfileResponse create(@RequestBody ProfileRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ProfileResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ProfileResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ProfileResponse update(@PathVariable Long id,
                                  @RequestBody ProfileRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}