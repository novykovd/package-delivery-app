package com.example.delivery_router_project.entities;

import jakarta.persistence.*;

import java.util.Map;

@Entity
public class GraphEntity {


    @Enumerated(value = EnumType.STRING)
    private TownEnum town;

    @OneToMany
    @MapKey(name = "id")
    private Map<Long, NodeEntity> nodes;


    public Map<Long, NodeEntity> getNodes() {
        return nodes;
    }

    public TownEnum getTown() {
        return town;
    }

    public void setTown(TownEnum town) {
        this.town = town;
    }

    public void setNodes(Map<Long, NodeEntity> nodes) {
        this.nodes = nodes;
    }
}
