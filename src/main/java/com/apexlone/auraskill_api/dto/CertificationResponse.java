package com.apexlone.auraskill_api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Builder
public class CertificationResponse {

    private Long id;
    private Long professionalId;
    private String certificationName;
    private String institution;
    private LocalDate issueDate;
    private String description;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}