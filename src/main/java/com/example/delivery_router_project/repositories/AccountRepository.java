package com.example.delivery_router_project.repositories;


import com.example.delivery_router_project.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    public AccountEntity findByName(String name);
}
