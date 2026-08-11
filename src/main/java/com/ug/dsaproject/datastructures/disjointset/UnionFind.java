package com.ug.dsaproject.datastructures.disjointset;

import java.util.HashMap;
import java.util.Map;

/**
 * OWNER: Akubia Edith Elorm
 * ROLE: Disjoint Set (Union-Find) — cycle detection / connectivity checks for MST
 *
 * This is consumed directly by Papah's and Abel's Kruskal MST implementation
 * (if the group picks Kruskal over Prim) to detect cycles when adding edges.
 * Coordinate with them on the exact interface if it needs to change.
 *
 * Implement with path compression and union by rank/size for full marks —
 * a naive version works but trace tables will look worse for large inputs.
 *
 * Minimum operations to implement:
 *  - makeSet(String locationId)
 *  - find(String locationId)   — with path compression
 *  - union(String a, String b) — with union by rank/size
 *  - connected(String a, String b)
 */
public class UnionFind {

    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Integer> rank = new HashMap<>();

    public void makeSet(String locationId) {
        parent.put(locationId, locationId);
        rank.put(locationId, 0);
    }

    public String find(String locationId) {
        // TODO: implement with path compression
        return null;
    }

    public boolean union(String a, String b) {
        // TODO: implement with union by rank; return false if already connected (cycle)
        return false;
    }

    public boolean connected(String a, String b) {
        // TODO: implement
        return false;
    }
}
