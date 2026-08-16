package com.ug.dsaproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * OWNER: Nigel Miranda Sedem Kedman
 * ROLE: DB schema + Integration/Testing lead
 *
 * SQLite persistence layer for the project. connect() opens (and creates,
 * if missing) the on-disk database file; createTables() runs the DDL for
 * all five tables so DatasetLoader has something to insert into.
 *
 * Usage:
 *   try (Connection conn = SchemaSetup.connect()) {
 *       SchemaSetup.createTables(conn);
 *       // ... insert / query
 *   }
 */
public class SchemaSetup {

    /** Default DB file, created at project root next to /data. */
    public static final String DEFAULT_DB_PATH = "campus_service_hub.db";

    private SchemaSetup() {
        // utility class, no instances
    }

    /** Opens a connection to the default SQLite DB file, creating it if needed. */
    public static Connection connect() throws SQLException {
        return connect(DEFAULT_DB_PATH);
    }

    /** Opens a connection to the given SQLite DB file, creating it if needed. */
    public static Connection connect(String dbPath) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        // Enforce FK constraints; SQLite has them off by default per-connection.
        try (Statement pragma = conn.createStatement()) {
            pragma.execute("PRAGMA foreign_keys = ON;");
        }
        return conn;
    }

    /** Creates all tables if they don't already exist. Safe to call every run. */
    public static void createTables(Connection conn) throws SQLException {
        String locations = """
                CREATE TABLE IF NOT EXISTS locations (
                    location_id     TEXT PRIMARY KEY,
                    name            TEXT NOT NULL,
                    area            TEXT,
                    location_type   TEXT,
                    x_coord         REAL,
                    y_coord         REAL
                );
                """;

        String roads = """
                CREATE TABLE IF NOT EXISTS roads (
                    road_id           TEXT PRIMARY KEY,
                    from_location_id  TEXT NOT NULL REFERENCES locations(location_id),
                    to_location_id    TEXT NOT NULL REFERENCES locations(location_id),
                    distance_km       REAL,
                    travel_time_min   INTEGER,
                    condition_weight  REAL
                );
                """;

        String resources = """
                CREATE TABLE IF NOT EXISTS resources (
                    resource_id          TEXT PRIMARY KEY,
                    resource_type        TEXT,
                    home_location_id     TEXT NOT NULL REFERENCES locations(location_id),
                    capacity             INTEGER,
                    availability_status  TEXT
                );
                """;

        String serviceRequests = """
                CREATE TABLE IF NOT EXISTS service_requests (
                    request_id               TEXT PRIMARY KEY,
                    source_location_id       TEXT NOT NULL REFERENCES locations(location_id),
                    destination_location_id  TEXT NOT NULL REFERENCES locations(location_id),
                    category                 TEXT,
                    urgency                  INTEGER,
                    time_submitted           TEXT,
                    deadline                 TEXT,
                    status                   TEXT
                );
                """;

        String teamParameters = """
                CREATE TABLE IF NOT EXISTS team_parameters (
                    member_name          TEXT PRIMARY KEY,
                    index_number         TEXT,
                    assigned_role        TEXT,
                    priority_weight      INTEGER,
                    route_penalty_factor REAL,
                    hash_table_size      INTEGER,
                    random_seed          INTEGER
                );
                """;

        try (Statement stmt = conn.createStatement()) {
            // locations first: roads/resources/service_requests reference it
            stmt.execute(locations);
            stmt.execute(roads);
            stmt.execute(resources);
            stmt.execute(serviceRequests);
            stmt.execute(teamParameters);
        }
    }

    /** Drops all tables. Handy for tests that want a clean slate. */
    public static void dropTables(Connection conn) throws SQLException {
        String[] tables = {
                "service_requests", "resources", "roads", "locations", "team_parameters"
        };
        try (Statement stmt = conn.createStatement()) {
            for (String table : tables) {
                stmt.execute("DROP TABLE IF EXISTS " + table + ";");
            }
        }
    }
}