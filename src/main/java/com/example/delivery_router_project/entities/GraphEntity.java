package com.example.delivery_router_project.entities;

import jakarta.persistence.*;

import java.util.List;
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
}
