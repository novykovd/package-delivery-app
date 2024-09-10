package com.example.delivery_router_project.entities;

import jakarta.persistence.*;

@Entity
public class EdgeEntity {
    @Id
    @GeneratedValue
    private Long id;
    private int weight;

    @OneToOne
    @JoinColumn
    private NodeEntity destination;

}
