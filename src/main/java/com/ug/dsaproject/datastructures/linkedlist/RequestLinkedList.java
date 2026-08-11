package com.ug.dsaproject.datastructures.linkedlist;

import com.ug.dsaproject.model.ServiceRequest;

/**
 * OWNER: Josephine Tetteh
 * ROLE: Linked List — request queue backbone / undo history
 *
 * Build this as a custom singly or doubly linked list (do NOT use
 * java.util.LinkedList for the core logic — implement your own Node class).
 *
 * Suggested use in this project: maintain the running history of processed
 * requests so a request can be "undone" (e.g. reverted from IN_PROGRESS
 * back to NEW) by walking back through the list.
 *
 * Minimum operations to implement:
 *  - addLast(ServiceRequest)  — append a request
 *  - removeFirst()            — pop from the front
 *  - undo()                   — revert the most recent operation
 *  - size(), isEmpty()
 *  - a way to iterate for printing / trace tables
 */
public class RequestLinkedList {

    private static class Node {
        ServiceRequest data;
        Node next;
        Node(ServiceRequest data) { this.data = data; }
    }

    private Node head;
    private Node tail;
    private int size;

    public void addLast(ServiceRequest request) {
        // TODO: implement
    }

    public ServiceRequest removeFirst() {
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
