package com.sage.tddplanner.project.adapter.out.persistence;

import com.sage.tddplanner.jpa.ProjectJpaEntity;
import com.sage.tddplanner.jpa.ProjectJpaRepository;
import com.sage.tddplanner.project.application.port.out.ProjectFactory;
import com.sage.tddplanner.project.domain.Project;
import com.sage.tddplanner.project.domain.ProjectId;
import org.springframework.stereotype.Repository;

@Repository
public class JpaProjectFactory implements ProjectFactory {

    private final ProjectJpaRepository projectJpaRepository;
    public JpaProjectFactory(ProjectJpaRepository projectJpaRepository) {
        this.projectJpaRepository = projectJpaRepository;
    }


    @Override
    public Project create(String projectName) {

        ProjectJpaEntity savedEntity = projectJpaRepository.save(ProjectJpaEntity.createWithNoId(projectName));


        return new Project(ProjectId.create(savedEntity.getId()), savedEntity.getName());
    }
}
