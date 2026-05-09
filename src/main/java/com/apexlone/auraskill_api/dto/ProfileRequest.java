package com.apexlone.auraskill_api.dto;

import lombok.Data;

@Data
public class ProfileRequest {

    private Long professionalId;
    private String professionalTitle;
    private String professionalObjective;
    private String professionalExperience;
    private String academicBackground;
    private String languages;
    private String technicalSkills;
    private String behavioralSkills;
}