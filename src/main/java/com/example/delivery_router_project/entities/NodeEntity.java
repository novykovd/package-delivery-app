package com.example.delivery_router_project.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class NodeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn
    private List<EdgeEntity> outgoingEdges = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public List<EdgeEntity> getOutgoingEdges() {
        return outgoingEdges;
    }
}
