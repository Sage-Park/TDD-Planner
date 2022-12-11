package com.sage.tddplanner.application;

import com.sage.tddplanner.jpa.Requirement;
import com.sage.tddplanner.jpa.RequirementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequirementService {

    private final RequirementRepository requirementRepository;

    public List<Requirement> getAllByProjectId(Long projectId) {
        return requirementRepository.findAllByProjectId(projectId);
    }

    public Long create(long projectId, String requirementName) {

        return requirementRepository.save(Requirement.builder()
                .projectId(projectId)
                .name(requirementName)
                .build()).getId();
    }
}
