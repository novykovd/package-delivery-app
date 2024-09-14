package com.example.delivery_router_project.services;


import com.example.delivery_router_project.entities.GraphEntity;
import com.example.delivery_router_project.entities.NodeEntity;
import com.example.delivery_router_project.entities.PackageEntity;
import com.example.delivery_router_project.entities.TownEnum;
import com.example.delivery_router_project.repositories.GraphRepository;
import com.example.delivery_router_project.repositories.PackageRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.hibernate.PessimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Service
public class SearchService {

    @Autowired
    private EntityManager em;
    private final GraphRepository graphRepository;
    private final PackageRepository packageRepository;


    public SearchService(GraphRepository graphRepository, PackageRepository packageRepository){
        this.graphRepository = graphRepository;
        this.packageRepository = packageRepository;
    }

    static class Search{
        static List<Long> dijikstra(Long start, Long destination, Map<Long, NodeEntity> graph){
            Queue<Long> queue = new LinkedList<>();
            queue.add(start);

            Consumer<Long> recursiveSearch = (pointer) -> {

            };

            while(!queue.isEmpty()){
                recursiveSearch.accept(queue.poll());
            }

            return
        }
    }

    @Transactional
    public void updatePathToPackage(Long id){

        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.lock.timeout", 15000);
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();

            PackageEntity aPackage = em.find(PackageEntity.class, id, LockModeType.PESSIMISTIC_WRITE, properties);
            aPackage.setPath(Search.dijikstra(aPackage.getStartNode().getId(), aPackage.getDestinationNode().getId(), graphRepository.findByName(aPackage.getTown()).getNodes()));

            em.merge(aPackage);
            et.commit();

        } catch (PessimisticLockException e){
            //wtf? i dont see how the shit could be locked?? race conditions?
        }
    }

    public List<LinkedList<Long>> getListOfPaths(List<Long> list, TownEnum town){

        GraphEntity graph = graphRepository.findByName(town);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ArrayList<LinkedList<Long>> results = new ArrayList<>();

        for(PackageEntity parsel : list){
            executorService.submit(() -> {
                Search.dijikstra(parsel.getStartNode().getId(), parsel.getDestinationNode().getId(), graph.getNodes());
            });
        }

        return results;

    }
}
