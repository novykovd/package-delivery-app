package com.example.delivery_router_project.entities;

import jakarta.persistence.*;

@Entity
public class EdgeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_node_id")
    private NodeEntity sourceNode;

    @ManyToOne
    @JoinColumn(name = "target_node_id")
    private NodeEntity targetNode;

    private Integer weight;  // for weighted graphs

    public Integer getWeight() {
        return weight;
    }

    public NodeEntity getTargetNode() {
        return targetNode;
    }
}
