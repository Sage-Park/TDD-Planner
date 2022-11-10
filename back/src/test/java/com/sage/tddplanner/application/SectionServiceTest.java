package com.sage.tddplanner.application;

import com.sage.tddplanner.domain.Section;
import com.sage.tddplanner.domain.SectionRepository;
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
class SectionServiceTest {

    private SectionService sectionService;
    @Mock
    private SectionRepository sectionRepository;

    @BeforeEach
    void setup() {
        sectionService = new SectionService(sectionRepository);
    }

    @Test
    void getSections() {
        given(sectionRepository.findAllByProjectId(1L)).willReturn(
                Arrays.asList(
                        Section.builder().build()
                )
        );
        List<Section> sections01 = sectionService.getSections(1L);
        assertThat(sections01.size(), is(1));


        given(sectionRepository.findAllByProjectId(2L)).willReturn(
                Arrays.asList(
                        Section.builder().build(),
                        Section.builder().build()
                )
        );
        List<Section> sections02 = sectionService.getSections(2L);
        assertThat(sections02.size(), is(2));
    }

    @Test
    void createSection() {

        saveAndReturnId(3L);
        Long sectionId01 = sectionService.createSection(1L, "BACK");
        assertThat(sectionId01, is(3L));
        then(sectionRepository).should().save(refEq(Section.builder().projectId(1L).name("BACK").build()));


        saveAndReturnId(4L);
        Long sectionId02 = sectionService.createSection(1L, "FRONT");
        assertThat(sectionId02, is(4L));
        then(sectionRepository).should().save(refEq(Section.builder().projectId(1L).name("FRONT").build()));

    }

    private void saveAndReturnId(long id) {
        given(sectionRepository.save(any())).willReturn(
                Section.builder().id(id).build()
        );
    }

}
