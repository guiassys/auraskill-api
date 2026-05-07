package com.apexlone.auraskill_api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class ProfessionalResponse {

    private Long id;
    private UUID keycloakUserId;
    private String fullName;
    private String email;
    private LocalDate dateOfBirth;
    private String phone;
    private String address;
    private String bio;
    private String profilePictureUrl;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}