package com.ug.dsaproject.datastructures.queue;

import com.ug.dsaproject.model.ServiceRequest;

/**
 * OWNER: Joseph Kobina Acquah
 * ROLE: Queue (FIFO) — standard request intake
 *
 * Custom array-backed circular queue.
 *
 * The queue follows FIFO (First-In, First-Out):
 * the first ServiceRequest added is the first one removed.
 */
public class RequestQueue {

    private ServiceRequest[] elements;
    private int front;
    private int rear;
    private int size;

    /**
     * Creates a queue with the specified capacity.
     *
     * @param capacity maximum number of requests the queue can hold
     */
    public RequestQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Queue capacity must be greater than 0");
        }

        elements = new ServiceRequest[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    /**
     * Adds a request to the rear of the queue.
     *
     * @param request ServiceRequest to add
     */
    public void enqueue(ServiceRequest request) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }

        rear = (rear + 1) % elements.length;
        elements[rear] = request;
        size++;
    }

    /**
     * Removes and returns the request at the front.
     *
     * @return the oldest ServiceRequest
     */
    public ServiceRequest dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        ServiceRequest request = elements[front];

        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;

        return request;
    }

    /**
     * Returns the request at the front without removing it.
     *
     * @return the oldest ServiceRequest
     */
    public ServiceRequest peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        return elements[front];
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return true if the queue contains no requests
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the queue is full.
     *
     * @return true if the queue has reached its capacity
     */
    public boolean isFull() {
        return size == elements.length;
    }

    /**
     * Returns the number of requests currently in the queue.
     *
     * @return current queue size
     */
    public int size() {
        return size;
    }
}