package com.apexlone.auraskill_api.service;

import com.apexlone.auraskill_api.dto.ProfessionalRequest;
import com.apexlone.auraskill_api.dto.ProfessionalResponse;
import com.apexlone.auraskill_api.entity.Professional;
import com.apexlone.auraskill_api.repository.ProfessionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessionalService {

    private final ProfessionalRepository repository;

    public ProfessionalResponse create(ProfessionalRequest request) {
        Professional professional = Professional.builder()
                .keycloakUserId(request.getKeycloakUserId())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .phone(request.getPhone())
                .address(request.getAddress())
                .bio(request.getBio())
                .profilePictureUrl(request.getProfilePictureUrl())
                .build();

        return toResponse(repository.save(professional));
    }

    public List<ProfessionalResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProfessionalResponse findById(Long id) {
        Professional professional = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        return toResponse(professional);
    }

    public ProfessionalResponse update(Long id, ProfessionalRequest request) {
        Professional professional = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        professional.setFullName(request.getFullName());
        professional.setEmail(request.getEmail());
        professional.setDateOfBirth(request.getDateOfBirth());
        professional.setPhone(request.getPhone());
        professional.setAddress(request.getAddress());
        professional.setBio(request.getBio());
        professional.setProfilePictureUrl(request.getProfilePictureUrl());

        return toResponse(repository.save(professional));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ProfessionalResponse toResponse(Professional p) {
        return ProfessionalResponse.builder()
                .id(p.getId())
                .keycloakUserId(p.getKeycloakUserId())
                .fullName(p.getFullName())
                .email(p.getEmail())
                .dateOfBirth(p.getDateOfBirth())
                .phone(p.getPhone())
                .address(p.getAddress())
                .bio(p.getBio())
                .profilePictureUrl(p.getProfilePictureUrl())
                .createdAt(p.getCreatedAt())
                .updatedAt(p.getUpdatedAt())
                .build();
    }
}