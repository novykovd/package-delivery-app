package com.example.delivery_router_project;

import com.example.delivery_router_project.services.SearchService;
import org.junit.jupiter.api.Test;

public class SearchServiceTest {

    @Test
    public void testDijkstra(){
        SearchService searchService = new SearchService();
        searchService.findPath();
    }
}
