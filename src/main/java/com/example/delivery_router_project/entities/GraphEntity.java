package com.example.delivery_router_project.entities;

import jakarta.persistence.*;

import java.util.Map;

@Entity
public class GraphEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private TownEnum town;

    @OneToMany(cascade = CascadeType.PERSIST)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
