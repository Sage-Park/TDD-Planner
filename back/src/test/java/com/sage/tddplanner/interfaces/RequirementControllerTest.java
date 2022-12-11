package com.sage.tddplanner.interfaces;

import com.sage.tddplanner.application.RequirementService;
import com.sage.tddplanner.jpa.Requirement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RequirementController.class)
@ExtendWith(MockitoExtension.class)
class RequirementControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public WebApplicationContext ctx;
    @MockBean
    private RequirementService requirementService;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }


    @Test
    void getProjectRequirement() throws Exception {

        given(requirementService.getAllByProjectId(1L)).willReturn(Arrays.asList(
                Requirement.builder()
                        .id(1L)
                        .name("프로젝트 리스트를 조회할 수 있다.")
                        .projectId(1L)
                        .build()
        ));
        mockMvc.perform(get("/projects/{projectId}/requirements", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"name\":\"프로젝트 리스트를 조회할 수 있다.\",\"projectId\":1}")));

        given(requirementService.getAllByProjectId(2L)).willReturn(Arrays.asList(
                Requirement.builder()
                        .id(1L)
                        .name("프로젝트를 추가할 수 있다.")
                        .projectId(2L)
                        .build(),
                Requirement.builder()
                        .id(2L)
                        .name("프로젝트의 요구사항을 조회할 수 있다.")
                        .projectId(2L)
                        .build()
        ));
        mockMvc.perform(get("/projects/{projectId}/requirements", 2L))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"name\":\"프로젝트를 추가할 수 있다.\",\"projectId\":2}")))
                .andExpect(content().string(containsString("{\"id\":2,\"name\":\"프로젝트의 요구사항을 조회할 수 있다.\",\"projectId\":2}")));


    }

    @Test
    void createProjectRequirement() throws Exception {

        given(requirementService.create(anyLong(), any())).willReturn(3L);

        mockMvc.perform(
                post("/projects/{projectId}/requirements", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"프로젝트를 추가할 수 있다.\"}")
        )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is("/projects/1/requirements/3")))
        ;

        then(requirementService).should().create(1L, "프로젝트를 추가할 수 있다.");

    }





}
