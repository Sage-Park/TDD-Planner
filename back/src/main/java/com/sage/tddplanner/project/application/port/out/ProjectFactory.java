package com.sage.tddplanner.project.application.port.out;

import com.sage.tddplanner.project.domain.Project;

public interface ProjectFactory {
    Project create(String projectName);
}
