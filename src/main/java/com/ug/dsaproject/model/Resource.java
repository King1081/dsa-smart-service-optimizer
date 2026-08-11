package com.ug.dsaproject.model;

/**
 * Represents a row from data/resources.csv
 */
public class Resource {
    private final String resourceId;
    private final String resourceType;
    private final String homeLocationId;
    private final int capacity;
    private String availabilityStatus;

    public Resource(String resourceId, String resourceType, String homeLocationId,
                     int capacity, String availabilityStatus) {
        this.resourceId = resourceId;
        this.resourceType = resourceType;
        this.homeLocationId = homeLocationId;
        this.capacity = capacity;
        this.availabilityStatus = availabilityStatus;
    }

    public String getResourceId() { return resourceId; }
    public String getResourceType() { return resourceType; }
    public String getHomeLocationId() { return homeLocationId; }
    public int getCapacity() { return capacity; }
    public String getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(String status) { this.availabilityStatus = status; }

    public boolean isAvailable() {
        return "AVAILABLE".equalsIgnoreCase(availabilityStatus);
    }

    @Override
    public String toString() {
        return resourceId + " (" + resourceType + ", " + availabilityStatus + ")";
    }
}
