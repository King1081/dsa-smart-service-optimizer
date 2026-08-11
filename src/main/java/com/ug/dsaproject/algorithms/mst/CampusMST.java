package com.ug.dsaproject.algorithms.mst;

import com.ug.dsaproject.datastructures.disjointset.UnionFind;
import com.ug.dsaproject.datastructures.graph.CampusGraph;
import com.ug.dsaproject.model.Road;

import java.util.*;

/**
 * OWNERS: Papah Kweku Okae Quansah (you) & Abel Asare Boateng
 * ROLE: Graph + MST (Prim/Kruskal) — optimal shuttle/maintenance network coverage
 *
 * Split suggestion: one of you implements Kruskal (uses Akubia's UnionFind
 * directly — good excuse to pair with her), the other implements Prim
 * (uses a min-priority structure over edge weights) on a different
 * scenario run. Two algorithms solving the same problem gives you a
 * natural comparison section for the report and two independent defense
 * pieces.
 *
 * Interpretation for this project: the MST gives the minimum-cost road
 * network that still connects every location — useful for arguing which
 * roads are essential for shuttle/maintenance coverage vs. redundant.
 */
public class CampusMST {

    /** Kruskal's algorithm — sort edges, use union-find to avoid cycles. */
    public List<Road> runKruskal(CampusGraph graph, List<Road> allRoads) {
        // TODO: implement
        // 1. Sort allRoads by getEffectiveWeight() ascending
        // 2. UnionFind uf = new UnionFind(); uf.makeSet(...) for every vertex
        // 3. For each road in sorted order: if uf.union(from, to) succeeds, add to MST
        // 4. Stop when MST has (vertexCount - 1) edges
        return new ArrayList<>();
    }

    /** Prim's algorithm — grow the MST from a starting vertex using a min-priority structure. */
    public List<Road> runPrim(CampusGraph graph, String startLocationId) {
        // TODO: implement
        // 1. Track visited set, start from startLocationId
        // 2. Repeatedly pick the minimum-weight edge crossing the visited/unvisited boundary
        // 3. Add that edge's Road to the MST, mark new vertex visited
        // 4. Stop when all reachable vertices are visited
        return new ArrayList<>();
    }
}
