package com.sage.tddplanner.project.adapter.out.persistence;

import com.sage.tddplanner.jpa.ProjectJpaRepository;
import com.sage.tddplanner.project.application.port.out.ProjectRepository;
import com.sage.tddplanner.project.domain.Project;
import com.sage.tddplanner.project.domain.ProjectId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaProjectRepository implements ProjectRepository {

    private final ProjectJpaRepository projectJpaRepository;

    public JpaProjectRepository(ProjectJpaRepository projectJpaRepository) {
        this.projectJpaRepository = projectJpaRepository;
    }

    @Override
    public Optional<Project> findProjectById(ProjectId projectId) {
        return Optional.empty();
    }

    @Override
    public List<Project> findAll() {
        return projectJpaRepository.findAll().stream()
                .map(projectJpaEntity -> new Project(ProjectId.create(projectJpaEntity.getId()), projectJpaEntity.getName()))
                .collect(Collectors.toList());
    }
}
