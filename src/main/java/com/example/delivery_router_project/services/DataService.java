package com.example.delivery_router_project.services;

import com.example.delivery_router_project.entities.AccountEntity;
import com.example.delivery_router_project.entities.NodeEntity;
import com.example.delivery_router_project.entities.PackageEntity;
import com.example.delivery_router_project.entities.TownEnum;
import com.example.delivery_router_project.repositories.AccountRepository;
import com.example.delivery_router_project.repositories.GraphRepository;
import com.example.delivery_router_project.repositories.PackageRepository;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class DataService {

    private final GraphRepository graphRepository;
    private final PackageRepository packageRepository;
    private final AccountRepository accountRepository;

    public DataService(GraphRepository graphRepository, PackageRepository packageRepository, AccountRepository accountRepository){
        this.graphRepository = graphRepository;
        this.packageRepository = packageRepository;
        this.accountRepository = accountRepository;
    }

    public void callbackProcessorFunction()

    public Map<Long, NodeEntity> getGraphOfCity(Authentication authentication){
        AccountEntity account = accountRepository.findByName(authentication.getName());
        return graphRepository.findByName(account.getTown()).getNodes();
    }

    public List<PackageEntity> getMyPackages(Username username){
        AccountEntity account = accountRepository.findByName(Username);
        return account.getOwnedPackages();

    }

    public List<LinkedList<Long>> getRoutes(String username){
        AccountEntity account = accountRepository.findByName(username);

    }



}
