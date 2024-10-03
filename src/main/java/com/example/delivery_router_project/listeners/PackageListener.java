package com.example.delivery_router_project.listeners;


import com.example.delivery_router_project.entities.PackageEntity;
import com.example.delivery_router_project.services.SearchService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class PackageListener {
    @Autowired
    private SearchService search;

    @PostPersist
    @PostUpdate
    public void onPostPersist(PackageEntity entity){
        if(entity.getGraphEntity() == null) return;
        if(entity.getDestinationNode() == null) return;
        if(entity.getStartNode() == null) return;
        search.updatePathToPackage(entity.getId());
    }
}
