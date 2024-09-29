package com.example.delivery_router_project.listeners;


import com.example.delivery_router_project.entities.PackageEntity;
import com.example.delivery_router_project.services.SearchService;
import jakarta.persistence.PostPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class PackageListener {
    @Autowired
    private SearchService search;

    @PostPersist
    public void onPostPersist(PackageEntity entity){
        search.updatePathToPackage(entity.getId());
    }
}
