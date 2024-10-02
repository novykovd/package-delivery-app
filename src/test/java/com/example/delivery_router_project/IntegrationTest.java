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
@Transactional
public class IntegrationTest {
    //when i save
    //and after i query
    //it should have a filled path.
    //now maybe i could test the listener first

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    GraphRepository graphRepository;

    @Test
    public void CheckIfPersisted(){
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

        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setStartNode(node1);
        packageEntity.setDestinationNode(node2);
        packageEntity.setId(1L);

        packageRepository.save(packageEntity);

        GraphEntity graphEntity = new GraphEntity();
        graphEntity.setNodes(graph);

        packageEntity.setGraphEntity(graphEntity);


        assertNotNull(packageRepository.getReferenceById(1L));
        assertEquals(packageRepository.getReferenceById(1L).getStartNode(), node1);


    }

    @Test
    public void CheckIfCalculated(){
        assertNotNull(packageRepository.getReferenceById(1L).getPath());
        assertEquals(packageRepository.getReferenceById(1L).getPath().size(), 2);
    }

}
