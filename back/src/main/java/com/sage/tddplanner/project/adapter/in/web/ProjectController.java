package com.sage.tddplanner.project.adapter.in.web;

import com.sage.tddplanner.project.application.port.in.CreateProjectUsecase;
import com.sage.tddplanner.project.application.port.in.GetProjectsQuery;
import com.sage.tddplanner.project.domain.Project;
import com.sage.tddplanner.project.domain.ProjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProjectController {

    private final CreateProjectUsecase createProjectUsecase;
    private final GetProjectsQuery getProjectsQuery;
    public ProjectController(@Qualifier("createProjectService") CreateProjectUsecase createProjectUsecase,
                             GetProjectsQuery getProjectsQuery) {
        this.createProjectUsecase = createProjectUsecase;
        this.getProjectsQuery = getProjectsQuery;
    }

    @GetMapping("/projects")

    public List<ProjectDto> getProjects() {

        return getProjectsQuery.getAllProjects().stream()
                .map(this::mapProject)
                .collect(Collectors.toList());
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable Long id) {
        try {
            Project project = getProjectsQuery.getProject(ProjectId.create(id));
            ProjectDto projectDto = mapProject(project);
            return ResponseEntity.ok(projectDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    private ProjectDto mapProject(Project project) {
        return new ProjectDto(project.getProjectId().getId(), project.getName());
    }

    @PostMapping("/projects")
    public ResponseEntity<?> create(@RequestBody CreateProjectRequest request) throws URISyntaxException {

        ProjectId projectId = createProjectUsecase.create(request.getName());

        return ResponseEntity.created(new URI("/projects/" + projectId.getId())).build();

    }

}
