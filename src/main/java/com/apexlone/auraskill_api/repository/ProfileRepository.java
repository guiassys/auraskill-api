package com.apexlone.auraskill_api.repository;

import com.apexlone.auraskill_api.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}