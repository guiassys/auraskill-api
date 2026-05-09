package com.apexlone.auraskill_api.repository;

import com.apexlone.auraskill_api.entity.RecentActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecentActivityRepository extends JpaRepository<RecentActivity, Long> {
}