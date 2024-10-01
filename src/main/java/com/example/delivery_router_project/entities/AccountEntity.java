package com.example.delivery_router_project.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class AccountEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private UserTypeEnum type;
    private String password;
    private TownEnum town;

    @OneToMany
    @JoinColumn
    private List<PackageEntity> ownedPackages;


    public TownEnum getTown() {
        return town;
    }

    public List<PackageEntity> getOwnedPackages() {
        return ownedPackages;
    }

    public String getType() {
        return type.toString();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
