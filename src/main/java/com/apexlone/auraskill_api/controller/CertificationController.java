package com.apexlone.auraskill_api.controller;

import com.apexlone.auraskill_api.dto.CertificationRequest;
import com.apexlone.auraskill_api.dto.CertificationResponse;
import com.apexlone.auraskill_api.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/certifications")
@RequiredArgsConstructor
public class CertificationController {

    private final CertificationService service;

    @PostMapping
    public CertificationResponse create(@RequestBody CertificationRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<CertificationResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CertificationResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public CertificationResponse update(@PathVariable Long id,
                                        @RequestBody CertificationRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}