package com.apexlone.auraskill_api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AchievementRequest {

    private Long professionalId;
    private String achievementName;
    private String achievementDescription;
    private LocalDate achievementDate;
    private String organizer;
}