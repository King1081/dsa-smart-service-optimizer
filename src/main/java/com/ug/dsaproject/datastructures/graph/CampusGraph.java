package com.ug.dsaproject.datastructures.graph;

import com.ug.dsaproject.model.Road;

import java.util.*;

/**
 * SHARED INFRASTRUCTURE — used by all 4 graph-role owners:
 *   Darko Papa Yaw Nyantakyi & Anna Khadija Saaka  (Dijkstra)
 *   Papah Kweku Okae Quansah & Abel Asare Boateng  (MST)
 *
 * This is a custom adjacency-list graph built from roads.csv (do NOT swap
 * in a library graph implementation — this counts as your "custom data
 * structure" for the graph requirement).
 *
 * IMPORTANT: coordinate with your graph-role teammates before changing this
 * file — everyone's algorithm depends on its shape. Agree on any changes in
 * the group first.
 */
public class CampusGraph {

    public static class Edge {
        public final String to;
        public final double weight;
        public final Road road;

        public Edge(String to, double weight, Road road) {
            this.to = to;
            this.weight = weight;
            this.road = road;
        }
    }

    private final Map<String, List<Edge>> adjacency = new HashMap<>();

    /** Add a location as a vertex (call for every Location before adding roads). */
    public void addVertex(String locationId) {
        adjacency.putIfAbsent(locationId, new ArrayList<>());
    }

    /** Add an undirected edge from a Road record. Adjust to directed if your scenario needs it. */
    public void addRoad(Road road) {
        addVertex(road.getFromLocationId());
        addVertex(road.getToLocationId());
        double w = road.getEffectiveWeight();
        adjacency.get(road.getFromLocationId()).add(new Edge(road.getToLocationId(), w, road));
        adjacency.get(road.getToLocationId()).add(new Edge(road.getFromLocationId(), w, road));
    }

    public List<Edge> getNeighbors(String locationId) {
        return adjacency.getOrDefault(locationId, Collections.emptyList());
    }

    public Set<String> getVertices() {
        return adjacency.keySet();
    }

    public int vertexCount() {
        return adjacency.size();
    }
}
