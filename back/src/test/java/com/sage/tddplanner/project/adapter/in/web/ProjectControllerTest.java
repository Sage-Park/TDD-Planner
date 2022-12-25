package com.sage.tddplanner.project.adapter.in.web;

import com.sage.tddplanner.project.application.port.in.CreateProjectUsecase;
import com.sage.tddplanner.project.application.port.in.GetProjectsQuery;
import com.sage.tddplanner.project.domain.Project;
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

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("createProjectService")
    private CreateProjectUsecase createProjectUsecase;

    @MockBean
    private GetProjectsQuery getProjectsQuery;

    @Test
    void getProjects() throws Exception {

        List<Project> projects = Arrays.asList(
                new Project(ProjectId.create(1L), "blog")
        );
        given(getProjectsQuery.getAllProjects()).willReturn(projects);

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

        given(getProjectsQuery.getProject(ProjectId.create(1L)))
                .willReturn(new Project(ProjectId.create(1L), "blog"));
        given(getProjectsQuery.getProject(ProjectId.create(2L)))
                .willReturn(new Project(ProjectId.create(2L), "TDD-planner"));


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
    void getProjectIfException() throws Exception {

        willThrow(new RuntimeException())
                .given(getProjectsQuery).getProject(any());

        mockMvc.perform(get("/projects/1"))
                .andExpect(status().is5xxServerError());

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

}
