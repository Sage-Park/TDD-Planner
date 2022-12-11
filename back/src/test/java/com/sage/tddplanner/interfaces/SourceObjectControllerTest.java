package com.sage.tddplanner.interfaces;

import com.sage.tddplanner.application.SourceObjectService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SourceObjectController.class)
class SourceObjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SourceObjectService sourceObjectService;

    @Test
    void getObjects() throws Exception {


        given(sourceObjectService.findByRequirementId(1L))
                .willReturn(Arrays.asList(

                ));


        mockMvc.perform(
                        get("/requirements/{requirementId}/source-objects", 1L)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"id\":1,\"name\":\"ObjectController\"}]")))
        ;


        mockMvc.perform(
                        get("/requirements/{requirementId}/source-objects", 2L)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"id\":2,\"name\":\"ObjectService\"}]")))
                .andExpect(content().string(containsString("[{\"id\":3,\"name\":\"ObjectRepository\"}]")))
        ;




    }



}
