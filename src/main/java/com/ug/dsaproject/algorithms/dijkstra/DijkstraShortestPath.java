package com.ug.dsaproject.algorithms.dijkstra;

import com.ug.dsaproject.datastructures.graph.CampusGraph;

import java.util.*;

/**
 * OWNERS: Darko Papa Yaw Nyantakyi & Anna Khadija Saaka
 * ROLE: Graph + Dijkstra — shortest route between locations
 *
 * Split suggestion: one of you implements the core algorithm below, the
 * other builds path reconstruction (using Marie-Anne's RouteStack to
 * backtrack from destination to source) and the trace table output. Swap
 * roles on a second scenario run so you both can defend the algorithm.
 *
 * Your derived parameters (route_penalty_factor from team_parameters.csv)
 * can be applied as an extra multiplier on top of Road.getEffectiveWeight()
 * if you want each of your runs to be distinguishable in the trace table.
 *
 * Do NOT use a library priority queue if your rubric requires a custom one —
 * check with Vical, since her UrgentRequestHeap could potentially be reused
 * here (confirm with your instructor whether that's allowed or whether this
 * needs an independent implementation).
 */
public class DijkstraShortestPath {

    public static class Result {
        public final Map<String, Double> distances;
        public final Map<String, String> previous;

        public Result(Map<String, Double> distances, Map<String, String> previous) {
            this.distances = distances;
            this.previous = previous;
        }
    }

    public Result run(CampusGraph graph, String sourceId) {
        // TODO: implement Dijkstra's algorithm
        // 1. Initialize distances map with Double.POSITIVE_INFINITY, source = 0
        // 2. Use a min-priority structure (custom, see note above) keyed on distance
        // 3. Relax edges via graph.getNeighbors(current)
        // 4. Track `previous` map for path reconstruction
        return new Result(new HashMap<>(), new HashMap<>());
    }

    /** Reconstruct the path from source to target using the `previous` map from run(). */
    public List<String> reconstructPath(Result result, String targetId) {
        // TODO: implement, ideally using RouteStack to backtrack then reverse
        return new ArrayList<>();
    }
}
