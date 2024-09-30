package com.example.delivery_router_project.entities;

import com.example.delivery_router_project.listeners.PackageListener;
import jakarta.persistence.*;

import java.util.List;

@Entity
@EntityListeners(PackageListener.class)
public class PackageEntity {
    private Long id;

    @Enumerated(EnumType.STRING)
    private PackageType type;
    @Enumerated(EnumType.STRING)
    private TownEnum town;
    private String name;

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


    //idk ig its ok that its not explicitly connected to the node tab
    @OneToMany
    @JoinColumn
    private List<NodeEntity>  path;


    public Long getId() {
        return id;
    }

    public NodeEntity getStartNode() {
        return startNode;
    }

    public NodeEntity getDestinationNode() {
        return destinationNode;
    }

    public void setPath(List<NodeEntity> path) {
        this.path = path;
    }

    public List<NodeEntity> getPath() {
        return path;
    }

    public TownEnum getTown() {
        return town;
    }
}
