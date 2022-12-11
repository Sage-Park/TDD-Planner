package com.sage.tddplanner.application;

import com.sage.tddplanner.interfaces.model.GetSourceObjectsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceObjectService {
    public List<GetSourceObjectsResponse.SourceObject> findByRequirementId(Long requirementId) {
        return null;
    }
}
