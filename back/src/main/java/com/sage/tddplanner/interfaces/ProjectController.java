package com.sage.tddplanner.interfaces;

import com.sage.tddplanner.application.ProjectService;
import com.sage.tddplanner.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    @GetMapping("/projects/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectService.getProject(id).orElse(null);
    }

    @PostMapping("/projects")
    public ResponseEntity<?> create(@RequestBody Project project) throws URISyntaxException {

        Long id = projectService.save(project);

        return ResponseEntity.created(new URI("/projects/" + id)).build();

    }

    @PatchMapping("/projects/{projectId}")
    public ResponseEntity<?> patch(
            @PathVariable Long projectId, @RequestBody Project project) {

        project.setId(projectId);
        projectService.update(projectId, project.getName());

        return ResponseEntity.ok().build();
    }
}
