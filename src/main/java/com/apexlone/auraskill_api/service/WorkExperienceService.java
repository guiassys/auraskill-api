package com.apexlone.auraskill_api.service;

import com.apexlone.auraskill_api.dto.WorkExperienceRequest;
import com.apexlone.auraskill_api.dto.WorkExperienceResponse;
import com.apexlone.auraskill_api.entity.Professional;
import com.apexlone.auraskill_api.entity.WorkExperience;
import com.apexlone.auraskill_api.repository.ProfessionalRepository;
import com.apexlone.auraskill_api.repository.WorkExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperienceService {

    private final WorkExperienceRepository repository;
    private final ProfessionalRepository professionalRepository;

    public WorkExperienceResponse create(WorkExperienceRequest request) {
        Professional professional = professionalRepository.findById(request.getProfessionalId())
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        WorkExperience workExperience = WorkExperience.builder()
                .professional(professional)
                .company(request.getCompany())
                .position(request.getPosition())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .activitiesDescription(request.getActivitiesDescription())
                .projectHighlight(request.getProjectHighlight())
                .build();

        return toResponse(repository.save(workExperience));
    }

    public List<WorkExperienceResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public WorkExperienceResponse findById(Long id) {
        WorkExperience workExperience = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("WorkExperience not found"));

        return toResponse(workExperience);
    }

    public WorkExperienceResponse update(Long id, WorkExperienceRequest request) {
        WorkExperience workExperience = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("WorkExperience not found"));

        workExperience.setCompany(request.getCompany());
        workExperience.setPosition(request.getPosition());
        workExperience.setStartDate(request.getStartDate());
        workExperience.setEndDate(request.getEndDate());
        workExperience.setActivitiesDescription(request.getActivitiesDescription());
        workExperience.setProjectHighlight(request.getProjectHighlight());

        return toResponse(repository.save(workExperience));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private WorkExperienceResponse toResponse(WorkExperience we) {
        return WorkExperienceResponse.builder()
                .id(we.getId())
                .professionalId(we.getProfessional().getId())
                .company(we.getCompany())
                .position(we.getPosition())
                .startDate(we.getStartDate())
                .endDate(we.getEndDate())
                .activitiesDescription(we.getActivitiesDescription())
                .projectHighlight(we.getProjectHighlight())
                .createdAt(we.getCreatedAt())
                .updatedAt(we.getUpdatedAt())
                .build();
    }
}