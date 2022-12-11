package com.sage.tddplanner.application;

import com.sage.tddplanner.jpa.Section;
import com.sage.tddplanner.jpa.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public List<Section> getSections(Long projectId) {
        return sectionRepository.findAllByProjectId(projectId);
    }

    public Long createSection(Long projectId, String name) {
        Section savedEntity = sectionRepository.save(Section.builder()
                        .projectId(projectId)
                        .name(name)
                .build());
        return savedEntity.getId();
    }
}
