package com.ug.dsaproject.datastructures.queue;

import com.ug.dsaproject.model.ServiceRequest;

/**
 * OWNER: Joseph Kobina Acquah
 * ROLE: Queue (FIFO) — standard request intake
 *
 * Build this as a custom array-backed circular queue or linked queue
 * (do NOT use java.util.Queue for the core logic).
 *
 * Suggested use in this project: the front door for new ServiceRequests
 * before they're triaged into the priority queue (Vical's module) by
 * urgency.
 *
 * Minimum operations to implement:
 *  - enqueue(ServiceRequest)
 *  - dequeue()
 *  - peek()
 *  - isEmpty(), isFull(), size()
 */
public class RequestQueue {

    private ServiceRequest[] elements;
    private int front;
    private int rear;
    private int size;

    public RequestQueue(int capacity) {
        elements = new ServiceRequest[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(ServiceRequest request) {
        // TODO: implement (circular wraparound)
    }

    public ServiceRequest dequeue() {
        // TODO: implement
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
