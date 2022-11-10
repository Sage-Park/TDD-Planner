package com.sage.tddplanner.interfaces;

import com.sage.tddplanner.application.RequirementService;
import com.sage.tddplanner.domain.Requirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RequirementController {

    private final RequirementService requirementService;
    public RequirementController(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    @GetMapping("/projects/{projectId}/requirements")
    public List<Requirement> getProjectRequirements(@PathVariable Long projectId) {

        return requirementService.getAllByProjectId(projectId);

    }

}
