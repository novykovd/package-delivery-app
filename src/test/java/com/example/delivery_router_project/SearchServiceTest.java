package com.example.delivery_router_project;

import com.example.delivery_router_project.entities.EdgeEntity;
import com.example.delivery_router_project.entities.GraphEntity;
import com.example.delivery_router_project.entities.NodeEntity;
import com.example.delivery_router_project.services.SearchService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SearchServiceTest {

    @Test
    public void testDijkstra(){

        NodeEntity node1 = new NodeEntity();
        NodeEntity node2 = new NodeEntity();

        node1.setId(1L);
        node2.setId(2L);

        EdgeEntity edge12 = new EdgeEntity();

        edge12.setTargetNode(node2);
        edge12.setWeight(10);
        edge12.setSourceNode(node1);
        edge12.setId(3L);

        List<EdgeEntity> edges = new ArrayList<>();
        edges.add(edge12);

        node1.setOutgoingEdges(edges);

        Map<Long, NodeEntity> graph = new HashMap<>();
        graph.put(node1.getId(), node1);
        graph.put(node2.getId(), node2);


        SearchService searchService = new SearchService();
        List<NodeEntity> path = searchService.findPath(node1, node2, graph);

        assertNotNull(path);
        assertEquals(2, path.size());
    }
}
