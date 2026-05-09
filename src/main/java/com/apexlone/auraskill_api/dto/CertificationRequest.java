package com.apexlone.auraskill_api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CertificationRequest {

    private Long professionalId;
    private String certificationName;
    private String institution;
    private LocalDate issueDate;
    private String description;
}