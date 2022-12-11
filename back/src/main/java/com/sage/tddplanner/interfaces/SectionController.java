package com.sage.tddplanner.interfaces;

import com.sage.tddplanner.application.SectionService;
import com.sage.tddplanner.jpa.Section;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class SectionController {

    private final SectionService sectionService;
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/projects/{projectId}/sections")
    public List<Section> getSections(@PathVariable Long projectId) {

        return sectionService.getSections(projectId);
    }

    @PostMapping("/projects/{projectId}/sections")
    public ResponseEntity<?> createSection(@PathVariable Long projectId, @RequestBody Section section) throws URISyntaxException {

        Long sectionId = sectionService.createSection(projectId, section.getName());

        return ResponseEntity.created(new URI("/projects/" + projectId + "/sections/" + sectionId)).build();
    }

}
