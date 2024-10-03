package com.example.delivery_router_project.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class NodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn
    private List<EdgeEntity> outgoingEdges = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public List<EdgeEntity> getOutgoingEdges() {
        return outgoingEdges;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOutgoingEdges(List<EdgeEntity> outgoingEdges) {
        this.outgoingEdges = outgoingEdges;
    }
}
