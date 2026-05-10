package com.apexlone.auraskill_api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectRequest {

    private Long professionalId;
    private String projectName;
    private String projectDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private String projectUrl;
}