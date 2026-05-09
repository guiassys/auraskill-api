package com.apexlone.auraskill_api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Builder
public class ProjectResponse {

    private Long id;
    private Long professionalId;
    private String projectName;
    private String projectDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private String projectUrl;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}