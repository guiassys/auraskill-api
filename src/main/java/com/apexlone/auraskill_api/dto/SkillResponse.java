package com.apexlone.auraskill_api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class SkillResponse {

    private Long id;
    private String name;
    private String skillType;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}