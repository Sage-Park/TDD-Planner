package com.sage.tddplanner.interfaces.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetSourceObjectsResponse {

    private List<SourceObject> objects;

    @Data
    @Builder
    public static class SourceObject {
        private Long id;
        private String name;
    }

}
