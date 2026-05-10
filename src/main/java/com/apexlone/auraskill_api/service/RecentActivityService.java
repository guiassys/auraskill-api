package com.apexlone.auraskill_api.service;

import com.apexlone.auraskill_api.dto.RecentActivityRequest;
import com.apexlone.auraskill_api.dto.RecentActivityResponse;
import com.apexlone.auraskill_api.entity.Professional;
import com.apexlone.auraskill_api.entity.RecentActivity;
import com.apexlone.auraskill_api.repository.ProfessionalRepository;
import com.apexlone.auraskill_api.repository.RecentActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecentActivityService {

    private final RecentActivityRepository repository;
    private final ProfessionalRepository professionalRepository;

    public RecentActivityResponse create(RecentActivityRequest request) {
        Professional professional = professionalRepository.findById(request.getProfessionalId())
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        RecentActivity recentActivity = RecentActivity.builder()
                .professional(professional)
                .activityType(request.getActivityType())
                .activityDescription(request.getActivityDescription())
                .build();

        return toResponse(repository.save(recentActivity));
    }

    public List<RecentActivityResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public RecentActivityResponse findById(Long id) {
        RecentActivity recentActivity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("RecentActivity not found"));

        return toResponse(recentActivity);
    }

    public RecentActivityResponse update(Long id, RecentActivityRequest request) {
        RecentActivity recentActivity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("RecentActivity not found"));

        recentActivity.setActivityType(request.getActivityType());
        recentActivity.setActivityDescription(request.getActivityDescription());

        return toResponse(repository.save(recentActivity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private RecentActivityResponse toResponse(RecentActivity ra) {
        return RecentActivityResponse.builder()
                .id(ra.getId())
                .professionalId(ra.getProfessional().getId())
                .activityType(ra.getActivityType())
                .activityDescription(ra.getActivityDescription())
                .activityDate(ra.getActivityDate())
                .build();
    }
}