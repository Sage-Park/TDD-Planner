package com.sage.tddplanner.application;

import com.sage.tddplanner.jpa.ProjectJpaEntity;
import com.sage.tddplanner.jpa.ProjectJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {
    private final ProjectJpaRepository projectRepository;

    public ProjectService(ProjectJpaRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectJpaEntity> getProjects() {

        return projectRepository.findAll();
    }

    public Optional<ProjectJpaEntity> getProject(long id) {
        return projectRepository.findById(id);
    }

    public Long save(ProjectJpaEntity project) {
        return projectRepository.save(project).getId();
    }

    public void update(Long projectId, String name) {

        ProjectJpaEntity project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException());
        if (StringUtils.hasLength(name)) {
            project.setName(name);
        }

    }
}
