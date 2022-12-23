package com.sage.tddplanner.project.adapter.out.persistence;

import com.sage.tddplanner.jpa.ProjectJpaRepository;
import com.sage.tddplanner.project.application.port.out.ProjectFactory;
import com.sage.tddplanner.project.domain.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@DataJpaTest
class JpaProjectFactoryTest {

    @Autowired
    private ProjectJpaRepository projectJpaRepository;

    @Test
    void createProject() {
        ProjectFactory projectFactory = new JpaProjectFactory(projectJpaRepository);

        //when
        Project project = projectFactory.create("TDD-Planner");

        //then
        boolean exist = projectJpaRepository.existsById(project.getProjectId().getId());
        assertThat(exist, is(true));
        assertThat(project.getName(), is("TDD-Planner"));
        assertThat(project.getProjectId(), notNullValue());

    }

}
