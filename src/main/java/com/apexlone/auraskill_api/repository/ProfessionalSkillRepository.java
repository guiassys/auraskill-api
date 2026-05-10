package com.apexlone.auraskill_api.repository;

import com.apexlone.auraskill_api.entity.ProfessionalSkill;
import com.apexlone.auraskill_api.entity.ProfessionalSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalSkillRepository extends JpaRepository<ProfessionalSkill, ProfessionalSkillId> {
}