package com.apexlone.auraskill_api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class RecentActivityResponse {

    private Long id;
    private Long professionalId;
    private String activityType;
    private String activityDescription;
    private OffsetDateTime activityDate;
}