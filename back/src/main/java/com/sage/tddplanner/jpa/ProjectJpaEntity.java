package com.sage.tddplanner.jpa;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "PROJECT")
public class ProjectJpaEntity {

    public static ProjectJpaEntity createWithNoId(String name) {
        return new ProjectJpaEntity(null, name);
    }



    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
