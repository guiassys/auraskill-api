package com.apexlone.auraskill_api.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProfessionalSkillId implements Serializable {

    private Long professionalId;
    private Long skillId;
}