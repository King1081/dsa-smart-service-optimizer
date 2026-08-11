package com.ug.dsaproject.model;

import java.time.LocalDateTime;

/**
 * Represents a row from data/service_requests.csv
 * This is the core object flowing through the linked list, queue,
 * priority queue, and hash table modules.
 */
public class ServiceRequest {
    private final String requestId;
    private final String sourceLocationId;
    private final String destinationLocationId;
    private final String category;
    private final int urgency; // 1 (low) - 5 (high)
    private final LocalDateTime timeSubmitted;
    private final LocalDateTime deadline;
    private String status;

    public ServiceRequest(String requestId, String sourceLocationId, String destinationLocationId,
                           String category, int urgency, LocalDateTime timeSubmitted,
                           LocalDateTime deadline, String status) {
        this.requestId = requestId;
        this.sourceLocationId = sourceLocationId;
        this.destinationLocationId = destinationLocationId;
        this.category = category;
        this.urgency = urgency;
        this.timeSubmitted = timeSubmitted;
        this.deadline = deadline;
        this.status = status;
    }

    public String getRequestId() { return requestId; }
    public String getSourceLocationId() { return sourceLocationId; }
    public String getDestinationLocationId() { return destinationLocationId; }
    public String getCategory() { return category; }
    public int getUrgency() { return urgency; }
    public LocalDateTime getTimeSubmitted() { return timeSubmitted; }
    public LocalDateTime getDeadline() { return deadline; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return requestId + " [" + category + ", urgency=" + urgency + ", " + status + "]";
    }
}
