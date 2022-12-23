package com.sage.tddplanner.project.domain;

import lombok.Getter;

public class ProjectId {
    @Getter
    private Long id;
    public ProjectId(Long id) {
        this.id = id;
    }

    public static ProjectId create(Long id) {
        return new ProjectId(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof ProjectId)) return false;

        ProjectId projectId = (ProjectId) obj;

        return this.id.equals(projectId.getId());
    }
}
