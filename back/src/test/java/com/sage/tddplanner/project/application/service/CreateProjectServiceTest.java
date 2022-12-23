package com.sage.tddplanner.project.application.service;

import com.sage.tddplanner.project.application.port.in.CreateProjectUsecase;
import com.sage.tddplanner.project.domain.Project;
import com.sage.tddplanner.project.application.port.out.ProjectFactory;
import com.sage.tddplanner.project.domain.ProjectId;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.*;

class CreateProjectServiceTest {

    @Test
    void createProject() {

        //given
        ProjectFactory projectFactory = Mockito.mock(ProjectFactory.class);
        given(projectFactory.create("TDD-Planner"))
                .willReturn(new Project(new ProjectId(1L), "TDD-Planner"));
        CreateProjectUsecase createProjectUsecase = new CreateProjectService(projectFactory);

        //when
        ProjectId projectId = createProjectUsecase.create("TDD-Planner");

        //then
        MatcherAssert.assertThat(projectId, Matchers.equalToObject(new ProjectId(1L)));

    }

}
