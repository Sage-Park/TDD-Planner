package com.sage.tddplanner.jpa;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Section {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long projectId;
}
