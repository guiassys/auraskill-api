package com.apexlone.auraskill_api.repository;

import com.apexlone.auraskill_api.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
}