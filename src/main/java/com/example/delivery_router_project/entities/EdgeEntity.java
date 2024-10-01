package com.example.delivery_router_project.entities;

import jakarta.persistence.*;

@Entity
public class EdgeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "source_node_id")
    private NodeEntity sourceNode;

    @OneToOne
    @JoinColumn(name = "target_node_id")
    private NodeEntity targetNode;

    private Integer weight;  // for weighted graphs

    public Integer getWeight() {
        return weight;
    }

    public NodeEntity getTargetNode() {
        return targetNode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NodeEntity getSourceNode() {
        return sourceNode;
    }

    public void setSourceNode(NodeEntity sourceNode) {
        this.sourceNode = sourceNode;
    }

    public void setTargetNode(NodeEntity targetNode) {
        this.targetNode = targetNode;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
