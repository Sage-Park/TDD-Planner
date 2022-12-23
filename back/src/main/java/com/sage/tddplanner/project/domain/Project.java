package com.sage.tddplanner.project.domain;

import lombok.Getter;

public class Project {

    @Getter
    private ProjectId projectId;
    @Getter
    private final String name;

    public Project(ProjectId projectId, String name) {
        this.projectId = projectId;
        this.name = name;
    }
}
