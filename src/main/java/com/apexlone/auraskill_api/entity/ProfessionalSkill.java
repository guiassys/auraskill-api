package com.apexlone.auraskill_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "professional_skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessionalSkill {

    @EmbeddedId
    private ProfessionalSkillId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("professionalId")
    @JoinColumn(name = "professional_id")
    private Professional professional;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @Column(name = "proficiency_level")
    private String proficiencyLevel;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
    }
}