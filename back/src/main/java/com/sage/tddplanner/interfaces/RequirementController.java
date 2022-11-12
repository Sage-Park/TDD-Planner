package com.sage.tddplanner.interfaces;

import com.sage.tddplanner.application.RequirementService;
import com.sage.tddplanner.domain.Requirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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

    @PostMapping("/projects/{projectId}/requirements")
    public ResponseEntity<?> createProjectRequirements(@PathVariable Long projectId, @RequestBody Requirement requirement) throws URISyntaxException {

        Long requirementId = requirementService.create(projectId, requirement.getName());


        return ResponseEntity.created(new URI("/projects/" + projectId + "/requirements/" + requirementId)).build();
    }

}
