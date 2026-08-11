package com.ug.dsaproject.datastructures.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Shared graph infra — whoever touches CampusGraph should keep these passing.
 */
class CampusGraphTest {

    @Test
    void newGraphHasNoVertices() {
        CampusGraph graph = new CampusGraph();
        assertEquals(0, graph.vertexCount());
    }

    @Test
    void addVertexIncreasesCount() {
        CampusGraph graph = new CampusGraph();
        graph.addVertex("L001");
        assertEquals(1, graph.vertexCount());
    }
}
