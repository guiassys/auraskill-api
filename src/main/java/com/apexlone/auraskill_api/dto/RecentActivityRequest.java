package com.apexlone.auraskill_api.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class RecentActivityRequest {

    private Long professionalId;
    private String activityType;
    private String activityDescription;
    private OffsetDateTime activityDate;
}