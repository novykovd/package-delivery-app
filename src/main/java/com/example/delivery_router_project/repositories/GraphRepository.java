package com.example.delivery_router_project.repositories;


import com.example.delivery_router_project.entities.GraphEntity;
import com.example.delivery_router_project.entities.TownEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GraphRepository extends JpaRepository<GraphEntity, Long>{
    public GraphEntity findByName(TownEnum name);
}
