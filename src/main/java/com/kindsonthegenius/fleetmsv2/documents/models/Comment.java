package com.kindsonthegenius.fleetmsv2.documents.models;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;
    // Getters and setters
}