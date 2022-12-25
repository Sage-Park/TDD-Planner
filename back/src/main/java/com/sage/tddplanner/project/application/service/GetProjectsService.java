package com.sage.tddplanner.project.application.service;

import com.sage.tddplanner.project.application.port.in.GetProjectsQuery;
import com.sage.tddplanner.project.application.port.out.ProjectRepository;
import com.sage.tddplanner.project.domain.Project;
import com.sage.tddplanner.project.domain.ProjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProjectsService implements GetProjectsQuery {

    private final ProjectRepository projectRepository;
    public GetProjectsService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project getProject(ProjectId projectId) {
        return projectRepository.findProjectById(projectId).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }


}
