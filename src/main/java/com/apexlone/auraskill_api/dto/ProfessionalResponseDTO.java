package com.apexlone.auraskill_api.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
public record ProfessionalResponseDTO(
        Long id,
        UUID keycloakUserId,
        String fullName,
        String email,
        LocalDate dateOfBirth,
        String phone,
        String address,
        String bio,
        String profilePictureUrl,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}