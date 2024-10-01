package com.example.delivery_router_project.services;


import com.example.delivery_router_project.entities.*;
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
    private GraphRepository graphRepository;
    private PackageRepository packageRepository;


    public SearchService(GraphRepository graphRepository, PackageRepository packageRepository){
        this.graphRepository = graphRepository;
        this.packageRepository = packageRepository;
    }

    public SearchService(){

    }

    private static class NodeDistance {
        private final NodeEntity node;
        private final int distance;

        public NodeDistance(NodeEntity node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        public NodeEntity getNode() {
            return node;
        }

        public int getDistance() {
            return distance;
        }
    }

    private static class Search{
        static List<NodeEntity> dijkstra(NodeEntity start, NodeEntity destination, Map<Long, NodeEntity> graph){
            PriorityQueue<NodeDistance> heap = new PriorityQueue<>(Comparator.comparingInt(NodeDistance::getDistance));
            Map<NodeEntity, Integer> distances = new HashMap<>();
            Map<NodeEntity, NodeEntity> paths = new HashMap<>();


            //set up
            heap.add(new NodeDistance(start, 0));
            paths.put(start, null);
            for(NodeEntity nodeEntity : graph.values()){
                distances.put(nodeEntity, Integer.MAX_VALUE); //infinity
            }


            //search
            while(!heap.isEmpty()){
                NodeEntity current = heap.poll().getNode();



                for(EdgeEntity edge : current.getOutgoingEdges()){
                    NodeEntity neighbor = edge.getTargetNode();
                    int weight = edge.getWeight() + distances.get(current);

                    if(weight < distances.get(neighbor)){
                        distances.put(neighbor, weight);
                        heap.add(new NodeDistance(neighbor, weight));
                        paths.put(neighbor, current);
                    }
                }
            }

            //safeguards
            if(distances.get(destination) == Integer.MAX_VALUE) return null;

            //calculating the resulting path
            List<NodeEntity> path = new ArrayList<>();
            NodeEntity c = destination;

            while(paths.get(c) != null){
                path.add(c);
                c = paths.get(c);
            }
            path.add(c);

            return path;
        }
    }

    public List<NodeEntity> findPath(NodeEntity start, NodeEntity end, Map<Long, NodeEntity> graph){
        return Search.dijkstra(start, end, graph);
    }

    @Transactional
    public void updatePathToPackage(Long id){

        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.lock.timeout", 15000);
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();

            PackageEntity aPackage = em.find(PackageEntity.class, id, LockModeType.PESSIMISTIC_WRITE, properties);
            aPackage.setPath(Search.dijkstra(aPackage.getStartNode(), aPackage.getDestinationNode(), graphRepository.findByName(aPackage.getTown()).getNodes()));

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
                Search.dijkstra(parsel.getStartNode(), parsel.getDestinationNode(), graph.getNodes());
            });
        }

        return results;

    }
}
