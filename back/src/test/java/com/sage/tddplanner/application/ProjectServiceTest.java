package com.sage.tddplanner.application;

import com.sage.tddplanner.domain.Project;
import com.sage.tddplanner.domain.ProjectRepository;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
                Project.builder().name("TDD-planner 만들기").build()
        );
        given(projectRepository.findAll()).willReturn(projects);

        //when
        List<Project> result = projectService.getProjects();

        //then
        Project project = result.get(0);
        assertThat(project.getName(), is("TDD-planner 만들기"));
    }

    @Test
    void getProjectById() {
        //given
        given(projectRepository.findById(1L)).willReturn(Optional.of(Project.builder().name("TDD-planner 만들기").build()));
        //when
        Optional<Project> project = projectService.getProject(1L);
        //then
        assertThat(project.get().getName(), is("TDD-planner 만들기"));
    }

    @Test
    void saveProject() {
        given(projectRepository.save(any())).willReturn(Project.builder()
                        .name("TDD-planner")
                        .id(2L)
                .build());

        Long id = projectService.save(Project.builder().name("TDD-planner").build());

        assertThat(id, is(2L));

    }

    @Nested
    class updateProject {

        @Test
        void ifProjectExist() {

            Project project = Project.builder()
                    .id(1L)
                    .name("TDD")
                    .build();
            given(projectRepository.findById(any())).willReturn(
                    Optional.of(project)
            );

            projectService.update(1L, "TDD-Planner");

            assertThat(project.getName(), is("TDD-Planner"));
        }

        @Test
        void ifProjectNotExist() {
            given(projectRepository.findById(any())).willReturn(
                    Optional.empty()
            );

            Throwable thrown = assertThrows(RuntimeException.class, () -> projectService.update(1L, "TDD-Planner"));

            assertThat(thrown, instanceOf(RuntimeException.class));


        }

        @Test
        void ifNameIsNull() {
            Project project = Project.builder()
                    .id(1L)
                    .name("TDD")
                    .build();
            given(projectRepository.findById(any())).willReturn(
                    Optional.of(project)
            );

            projectService.update(1L, null);

            assertThat(project.getName(), is("TDD"));
        }

    }



}
