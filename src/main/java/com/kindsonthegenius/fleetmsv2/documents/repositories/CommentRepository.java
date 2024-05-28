package com.kindsonthegenius.fleetmsv2.documents.repositories;

import com.kindsonthegenius.fleetmsv2.documents.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
