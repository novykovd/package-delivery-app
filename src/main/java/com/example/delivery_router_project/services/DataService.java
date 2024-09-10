package com.example.delivery_router_project.services;

import com.example.delivery_router_project.entities.NodeEntity;
import com.example.delivery_router_project.repositories.GraphRepository;
import com.example.delivery_router_project.repositories.PackageRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DataService {
    private final GraphRepository graphRepository;
    private final PackageRepository packageRepository;

    public DataService(GraphRepository graphRepository, PackageRepository packageRepository){
        this.graphRepository = graphRepository;
        this.packageRepository = packageRepository;
    }



}
