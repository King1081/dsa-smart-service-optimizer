package com.ug.dsaproject.datastructures.stack;

import com.ug.dsaproject.model.Location;

/**
 * OWNER: Marie-Anne Dzifa Hayibor
 * ROLE: Stack — route backtracking / undo actions
 *
 * Build this as a custom array-backed or linked stack (do NOT use
 * java.util.Stack for the core logic).
 *
 * Suggested use in this project: track the path taken during a graph
 * traversal (e.g. for Dijkstra path reconstruction, or backtracking when
 * a route hits a dead end / blocked road).
 *
 * Minimum operations to implement:
 *  - push(Location)
 *  - pop()
 *  - peek()
 *  - isEmpty(), size()
 */
public class RouteStack {

    private Object[] elements; // TODO: replace Object[] with Location[] once implemented
    private int top;

    public RouteStack(int initialCapacity) {
        elements = new Object[initialCapacity];
        top = -1;
    }

    public void push(Location location) {
        // TODO: implement (resize array if full)
    }

    public Location pop() {
        // TODO: implement
        return null;
    }

    public Location peek() {
        // TODO: implement
        return null;
    }

    public boolean isEmpty() {
        return top == -1;
    }
}
