package com.apexlone.auraskill_api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Builder
public class AchievementResponse {

    private Long id;
    private Long professionalId;
    private String achievementName;
    private String achievementDescription;
    private LocalDate achievementDate;
    private String organizer;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}