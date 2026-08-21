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
        heap = new ServiceRequest[capacity > 0 ? capacity : 10];
        size = 0;
        this.priorityWeight = priorityWeight;
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }

    private void swap(int i, int j) {
        ServiceRequest temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int compare(ServiceRequest a, ServiceRequest b) {
        if (a.getUrgency() != b.getUrgency()) {
            return Integer.compare(a.getUrgency(), b.getUrgency());
        }
        // Tie-breaker: earlier deadline means higher priority.
        // b.compareTo(a) is positive if b is after a (meaning a is earlier, so a should be greater)
        if (a.getDeadline() != null && b.getDeadline() != null) {
            int deadlineCompare = b.getDeadline().compareTo(a.getDeadline());
            if (deadlineCompare != 0) {
                 return deadlineCompare * (priorityWeight > 0 ? priorityWeight : 1);
            }
        }
        // If still tie, earlier timeSubmitted means higher priority
        if (a.getTimeSubmitted() != null && b.getTimeSubmitted() != null) {
            return b.getTimeSubmitted().compareTo(a.getTimeSubmitted());
        }
        return 0;
    }

    public void insert(ServiceRequest request) {
        if (size >= heap.length) {
            ServiceRequest[] newHeap = new ServiceRequest[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, heap.length);
            heap = newHeap;
        }
        heap[size] = request;
        int current = size;
        size++;

        // Bubble up
        while (current > 0 && compare(heap[current], heap[parent(current)]) > 0) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public ServiceRequest extractMax() {
        if (isEmpty()) {
            return null;
        }
        ServiceRequest max = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        
        // Bubble down
        int current = 0;
        while (leftChild(current) < size) {
            int maxChild = leftChild(current);
            int right = rightChild(current);
            if (right < size && compare(heap[right], heap[maxChild]) > 0) {
                maxChild = right;
            }
            if (compare(heap[current], heap[maxChild]) >= 0) {
                break;
            }
            swap(current, maxChild);
            current = maxChild;
        }
        return max;
    }

    public ServiceRequest peek() {
        if (isEmpty()) return null;
        return heap[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
