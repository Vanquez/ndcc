package com.kindsonthegenius.fleetmsv2.es.repositories;

import com.kindsonthegenius.fleetmsv2.es.models.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Integer> {
}
