package com.apexlone.auraskill_api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WorkExperienceRequest {

    private Long professionalId;
    private String company;
    private String position;
    private LocalDate startDate;
    private LocalDate endDate;
    private String activitiesDescription;
    private String projectHighlight;
}