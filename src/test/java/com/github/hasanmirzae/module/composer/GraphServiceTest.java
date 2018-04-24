package com.github.hasanmirzae.module.composer;

import com.github.hasanmirzae.module.composer.model.GraphData;
import com.github.hasanmirzae.module.composer.service.GraphService;
import org.junit.Test;

public class GraphServiceTest {

    @Test
    public void testGettingGraphData(){
        GraphService graphService = new GraphService();
        GraphData data = graphService.getData();
    }
}
