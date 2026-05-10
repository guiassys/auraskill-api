package com.apexlone.auraskill_api.repository;

import com.apexlone.auraskill_api.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}