package com.sage.tddplanner.project.application.service;

import com.sage.tddplanner.project.application.port.in.GetProjectsQuery;
import com.sage.tddplanner.project.application.port.out.ProjectRepository;
import com.sage.tddplanner.project.domain.Project;
import com.sage.tddplanner.project.domain.ProjectId;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;

class GetProjectsServiceTest {

    @Test
    void getProject() {

        ProjectRepository projectRepository = Mockito.mock(ProjectRepository.class);
        GetProjectsQuery query = new GetProjectsService(projectRepository);

        given(projectRepository.findProjectById(any())).willReturn(
                Optional.of(new Project(ProjectId.create(1L), "TDD-Planner"))
        );

        Project project = query.getProject(ProjectId.create(1L));

        assertThat(project, notNullValue());
    }

    @DisplayName("만약 프로젝트가 없다면 에러를 던진다.")
    @Test
    void throwExceptionIfNotExist() {
        ProjectRepository projectRepository = Mockito.mock(ProjectRepository.class);
        GetProjectsQuery query = new GetProjectsService(projectRepository);

        given(projectRepository.findProjectById(any())).willReturn(
                Optional.empty()
        );

        Throwable throwable = Assertions.catchThrowable(() -> query.getProject(ProjectId.create(1L)));

        assertThat(throwable, instanceOf(RuntimeException.class));
    }


}
