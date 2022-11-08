package com.sage.tddplanner.interfaces;

import com.sage.tddplanner.application.SectionService;
import com.sage.tddplanner.domain.Section;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SectionController.class)
@ExtendWith(MockitoExtension.class)
class SectionControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public WebApplicationContext ctx;
    @MockBean
    private SectionService sectionService;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    void getSections() throws Exception {

        given(sectionService.getSections(1L)).willReturn(Arrays.asList(
                Section.builder()
                        .id(1L)
                        .name("API개발")
                        .build(),
                Section.builder()
                        .id(2L)
                        .name("FRONT개발")
                        .build()
        ));

        mockMvc.perform(get("/projects/{projectId}/sections", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("{" +
                                "\"id\":1,\"name\":\"API개발\"" +
                                "}")
                ))
                .andExpect(content().string(
                        containsString("{" +
                                "\"id\":2,\"name\":\"FRONT개발\"" +
                                "}")
                ));

    }

    @Test
    void createSection() throws Exception {

        given(sectionService.createSection(2L, "API개발")).willReturn(1L);

        mockMvc.perform(
                        post("/projects/{projectId}/sections", 2L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\" :  \"API개발\"}")
                )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is("/projects/2/sections/1")))
                .andDo(print())
        ;


        given(sectionService.createSection(3L, "FRONT개발")).willReturn(2L);

        mockMvc.perform(
                        post("/projects/{projectId}/sections", 3L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\" :  \"FRONT개발\"}")
                )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is("/projects/3/sections/2")))
                .andDo(print())
        ;

    }



}
