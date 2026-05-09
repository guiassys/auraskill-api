package com.apexlone.auraskill_api.repository;

import com.apexlone.auraskill_api.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}