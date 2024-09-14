package com.example.delivery_router_project.repositories;

import com.example.delivery_router_project.entities.PackageEntity;
import com.example.delivery_router_project.entities.TownEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository extends JpaRepository<PackageEntity, Long> {
    public List<PackageEntity> findByTown(TownEnum town);

    public List<PackageEntity> findAll(Iterable<Long> ids);
}
