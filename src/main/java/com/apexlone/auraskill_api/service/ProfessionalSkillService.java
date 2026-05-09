package com.apexlone.auraskill_api.service;

import com.apexlone.auraskill_api.dto.ProfessionalSkillRequest;
import com.apexlone.auraskill_api.dto.ProfessionalSkillResponse;
import com.apexlone.auraskill_api.entity.Professional;
import com.apexlone.auraskill_api.entity.ProfessionalSkill;
import com.apexlone.auraskill_api.entity.ProfessionalSkillId;
import com.apexlone.auraskill_api.entity.Skill;
import com.apexlone.auraskill_api.repository.ProfessionalRepository;
import com.apexlone.auraskill_api.repository.ProfessionalSkillRepository;
import com.apexlone.auraskill_api.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessionalSkillService {

    private final ProfessionalSkillRepository repository;
    private final ProfessionalRepository professionalRepository;
    private final SkillRepository skillRepository;

    public ProfessionalSkillResponse create(ProfessionalSkillRequest request) {
        Professional professional = professionalRepository.findById(request.getProfessionalId())
                .orElseThrow(() -> new RuntimeException("Professional not found"));
        Skill skill = skillRepository.findById(request.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        ProfessionalSkillId id = new ProfessionalSkillId(professional.getId(), skill.getId());

        ProfessionalSkill professionalSkill = ProfessionalSkill.builder()
                .id(id)
                .professional(professional)
                .skill(skill)
                .proficiencyLevel(request.getProficiencyLevel())
                .yearsOfExperience(request.getYearsOfExperience())
                .build();

        return toResponse(repository.save(professionalSkill));
    }

    public List<ProfessionalSkillResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProfessionalSkillResponse findById(ProfessionalSkillId id) {
        ProfessionalSkill professionalSkill = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProfessionalSkill not found"));

        return toResponse(professionalSkill);
    }

    public ProfessionalSkillResponse update(ProfessionalSkillId id, ProfessionalSkillRequest request) {
        ProfessionalSkill professionalSkill = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProfessionalSkill not found"));

        professionalSkill.setProficiencyLevel(request.getProficiencyLevel());
        professionalSkill.setYearsOfExperience(request.getYearsOfExperience());

        return toResponse(repository.save(professionalSkill));
    }

    public void delete(ProfessionalSkillId id) {
        repository.deleteById(id);
    }

    private ProfessionalSkillResponse toResponse(ProfessionalSkill ps) {
        return ProfessionalSkillResponse.builder()
                .professionalId(ps.getProfessional().getId())
                .skillId(ps.getSkill().getId())
                .proficiencyLevel(ps.getProficiencyLevel())
                .yearsOfExperience(ps.getYearsOfExperience())
                .createdAt(ps.getCreatedAt())
                .build();
    }
}