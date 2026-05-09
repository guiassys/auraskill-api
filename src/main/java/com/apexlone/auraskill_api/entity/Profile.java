package com.apexlone.auraskill_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professional_id", referencedColumnName = "id", nullable = false, unique = true)
    private Professional professional;

    @Column(name = "professional_title")
    private String professionalTitle;

    @Column(name = "professional_objective", columnDefinition = "TEXT")
    private String professionalObjective;

    @Column(name = "professional_experience", columnDefinition = "TEXT")
    private String professionalExperience;

    @Column(name = "academic_background", columnDefinition = "TEXT")
    private String academicBackground;

    @Column(columnDefinition = "TEXT")
    private String languages;

    @Column(name = "technical_skills", columnDefinition = "TEXT")
    private String technicalSkills;

    @Column(name = "behavioral_skills", columnDefinition = "TEXT")
    private String behavioralSkills;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}