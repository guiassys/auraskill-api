package com.apexlone.auraskill_api.repository;

import com.apexlone.auraskill_api.entity.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

    Optional<Professional> findByKeycloakUserId(UUID keycloakUserId);

    Optional<Professional> findByEmail(String email);
}