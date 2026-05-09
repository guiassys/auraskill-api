package com.apexlone.auraskill_api.repository;

import com.apexlone.auraskill_api.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}