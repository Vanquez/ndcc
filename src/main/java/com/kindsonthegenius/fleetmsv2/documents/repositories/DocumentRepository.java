package com.kindsonthegenius.fleetmsv2.documents.repositories;

import com.kindsonthegenius.fleetmsv2.documents.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {
}
