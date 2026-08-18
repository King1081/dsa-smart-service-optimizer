package com.ug.dsaproject.algorithms.mst;

import com.ug.dsaproject.datastructures.disjointset.UnionFind;
import com.ug.dsaproject.datastructures.graph.CampusGraph;
import com.ug.dsaproject.model.Road;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

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
        List<Road> mst = new ArrayList<>();

        // 1. Sort roads cheapest -> priciest by effective weight
        List<Road> sorted = new ArrayList<>(allRoads);
        sorted.sort(Comparator.comparingDouble(Road::getEffectiveWeight));

        // 2. Every location starts as its own set
        UnionFind uf = new UnionFind();
        for (String vertex : graph.getVertices()) {
            uf.makeSet(vertex);
        }

        // 3. Add each road unless it would create a cycle (union() returns false)
        int targetEdges = graph.vertexCount() - 1;
        for (Road road : sorted) {
            if (mst.size() == targetEdges) break;
            if (uf.union(road.getFromLocationId(), road.getToLocationId())) {
                mst.add(road);
            }
        }
        return mst;
    }

    /** Prim's algorithm — grow the MST from a starting vertex using a min-priority structure. */
    public List<Road> runPrim(CampusGraph graph, String startLocationId) {
        List<Road> mst = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        // Min-priority queue over candidate edges crossing the visited/unvisited boundary.
        // NOTE: uses java.util.PriorityQueue for simplicity. If your rubric requires every
        // structure to be custom, swap this for Vical's UrgentRequestHeap pattern adapted to
        // Road edges instead of ServiceRequests -- the algorithm logic below stays the same.
        PriorityQueue<CampusGraph.Edge> frontier =
                new PriorityQueue<>(Comparator.comparingDouble(e -> e.weight));

        visited.add(startLocationId);
        frontier.addAll(graph.getNeighbors(startLocationId));

        while (!frontier.isEmpty() && visited.size() < graph.vertexCount()) {
            CampusGraph.Edge edge = frontier.poll();
            if (visited.contains(edge.to)) continue; // both ends already in the tree

            visited.add(edge.to);
            mst.add(edge.road);

            for (CampusGraph.Edge next : graph.getNeighbors(edge.to)) {
                if (!visited.contains(next.to)) {
                    frontier.add(next);
                }
            }
        }
        return mst;
    }

    /** Sum of effective edge weights in an MST result — use this to cross-check Kruskal vs Prim. */
    public double totalWeight(List<Road> mstEdges) {
        double total = 0;
        for (Road r : mstEdges) total += r.getEffectiveWeight();
        return total;
    }
}
