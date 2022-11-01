package com.sage.tddplaner.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectRepository {

    List<Project> projects = new ArrayList<>();

    public ProjectRepository() {
        projects.add(Project.builder().id(1L).name("blog").build());
        projects.add(Project.builder().id(2L).name("TDD-Planer").build());
    }

    public List<Project> findAll() {
        return Arrays.asList(
                Project.builder().id(1L).name("blog").build()
        );
    }

    public Project findById(Long id) {
        return projects.stream()
                .filter(project -> project.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
