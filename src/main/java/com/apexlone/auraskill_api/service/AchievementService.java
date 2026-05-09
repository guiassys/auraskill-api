package com.apexlone.auraskill_api.service;

import com.apexlone.auraskill_api.dto.AchievementRequest;
import com.apexlone.auraskill_api.dto.AchievementResponse;
import com.apexlone.auraskill_api.entity.Achievement;
import com.apexlone.auraskill_api.entity.Professional;
import com.apexlone.auraskill_api.repository.AchievementRepository;
import com.apexlone.auraskill_api.repository.ProfessionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementService {

    private final AchievementRepository repository;
    private final ProfessionalRepository professionalRepository;

    public AchievementResponse create(AchievementRequest request) {
        Professional professional = professionalRepository.findById(request.getProfessionalId())
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        Achievement achievement = Achievement.builder()
                .professional(professional)
                .achievementName(request.getAchievementName())
                .achievementDescription(request.getAchievementDescription())
                .achievementDate(request.getAchievementDate())
                .organizer(request.getOrganizer())
                .build();

        return toResponse(repository.save(achievement));
    }

    public List<AchievementResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public AchievementResponse findById(Long id) {
        Achievement achievement = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found"));

        return toResponse(achievement);
    }

    public AchievementResponse update(Long id, AchievementRequest request) {
        Achievement achievement = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found"));

        achievement.setAchievementName(request.getAchievementName());
        achievement.setAchievementDescription(request.getAchievementDescription());
        achievement.setAchievementDate(request.getAchievementDate());
        achievement.setOrganizer(request.getOrganizer());

        return toResponse(repository.save(achievement));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private AchievementResponse toResponse(Achievement a) {
        return AchievementResponse.builder()
                .id(a.getId())
                .professionalId(a.getProfessional().getId())
                .achievementName(a.getAchievementName())
                .achievementDescription(a.getAchievementDescription())
                .achievementDate(a.getAchievementDate())
                .organizer(a.getOrganizer())
                .createdAt(a.getCreatedAt())
                .updatedAt(a.getUpdatedAt())
                .build();
    }
}