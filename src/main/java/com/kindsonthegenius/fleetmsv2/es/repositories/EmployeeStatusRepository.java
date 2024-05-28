package com.kindsonthegenius.fleetmsv2.es.repositories;

import com.kindsonthegenius.fleetmsv2.es.models.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeStatusRepository extends JpaRepository<EmployeeStatus, Integer> {
}
