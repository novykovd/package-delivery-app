package com.example.delivery_router_project.services;

import com.example.delivery_router_project.entities.AccountEntity;
import com.example.delivery_router_project.entities.NodeEntity;
import com.example.delivery_router_project.entities.PackageEntity;
import com.example.delivery_router_project.entities.UserTypeEnum;
import com.example.delivery_router_project.repositories.AccountRepository;
import com.example.delivery_router_project.repositories.GraphRepository;
import com.example.delivery_router_project.repositories.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@Service
public class DataService {


    @Autowired
    private SearchService search;

    private final GraphRepository graphRepository;
    private final PackageRepository packageRepository;
    private final AccountRepository accountRepository;

    public DataService(GraphRepository graphRepository, PackageRepository packageRepository, AccountRepository accountRepository){
        this.graphRepository = graphRepository;
        this.packageRepository = packageRepository;
        this.accountRepository = accountRepository;
    }

    public void callbackProcessorFunction(String username, GenericDTO dto, BiConsumer<AccountEntity, GenericDTO>... callbacks){
        AccountEntity account = accountRepository.findByName(username);
        for(BiConsumer<AccountEntity, GenericDTO> callback : callbacks){
            callback.accept(account, dto);
        }
    }

    public void getGraphOfCity(AccountEntity account, GenericDTO dto){
        dto.graph = graphRepository.findByName(account.getTown());
    }

    public void getMyPackages(AccountEntity account, GenericDTO dto){
        dto.packages = account.getOwnedPackages();
    }

    public void saveNewPackage(PackageEntity aPackage){
        packageRepository.save(aPackage);
    }




}
