package com.ug.dsaproject.datastructures.priorityqueue;

import com.ug.dsaproject.model.ServiceRequest;

/**
 * OWNER: Vical Divine Aghe
 * ROLE: Priority Queue / Heap — urgent request dispatch
 *
 * Build this as a custom binary (max-)heap keyed on ServiceRequest.getUrgency()
 * (do NOT use java.util.PriorityQueue for the core logic).
 *
 * Your derived parameter (from team_parameters.csv, priority_weight=5) can be
 * used as a tie-breaker multiplier when two requests share the same urgency —
 * e.g. combine urgency with category or deadline proximity, scaled by your
 * priority_weight, to decide ordering.
 *
 * Minimum operations to implement:
 *  - insert(ServiceRequest)
 *  - extractMax()      — pull the most urgent request
 *  - peek()
 *  - isEmpty(), size()
 */
public class UrgentRequestHeap {

    private ServiceRequest[] heap;
    private int size;
    private final int priorityWeight; // from your team_parameters.csv row

    public UrgentRequestHeap(int capacity, int priorityWeight) {
        heap = new ServiceRequest[capacity];
        size = 0;
        this.priorityWeight = priorityWeight;
    }

    public void insert(ServiceRequest request) {
        // TODO: implement (bubble up)
    }

    public ServiceRequest extractMax() {
        // TODO: implement (swap root with last, bubble down)
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
