package com.apexlone.auraskill_api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Builder
public class WorkExperienceResponse {

    private Long id;
    private Long professionalId;
    private String company;
    private String position;
    private LocalDate startDate;
    private LocalDate endDate;
    private String activitiesDescription;
    private String projectHighlight;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}