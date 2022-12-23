package com.sage.tddplanner.project.adapter.in.web;

import com.sage.tddplanner.application.ProjectService;
import com.sage.tddplanner.jpa.ProjectJpaEntity;
import com.sage.tddplanner.project.application.port.in.CreateProjectUsecase;
import com.sage.tddplanner.project.domain.ProjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @MockBean
    @Qualifier("createProjectService")
    private CreateProjectUsecase createProjectUsecase;

    @Test
    void getProjects() throws Exception {

        List<ProjectJpaEntity> projects = Arrays.asList(
                ProjectJpaEntity.builder().id(1L).name("blog").build()
        );

        given(projectService.getProjects()).willReturn(projects);

        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("{" +
                                "\"id\":1," +
                                "\"name\":\"blog\"" +
                                "}")
                ))
                .andDo(log())
        ;

    }

    @Test
    void getProject() throws Exception {

        given(projectService.getProject(1L)).willReturn(Optional.of(ProjectJpaEntity.builder()
                .id(1L).name("blog").build()));
        given(projectService.getProject(2L)).willReturn(Optional.of(ProjectJpaEntity.builder()
                .id(2L).name("TDD-planner").build()));

        mockMvc.perform(get("/projects/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("{" +
                                "\"id\":1," +
                                "\"name\":\"blog\"" +
                                "}")
                ))
        ;

        mockMvc.perform(get("/projects/{id}", 2L))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("{" +
                                "\"id\":2," +
                                "\"name\":\"TDD-planner\"" +
                                "}")
                ))
        ;

    }

    @Test
    void postProject() throws Exception {

        given(createProjectUsecase.create(any())).willReturn(ProjectId.create(2L));

        mockMvc.perform(
                        post("/projects")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"TDD-planner\"}")
                )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is("/projects/2")))
        ;

        then(createProjectUsecase).should().create("TDD-planner");

    }

    @Test
    void patchProject() throws Exception {

        mockMvc.perform(
                patch("/projects/{projectId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"TDD-Planner02\"}")
        )
                .andExpect(status().isOk());

        then(projectService).should().update(1L, "TDD-Planner02");
    }

}
