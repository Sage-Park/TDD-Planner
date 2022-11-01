package com.sage.tddplaner.interfaces;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void getProjects() throws Exception {

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

    @SneakyThrows
    @Test
    void getProject() {

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
                                "\"name\":\"TDD-Planer\"" +
                                "}")
                ))
        ;


    }
}
