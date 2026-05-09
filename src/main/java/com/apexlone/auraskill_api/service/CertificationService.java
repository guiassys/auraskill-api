package com.apexlone.auraskill_api.service;

import com.apexlone.auraskill_api.dto.CertificationRequest;
import com.apexlone.auraskill_api.dto.CertificationResponse;
import com.apexlone.auraskill_api.entity.Certification;
import com.apexlone.auraskill_api.entity.Professional;
import com.apexlone.auraskill_api.repository.CertificationRepository;
import com.apexlone.auraskill_api.repository.ProfessionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificationService {

    private final CertificationRepository repository;
    private final ProfessionalRepository professionalRepository;

    public CertificationResponse create(CertificationRequest request) {
        Professional professional = professionalRepository.findById(request.getProfessionalId())
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        Certification certification = Certification.builder()
                .professional(professional)
                .certificationName(request.getCertificationName())
                .institution(request.getInstitution())
                .issueDate(request.getIssueDate())
                .description(request.getDescription())
                .build();

        return toResponse(repository.save(certification));
    }

    public List<CertificationResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CertificationResponse findById(Long id) {
        Certification certification = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certification not found"));

        return toResponse(certification);
    }

    public CertificationResponse update(Long id, CertificationRequest request) {
        Certification certification = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certification not found"));

        certification.setCertificationName(request.getCertificationName());
        certification.setInstitution(request.getInstitution());
        certification.setIssueDate(request.getIssueDate());
        certification.setDescription(request.getDescription());

        return toResponse(repository.save(certification));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private CertificationResponse toResponse(Certification c) {
        return CertificationResponse.builder()
                .id(c.getId())
                .professionalId(c.getProfessional().getId())
                .certificationName(c.getCertificationName())
                .institution(c.getInstitution())
                .issueDate(c.getIssueDate())
                .description(c.getDescription())
                .createdAt(c.getCreatedAt())
                .updatedAt(c.getUpdatedAt())
                .build();
    }
}