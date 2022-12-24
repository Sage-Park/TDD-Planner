package com.sage.tddplanner.project.adapter.out.persistence;

import com.sage.tddplanner.project.application.port.out.ProjectRepository;
import com.sage.tddplanner.project.domain.Project;
import com.sage.tddplanner.project.domain.ProjectId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaProjectRepository implements ProjectRepository {

    @Override
    public Optional<Project> findProjectById(ProjectId projectId) {
        return Optional.empty();
    }
}
