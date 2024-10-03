package com.example.delivery_router_project;


import com.example.delivery_router_project.entities.EdgeEntity;
import com.example.delivery_router_project.entities.GraphEntity;
import com.example.delivery_router_project.entities.NodeEntity;
import com.example.delivery_router_project.entities.PackageEntity;
import com.example.delivery_router_project.repositories.GraphRepository;
import com.example.delivery_router_project.repositories.PackageRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest
public class IntegrationTest {
    //when i save
    //and after i query
    //it should have a filled path.
    //now maybe i could test the listener first

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    GraphRepository graphRepository;

    PackageEntity packageEntity = new PackageEntity();

    @Test
    public void CheckIfPersisted(){
        NodeEntity node1 = new NodeEntity();
        NodeEntity node2 = new NodeEntity();

        EdgeEntity edge12 = new EdgeEntity();

        edge12.setTargetNode(node2);
        edge12.setWeight(10);
        edge12.setSourceNode(node1);

        List<EdgeEntity> edges = new ArrayList<>();
        edges.add(edge12);

        node1.setOutgoingEdges(edges);




        packageEntity.setStartNode(node1);
        packageEntity.setDestinationNode(node2);

        packageRepository.save(packageEntity);

        Map<Long, NodeEntity> graph = new HashMap<>();
        graph.put(node1.getId(), node1);
        graph.put(node2.getId(), node2);

        GraphEntity graphEntity = new GraphEntity();
        graphEntity.setNodes(graph);
        packageEntity.setGraphEntity(graphEntity);

        packageRepository.save(packageEntity);

        assertNotNull(packageEntity.getId());
        assertEquals(packageEntity.getStartNode(), node1);


    }

    @Test
    public void CheckIfCalculated(){
        assertNotNull(packageEntity.getPath());
        assertEquals(packageEntity.getPath().size(), 2);
    }

}
