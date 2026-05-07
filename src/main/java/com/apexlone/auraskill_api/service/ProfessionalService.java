package com.apexlone.auraskill_api.service;

import com.apexlone.auraskill_api.dto.ProfessionalRequestDTO;
import com.apexlone.auraskill_api.dto.ProfessionalResponseDTO;
import com.apexlone.auraskill_api.entity.Professional;
import com.apexlone.auraskill_api.repository.ProfessionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessionalService {

    private final ProfessionalRepository repository;

    public List<ProfessionalResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProfessionalResponseDTO create(ProfessionalRequestDTO dto) {

        Professional professional = Professional.builder()
                .keycloakUserId(UUID.randomUUID())
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .dateOfBirth(dto.getDateOfBirth())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .bio(dto.getBio())
                .profilePictureUrl(dto.getProfilePictureUrl())
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();

        Professional saved = repository.save(professional);

        return toResponse(saved);
    }

    private ProfessionalResponseDTO toResponse(Professional professional) {
        return ProfessionalResponseDTO.builder()
                .id(professional.getId())
                .keycloakUserId(professional.getKeycloakUserId())
                .fullName(professional.getFullName())
                .email(professional.getEmail())
                .dateOfBirth(professional.getDateOfBirth())
                .phone(professional.getPhone())
                .address(professional.getAddress())
                .bio(professional.getBio())
                .profilePictureUrl(professional.getProfilePictureUrl())
                .createdAt(professional.getCreatedAt())
                .updatedAt(professional.getUpdatedAt())
                .build();
    }
}