package com.example.delivery_router_project.entities;

import jakarta.persistence.*;

@Entity
public class PackageEntity {
    private Long id;

    @Enumerated(EnumType.STRING)
    private PackageType type;
    @Enumerated(EnumType.STRING)
    private TownEnum town;
    private String description;

    @OneToOne()
    @JoinColumn()
    private NodeEntity startNode;

    @OneToOne()
    @JoinColumn()
    private NodeEntity destinationNode;

    @OneToOne
    @JoinColumn
    private AccountEntity owner;

    @OneToOne
    @JoinColumn
    private AccountEntity courier;


    public Long getId() {
        return id;
    }

    public NodeEntity getStartNode() {
        return startNode;
    }

    public NodeEntity getDestinationNode() {
        return destinationNode;
    }
}
