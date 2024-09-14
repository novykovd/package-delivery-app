package com.example.delivery_router_project.services;


import com.example.delivery_router_project.entities.GraphEntity;
import com.example.delivery_router_project.entities.NodeEntity;
import com.example.delivery_router_project.entities.PackageEntity;
import com.example.delivery_router_project.entities.TownEnum;
import com.example.delivery_router_project.repositories.GraphRepository;
import com.example.delivery_router_project.repositories.PackageRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Service
public class SearchService {
    private final GraphRepository graphRepository;
    private final PackageRepository packageRepository;


    public SearchService(GraphRepository graphRepository, PackageRepository packageRepository){
        this.graphRepository = graphRepository;
        this.packageRepository = packageRepository;
    }

    static class Search{
        static void dijikstra(Long start, Long destination, Map<Long, NodeEntity> graph, List resultObject){
            Queue<Long> queue = new LinkedList<>();
            queue.add(start);

            Consumer<Long> recursiveSearch = (pointer) -> {

            };

            while(!queue.isEmpty()){
                recursiveSearch.accept(queue.poll());
            }
        }
    }

    public List<LinkedList<Long>> getListOfPaths(List<Long> list, TownEnum town){

        GraphEntity graph = graphRepository.findByName(town);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ArrayList<LinkedList<Long>> results = new ArrayList<>();
        



        for(PackageEntity parsel : list){
            executorService.submit(() -> {
                Search.dijikstra(parsel.getStartNode().getId(), parsel.getDestinationNode().getId(), graph.getNodes(), results);
            });
        }

        return results;

    }
}
