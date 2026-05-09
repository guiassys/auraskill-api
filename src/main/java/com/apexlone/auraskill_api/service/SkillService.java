package com.apexlone.auraskill_api.service;

import com.apexlone.auraskill_api.dto.SkillRequest;
import com.apexlone.auraskill_api.dto.SkillResponse;
import com.apexlone.auraskill_api.entity.Skill;
import com.apexlone.auraskill_api.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository repository;

    public SkillResponse create(SkillRequest request) {
        Skill skill = Skill.builder()
                .name(request.getName())
                .skillType(request.getSkillType())
                .build();

        return toResponse(repository.save(skill));
    }

    public List<SkillResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public SkillResponse findById(Long id) {
        Skill skill = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        return toResponse(skill);
    }

    public SkillResponse update(Long id, SkillRequest request) {
        Skill skill = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        skill.setName(request.getName());
        skill.setSkillType(request.getSkillType());

        return toResponse(repository.save(skill));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private SkillResponse toResponse(Skill s) {
        return SkillResponse.builder()
                .id(s.getId())
                .name(s.getName())
                .skillType(s.getSkillType())
                .createdAt(s.getCreatedAt())
                .updatedAt(s.getUpdatedAt())
                .build();
    }
}