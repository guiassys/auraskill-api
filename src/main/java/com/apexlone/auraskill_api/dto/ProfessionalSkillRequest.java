package com.apexlone.auraskill_api.dto;

import lombok.Data;

@Data
public class ProfessionalSkillRequest {

    private Long professionalId;
    private Long skillId;
    private String proficiencyLevel;
    private Integer yearsOfExperience;
}