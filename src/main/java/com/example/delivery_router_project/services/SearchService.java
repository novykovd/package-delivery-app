package com.example.delivery_router_project.services;


import com.example.delivery_router_project.entities.GraphEntity;
import com.example.delivery_router_project.entities.NodeEntity;
import com.example.delivery_router_project.entities.PackageEntity;
import com.example.delivery_router_project.entities.TownEnum;
import com.example.delivery_router_project.repositories.GraphRepository;
import com.example.delivery_router_project.repositories.PackageRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.hibernate.PessimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

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
        static List<Long> dijkstra(Long start, Long destination, Map<Long, NodeEntity> graph){
            PriorityQueue<NodeEntity> heap = new PriorityQueue<>();
            Map<NodeEntity, Integer> distances = new HashMap<>();
            Map<NodeEntity, NodeEntity> path = new HashMap<>();
            heap.add(graph.get(start));
            while(!heap.isEmpty()){
                NodeEntity focus = heap.poll();
            }
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
            aPackage.setPath(Search.dijkstra(aPackage.getStartNode().getId(), aPackage.getDestinationNode().getId(), graphRepository.findByName(aPackage.getTown()).getNodes()));

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

        for(Long parselId : list){
            PackageEntity parsel = packageRepository.getReferenceById(parselId);
            executorService.submit(() -> {
                Search.dijkstra(parsel.getStartNode().getId(), parsel.getDestinationNode().getId(), graph.getNodes());
            });
        }

        return results;

    }
}
