package com.sage.tddplaner.interfaces;

import antlr.ASTNULLType;
import com.sage.tddplaner.domain.Project;
import com.sage.tddplaner.domain.ProjectRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProjectController {

    private ProjectRepository projectRepository = new ProjectRepository();

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/projects/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectRepository.findById(id);
    }
}
