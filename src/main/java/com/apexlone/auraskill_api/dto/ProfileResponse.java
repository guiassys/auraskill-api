package com.apexlone.auraskill_api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class ProfileResponse {

    private Long id;
    private Long professionalId;
    private String professionalTitle;
    private String professionalObjective;
    private String professionalExperience;
    private String academicBackground;
    private String languages;
    private String technicalSkills;
    private String behavioralSkills;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}