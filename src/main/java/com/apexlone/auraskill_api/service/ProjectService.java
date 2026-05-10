package com.apexlone.auraskill_api.service;

import com.apexlone.auraskill_api.dto.ProjectRequest;
import com.apexlone.auraskill_api.dto.ProjectResponse;
import com.apexlone.auraskill_api.entity.Professional;
import com.apexlone.auraskill_api.entity.Project;
import com.apexlone.auraskill_api.repository.ProfessionalRepository;
import com.apexlone.auraskill_api.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository repository;
    private final ProfessionalRepository professionalRepository;

    public ProjectResponse create(ProjectRequest request) {
        Professional professional = professionalRepository.findById(request.getProfessionalId())
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        Project project = Project.builder()
                .professional(professional)
                .projectName(request.getProjectName())
                .projectDescription(request.getProjectDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .projectUrl(request.getProjectUrl())
                .build();

        return toResponse(repository.save(project));
    }

    public List<ProjectResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProjectResponse findById(Long id) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        return toResponse(project);
    }

    public ProjectResponse update(Long id, ProjectRequest request) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setProjectName(request.getProjectName());
        project.setProjectDescription(request.getProjectDescription());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setProjectUrl(request.getProjectUrl());

        return toResponse(repository.save(project));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ProjectResponse toResponse(Project p) {
        return ProjectResponse.builder()
                .id(p.getId())
                .professionalId(p.getProfessional().getId())
                .projectName(p.getProjectName())
                .projectDescription(p.getProjectDescription())
                .startDate(p.getStartDate())
                .endDate(p.getEndDate())
                .projectUrl(p.getProjectUrl())
                .createdAt(p.getCreatedAt())
                .updatedAt(p.getUpdatedAt())
                .build();
    }
}