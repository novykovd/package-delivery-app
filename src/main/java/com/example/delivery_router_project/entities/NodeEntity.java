package com.example.delivery_router_project.entities;


import jakarta.persistence.*;

import java.util.Map;

@Entity
public class NodeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    @MapKey(name = "id")
    private Map<Long, EdgeEntity> outbound;


    public Long getId() {
        return id;
    }
}
