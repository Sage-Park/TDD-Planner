package com.sage.tddplanner.application;

import com.sage.tddplanner.jpa.Project;
import com.sage.tddplanner.jpa.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getProjects() {

        return projectRepository.findAll();
    }

    public Optional<Project> getProject(long id) {
        return projectRepository.findById(id);
    }

    public Long save(Project project) {
        return projectRepository.save(project).getId();
    }

    public void update(Long projectId, String name) {

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException());
        if (StringUtils.hasLength(name)) {
            project.setName(name);
        }

    }
}
