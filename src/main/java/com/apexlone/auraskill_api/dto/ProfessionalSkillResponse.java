package com.apexlone.auraskill_api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class ProfessionalSkillResponse {

    private Long professionalId;
    private Long skillId;
    private String proficiencyLevel;
    private Integer yearsOfExperience;
    private OffsetDateTime createdAt;
}