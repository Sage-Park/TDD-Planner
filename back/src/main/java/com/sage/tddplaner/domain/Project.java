package com.sage.tddplaner.domain;

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
public class Project {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
