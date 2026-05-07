package com.apexlone.auraskill_api.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ProfessionalRequest {

    private UUID keycloakUserId;
    private String fullName;
    private String email;
    private LocalDate dateOfBirth;
    private String phone;
    private String address;
    private String bio;
    private String profilePictureUrl;
}