package com.apexlone.auraskill_api.service;

import com.apexlone.auraskill_api.dto.ProfileRequest;
import com.apexlone.auraskill_api.dto.ProfileResponse;
import com.apexlone.auraskill_api.entity.Profile;
import com.apexlone.auraskill_api.entity.Professional;
import com.apexlone.auraskill_api.repository.ProfileRepository;
import com.apexlone.auraskill_api.repository.ProfessionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository repository;
    private final ProfessionalRepository professionalRepository;

    public ProfileResponse create(ProfileRequest request) {
        Professional professional = professionalRepository.findById(request.getProfessionalId())
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        Profile profile = Profile.builder()
                .professional(professional)
                .professionalTitle(request.getProfessionalTitle())
                .professionalObjective(request.getProfessionalObjective())
                .professionalExperience(request.getProfessionalExperience())
                .academicBackground(request.getAcademicBackground())
                .languages(request.getLanguages())
                .technicalSkills(request.getTechnicalSkills())
                .behavioralSkills(request.getBehavioralSkills())
                .build();

        return toResponse(repository.save(profile));
    }

    public List<ProfileResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProfileResponse findById(Long id) {
        Profile profile = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return toResponse(profile);
    }

    public ProfileResponse update(Long id, ProfileRequest request) {
        Profile profile = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setProfessionalTitle(request.getProfessionalTitle());
        profile.setProfessionalObjective(request.getProfessionalObjective());
        profile.setProfessionalExperience(request.getProfessionalExperience());
        profile.setAcademicBackground(request.getAcademicBackground());
        profile.setLanguages(request.getLanguages());
        profile.setTechnicalSkills(request.getTechnicalSkills());
        profile.setBehavioralSkills(request.getBehavioralSkills());

        return toResponse(repository.save(profile));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ProfileResponse toResponse(Profile p) {
        return ProfileResponse.builder()
                .id(p.getId())
                .professionalId(p.getProfessional().getId())
                .professionalTitle(p.getProfessionalTitle())
                .professionalObjective(p.getProfessionalObjective())
                .professionalExperience(p.getProfessionalExperience())
                .academicBackground(p.getAcademicBackground())
                .languages(p.getLanguages())
                .technicalSkills(p.getTechnicalSkills())
                .behavioralSkills(p.getBehavioralSkills())
                .createdAt(p.getCreatedAt())
                .updatedAt(p.getUpdatedAt())
                .build();
    }
}