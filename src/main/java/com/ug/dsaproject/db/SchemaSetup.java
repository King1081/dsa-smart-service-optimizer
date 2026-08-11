package com.ug.dsaproject.db;

/**
 * OWNER: Nigel Miranda Sedem Kedman
 * ROLE: DB schema + Integration/Testing lead
 *
 * Suggested schema (adjust to your chosen DB — SQLite is easiest for a
 * student project, no server setup required):
 *
 * CREATE TABLE locations (
 *     location_id     TEXT PRIMARY KEY,
 *     name            TEXT NOT NULL,
 *     area            TEXT,
 *     location_type   TEXT,
 *     x_coord         REAL,
 *     y_coord         REAL
 * );
 *
 * CREATE TABLE roads (
 *     road_id           TEXT PRIMARY KEY,
 *     from_location_id  TEXT NOT NULL REFERENCES locations(location_id),
 *     to_location_id    TEXT NOT NULL REFERENCES locations(location_id),
 *     distance_km       REAL,
 *     travel_time_min   INTEGER,
 *     condition_weight  REAL
 * );
 *
 * CREATE TABLE resources (
 *     resource_id          TEXT PRIMARY KEY,
 *     resource_type        TEXT,
 *     home_location_id     TEXT NOT NULL REFERENCES locations(location_id),
 *     capacity              INTEGER,
 *     availability_status  TEXT
 * );
 *
 * CREATE TABLE service_requests (
 *     request_id               TEXT PRIMARY KEY,
 *     source_location_id       TEXT NOT NULL REFERENCES locations(location_id),
 *     destination_location_id  TEXT NOT NULL REFERENCES locations(location_id),
 *     category                 TEXT,
 *     urgency                  INTEGER,
 *     time_submitted            TEXT,
 *     deadline                 TEXT,
 *     status                   TEXT
 * );
 *
 * TODO:
 *  1. Pick a DB (SQLite recommended for simplicity — add
 *     org.xerial:sqlite-jdbc to pom.xml)
 *  2. Implement connect() and createTables() below
 *  3. Write a loader that reads data/*.csv and inserts into these tables
 *     (or reuse CsvLoader + insert the resulting objects)
 *  4. Coordinate with everyone once their module works in-memory — your
 *     job is wiring their structures to persist/read from this DB
 */
public class SchemaSetup {

    public static void createTables(/* Connection conn */) {
        // TODO: implement CREATE TABLE statements above
    }
}
