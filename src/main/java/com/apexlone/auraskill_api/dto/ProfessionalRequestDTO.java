package com.apexlone.auraskill_api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfessionalRequestDTO {

    private String fullName;

    private String email;

    private LocalDate dateOfBirth;

    private String phone;

    private String address;

    private String bio;

    private String profilePictureUrl;
}