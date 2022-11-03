package com.sage.tddplaner.application;

import com.sage.tddplaner.domain.Project;
import com.sage.tddplaner.domain.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
}
