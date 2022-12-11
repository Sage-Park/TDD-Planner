package com.sage.tddplanner.application;

import com.sage.tddplanner.jpa.Requirement;
import com.sage.tddplanner.jpa.RequirementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class RequirementServiceTest {

    private RequirementService requirementService;
    @Mock
    private RequirementRepository requirementRepository;

    @BeforeEach
    void setUp() {
        requirementService = new RequirementService(requirementRepository);
    }

    @Test
    void getAllByProjectId() {
        given(requirementRepository.findAllByProjectId(any())).willReturn(Arrays.asList(
                Requirement.builder().id(1L).name("프로젝트를 생성할 수 있다.").build()
        ));

        List<Requirement> requirements = requirementService.getAllByProjectId(1L);

        assertThat(requirements.get(0).getName(), is("프로젝트를 생성할 수 있다."));
    }

    @Test
    void create() {

        given(requirementRepository.save(any())).willReturn(Requirement.builder().id(2L).build());

        Long requirementId = requirementService.create(1L, "프로젝트를 생성할 수 있다.");

        assertThat(requirementId, is(2L));
        then(requirementRepository).should().save(refEq(Requirement.builder()
                .name("프로젝트를 생성할 수 있다.")
                .projectId(1L)
                .build()));

    }



}
