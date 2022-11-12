package com.sage.tddplanner.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Long> {

    List<Requirement> findAllByProjectId(Long projectId);
}
