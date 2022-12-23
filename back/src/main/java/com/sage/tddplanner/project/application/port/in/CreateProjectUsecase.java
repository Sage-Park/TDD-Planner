package com.sage.tddplanner.project.application.port.in;

import com.sage.tddplanner.project.domain.ProjectId;

public interface CreateProjectUsecase {
    ProjectId create(String projectName);
}
