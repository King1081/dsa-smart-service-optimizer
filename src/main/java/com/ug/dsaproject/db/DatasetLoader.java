package com.ug.dsaproject.db;

import com.ug.dsaproject.model.*;
import com.ug.dsaproject.util.CsvLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class DatasetLoader {

    private static final DateTimeFormatter DT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    private DatasetLoader() {
        }

    // ------------------------------------------------------------------
    // CSV -> DB
    // ------------------------------------------------------------------

    /**
     * Reads all 5 CSVs from dataDir (via CsvLoader) and inserts them into
     * the DB in FK-safe order: locations, then roads/resources/requests,
     * then team_parameters. Wraps everything in one transaction.
     */
    public static void loadCsvIntoDb(Connection conn, String dataDir) throws SQLException, java.io.IOException {
        List<Location> locations = CsvLoader.loadLocations(dataDir + "/locations.csv");
        List<Road> roads = CsvLoader.loadRoads(dataDir + "/roads.csv");
        List<Resource> resources = CsvLoader.loadResources(dataDir + "/resources.csv");
        List<ServiceRequest> requests = CsvLoader.loadServiceRequests(dataDir + "/service_requests.csv");
        List<TeamParameter> params = CsvLoader.loadTeamParameters(dataDir + "/team_parameters.csv");

        boolean originalAutoCommit = conn.getAutoCommit();
        conn.setAutoCommit(false);
        try {
            insertLocations(conn, locations);
            insertRoads(conn, roads);
            insertResources(conn, resources);
            insertServiceRequests(conn, requests);
            insertTeamParameters(conn, params);
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(originalAutoCommit);
        }
    }

    public static void insertLocations(Connection conn, List<Location> locations) throws SQLException {
        String sql = "INSERT OR REPLACE INTO locations (location_id, name, area, location_type, x_coord, y_coord) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Location loc : locations) {
                ps.setString(1, loc.getLocationId());
                ps.setString(2, loc.getName());
                ps.setString(3, loc.getArea());
                ps.setString(4, loc.getLocationType());
                ps.setDouble(5, loc.getXCoord());
                ps.setDouble(6, loc.getYCoord());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public static void insertRoads(Connection conn, List<Road> roads) throws SQLException {
        String sql = "INSERT OR REPLACE INTO roads (road_id, from_location_id, to_location_id, distance_km, travel_time_min, condition_weight) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Road road : roads) {
                ps.setString(1, road.getRoadId());
                ps.setString(2, road.getFromLocationId());
                ps.setString(3, road.getToLocationId());
                ps.setDouble(4, road.getDistanceKm());
                ps.setInt(5, road.getTravelTimeMin());
                ps.setDouble(6, road.getConditionWeight());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public static void insertResources(Connection conn, List<Resource> resources) throws SQLException {
        String sql = "INSERT OR REPLACE INTO resources (resource_id, resource_type, home_location_id, capacity, availability_status) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Resource res : resources) {
                ps.setString(1, res.getResourceId());
                ps.setString(2, res.getResourceType());
                ps.setString(3, res.getHomeLocationId());
                ps.setInt(4, res.getCapacity());
                ps.setString(5, res.getAvailabilityStatus());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public static void insertServiceRequests(Connection conn, List<ServiceRequest> requests) throws SQLException {
        String sql = "INSERT OR REPLACE INTO service_requests " +
                "(request_id, source_location_id, destination_location_id, category, urgency, time_submitted, deadline, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (ServiceRequest req : requests) {
                ps.setString(1, req.getRequestId());
                ps.setString(2, req.getSourceLocationId());
                ps.setString(3, req.getDestinationLocationId());
                ps.setString(4, req.getCategory());
                ps.setInt(5, req.getUrgency());
                ps.setString(6, req.getTimeSubmitted().format(DT_FORMAT));
                ps.setString(7, req.getDeadline().format(DT_FORMAT));
                ps.setString(8, req.getStatus());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public static void insertTeamParameters(Connection conn, List<TeamParameter> params) throws SQLException {
        String sql = "INSERT OR REPLACE INTO team_parameters " +
                "(member_name, index_number, assigned_role, priority_weight, route_penalty_factor, hash_table_size, random_seed) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (TeamParameter p : params) {
                ps.setString(1, p.getMemberName());
                ps.setString(2, p.getIndexNumber());
                ps.setString(3, p.getAssignedRole());
                ps.setInt(4, p.getPriorityWeight());
                ps.setDouble(5, p.getRoutePenaltyFactor());
                ps.setInt(6, p.getHashTableSize());
                ps.setInt(7, p.getRandomSeed());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    // ------------------------------------------------------------------
    // DB -> in-memory model objects
    // ------------------------------------------------------------------

    public static List<Location> loadLocationsFromDb(Connection conn) throws SQLException {
        List<Location> result = new ArrayList<>();
        String sql = "SELECT location_id, name, area, location_type, x_coord, y_coord FROM locations";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new Location(
                        rs.getString("location_id"),
                        rs.getString("name"),
                        rs.getString("area"),
                        rs.getString("location_type"),
                        rs.getDouble("x_coord"),
                        rs.getDouble("y_coord")
                ));
            }
        }
        return result;
    }

    public static List<Road> loadRoadsFromDb(Connection conn) throws SQLException {
        List<Road> result = new ArrayList<>();
        String sql = "SELECT road_id, from_location_id, to_location_id, distance_km, travel_time_min, condition_weight FROM roads";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new Road(
                        rs.getString("road_id"),
                        rs.getString("from_location_id"),
                        rs.getString("to_location_id"),
                        rs.getDouble("distance_km"),
                        rs.getInt("travel_time_min"),
                        rs.getDouble("condition_weight")
                ));
            }
        }
        return result;
    }

    public static List<Resource> loadResourcesFromDb(Connection conn) throws SQLException {
        List<Resource> result = new ArrayList<>();
        String sql = "SELECT resource_id, resource_type, home_location_id, capacity, availability_status FROM resources";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new Resource(
                        rs.getString("resource_id"),
                        rs.getString("resource_type"),
                        rs.getString("home_location_id"),
                        rs.getInt("capacity"),
                        rs.getString("availability_status")
                ));
            }
        }
        return result;
    }

    public static List<ServiceRequest> loadServiceRequestsFromDb(Connection conn) throws SQLException {
        List<ServiceRequest> result = new ArrayList<>();
        String sql = "SELECT request_id, source_location_id, destination_location_id, category, urgency, time_submitted, deadline, status FROM service_requests";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new ServiceRequest(
                        rs.getString("request_id"),
                        rs.getString("source_location_id"),
                        rs.getString("destination_location_id"),
                        rs.getString("category"),
                        rs.getInt("urgency"),
                        LocalDateTime.parse(rs.getString("time_submitted"), DT_FORMAT),
                        LocalDateTime.parse(rs.getString("deadline"), DT_FORMAT),
                        rs.getString("status")
                ));
            }
        }
        return result;
    }

    public static List<TeamParameter> loadTeamParametersFromDb(Connection conn) throws SQLException {
        List<TeamParameter> result = new ArrayList<>();
        String sql = "SELECT member_name, index_number, assigned_role, priority_weight, route_penalty_factor, hash_table_size, random_seed FROM team_parameters";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new TeamParameter(
                        rs.getString("member_name"),
                        rs.getString("index_number"),
                        rs.getString("assigned_role"),
                        rs.getInt("priority_weight"),
                        rs.getDouble("route_penalty_factor"),
                        rs.getInt("hash_table_size"),
                        rs.getInt("random_seed")
                ));
            }
        }
        return result;
    }
}