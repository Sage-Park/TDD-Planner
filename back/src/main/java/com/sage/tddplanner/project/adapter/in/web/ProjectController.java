package com.sage.tddplanner.project.adapter.in.web;

import com.sage.tddplanner.application.ProjectService;
import com.sage.tddplanner.jpa.ProjectJpaEntity;
import com.sage.tddplanner.project.application.port.in.CreateProjectUsecase;
import com.sage.tddplanner.project.application.port.in.GetProjectsQuery;
import com.sage.tddplanner.project.domain.Project;
import com.sage.tddplanner.project.domain.ProjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    private final CreateProjectUsecase createProjectUsecase;
    private final GetProjectsQuery getProjectsQuery;
    public ProjectController(@Qualifier("createProjectService") CreateProjectUsecase createProjectUsecase,
                             GetProjectsQuery getProjectsQuery) {
        this.createProjectUsecase = createProjectUsecase;
        this.getProjectsQuery = getProjectsQuery;
    }

    @GetMapping("/projects")

    public List<ProjectJpaEntity> getProjects() {
        return projectService.getProjects();
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable Long id) {
        try {
            Project project = getProjectsQuery.getProject(ProjectId.create(id));
            ProjectDto projectDto = new ProjectDto(project.getProjectId().getId(), project.getName());
            return ResponseEntity.ok(projectDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/projects")
    public ResponseEntity<?> create(@RequestBody CreateProjectRequest request) throws URISyntaxException {

        ProjectId projectId = createProjectUsecase.create(request.getName());

        return ResponseEntity.created(new URI("/projects/" + projectId.getId())).build();

    }

    @PatchMapping("/projects/{projectId}")
    public ResponseEntity<?> patch(
            @PathVariable Long projectId, @RequestBody ProjectJpaEntity project) {

        project.setId(projectId);
        projectService.update(projectId, project.getName());

        return ResponseEntity.ok().build();
    }
}
