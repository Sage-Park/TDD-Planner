package com.sage.tddplanner.project.application.port.out;

import com.sage.tddplanner.project.domain.Project;
import com.sage.tddplanner.project.domain.ProjectId;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    Optional<Project> findProjectById(ProjectId projectId);

    List<Project> findAll();
}
