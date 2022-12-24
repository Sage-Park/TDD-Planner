package com.sage.tddplanner.project.application.port.in;

import com.sage.tddplanner.project.domain.Project;
import com.sage.tddplanner.project.domain.ProjectId;

public interface GetProjectsQuery {

    Project getProject(ProjectId projectId);

}
