package com.sage.tddplanner.interfaces;

import com.sage.tddplanner.application.SourceObjectService;
import com.sage.tddplanner.interfaces.model.GetSourceObjectsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SourceObjectController {

    private final SourceObjectService sourceObjectService;

    @GetMapping("/requirements/{requirementId}/source-objects")
    public ResponseEntity<GetSourceObjectsResponse> getObjects(@PathVariable Long requirementId) {

        return ResponseEntity.ok(GetSourceObjectsResponse.builder()
                        .objects(sourceObjectService.findByRequirementId(requirementId))
                .build());

    }


}
