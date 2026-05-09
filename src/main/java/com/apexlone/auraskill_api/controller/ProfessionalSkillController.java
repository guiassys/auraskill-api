package com.apexlone.auraskill_api.controller;

import com.apexlone.auraskill_api.dto.ProfessionalSkillRequest;
import com.apexlone.auraskill_api.dto.ProfessionalSkillResponse;
import com.apexlone.auraskill_api.entity.ProfessionalSkillId;
import com.apexlone.auraskill_api.service.ProfessionalSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professional-skills")
@RequiredArgsConstructor
public class ProfessionalSkillController {

    private final ProfessionalSkillService service;

    @PostMapping
    public ProfessionalSkillResponse create(@RequestBody ProfessionalSkillRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ProfessionalSkillResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{professionalId}/{skillId}")
    public ProfessionalSkillResponse findById(@PathVariable Long professionalId, @PathVariable Long skillId) {
        return service.findById(new ProfessionalSkillId(professionalId, skillId));
    }

    @PutMapping("/{professionalId}/{skillId}")
    public ProfessionalSkillResponse update(@PathVariable Long professionalId, @PathVariable Long skillId,
                                            @RequestBody ProfessionalSkillRequest request) {
        return service.update(new ProfessionalSkillId(professionalId, skillId), request);
    }

    @DeleteMapping("/{professionalId}/{skillId}")
    public void delete(@PathVariable Long professionalId, @PathVariable Long skillId) {
        service.delete(new ProfessionalSkillId(professionalId, skillId));
    }
}