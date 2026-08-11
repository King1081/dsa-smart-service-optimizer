package com.ug.dsaproject.model;

/**
 * Represents a row from data/locations.csv
 */
public class Location {
    private final String locationId;
    private final String name;
    private final String area;
    private final String locationType;
    private final double xCoord;
    private final double yCoord;

    public Location(String locationId, String name, String area, String locationType,
                     double xCoord, double yCoord) {
        this.locationId = locationId;
        this.name = name;
        this.area = area;
        this.locationType = locationType;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public String getLocationId() { return locationId; }
    public String getName() { return name; }
    public String getArea() { return area; }
    public String getLocationType() { return locationType; }
    public double getXCoord() { return xCoord; }
    public double getYCoord() { return yCoord; }

    @Override
    public String toString() {
        return locationId + " (" + name + ", " + locationType + ")";
    }
}
