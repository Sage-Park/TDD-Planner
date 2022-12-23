package com.sage.tddplanner.project.application.service;

import com.sage.tddplanner.project.application.port.in.CreateProjectUsecase;
import com.sage.tddplanner.project.domain.Project;
import com.sage.tddplanner.project.application.port.out.ProjectFactory;
import com.sage.tddplanner.project.domain.ProjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CreateProjectService implements CreateProjectUsecase {

    private final ProjectFactory projectFactory;
    public CreateProjectService(@Qualifier("jpaProjectFactory") ProjectFactory projectFactory) {
        this.projectFactory = projectFactory;
    }

    @Override
    public ProjectId create(String projectName) {

        Project project = projectFactory.create(projectName);

        return project.getProjectId();

    }
}
