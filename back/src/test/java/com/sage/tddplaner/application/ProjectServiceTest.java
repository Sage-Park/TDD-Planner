package com.sage.tddplaner.application;

import com.sage.tddplaner.domain.Project;
import com.sage.tddplaner.domain.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    private ProjectService projectService;
    @Mock
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        projectService = new ProjectService(projectRepository);
    }

    @Test
    void getProjects() {

        //given
        List<Project> projects = Arrays.asList(
                Project.builder().name("TDD-Planer 만들기").build()
        );
        given(projectRepository.findAll()).willReturn(projects);

        //when
        List<Project> result = projectService.getProjects();

        //then
        Project project = result.get(0);
        assertThat(project.getName(), is("TDD-Planer 만들기"));
    }

    @Test
    void getProjectById() {
        //given
        given(projectRepository.findById(1L)).willReturn(Optional.of(Project.builder().name("TDD-Planer 만들기").build()));
        //when
        Optional<Project> project = projectService.getProject(1L);
        //then
        assertThat(project.get().getName(), is("TDD-Planer 만들기"));
    }

}
