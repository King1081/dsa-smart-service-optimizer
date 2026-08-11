package com.ug.dsaproject.model;

/**
 * Represents a row from data/roads.csv — an edge between two locations.
 * Used directly by the Graph, Dijkstra, and MST modules.
 */
public class Road {
    private final String roadId;
    private final String fromLocationId;
    private final String toLocationId;
    private final double distanceKm;
    private final int travelTimeMin;
    private final double conditionWeight;

    public Road(String roadId, String fromLocationId, String toLocationId,
                double distanceKm, int travelTimeMin, double conditionWeight) {
        this.roadId = roadId;
        this.fromLocationId = fromLocationId;
        this.toLocationId = toLocationId;
        this.distanceKm = distanceKm;
        this.travelTimeMin = travelTimeMin;
        this.conditionWeight = conditionWeight;
    }

    public String getRoadId() { return roadId; }
    public String getFromLocationId() { return fromLocationId; }
    public String getToLocationId() { return toLocationId; }
    public double getDistanceKm() { return distanceKm; }
    public int getTravelTimeMin() { return travelTimeMin; }
    public double getConditionWeight() { return conditionWeight; }

    /** Effective edge weight for graph algorithms: distance scaled by road condition. */
    public double getEffectiveWeight() {
        return distanceKm * conditionWeight;
    }

    @Override
    public String toString() {
        return roadId + " [" + fromLocationId + " -> " + toLocationId + ", " + distanceKm + "km]";
    }
}
