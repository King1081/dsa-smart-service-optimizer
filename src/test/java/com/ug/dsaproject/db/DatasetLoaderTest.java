package com.ug.dsaproject.db;

import com.ug.dsaproject.model.Location;
import com.ug.dsaproject.util.CsvLoader;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OWNER: Nigel Miranda Sedem Kedman
 * Integration tests: CSV -> SQLite -> read-back round trip, run against an
 * in-memory DB. Assumes tests run from the project root (Maven's default
 * working directory) so "data/*.csv" resolves the same way CsvLoader
 * expects everywhere else in the project.
 */
class DatasetLoaderTest {

    @Test
    void loadCsvIntoDbThenReadBackMatchesCsvCounts() throws SQLException, java.io.IOException {
        try (Connection conn = SchemaSetup.connect(":memory:")) {
            SchemaSetup.createTables(conn);
            DatasetLoader.loadCsvIntoDb(conn, "data");

            List<Location> csvLocations = CsvLoader.loadLocations("data/locations.csv");
            List<Location> dbLocations = DatasetLoader.loadLocationsFromDb(conn);

            assertEquals(csvLocations.size(), dbLocations.size());
            assertEquals(50, dbLocations.size()); // per README: locations.csv has 50 rows
        }
    }

    @Test
    void loadCsvIntoDbPersistsRoadsResourcesAndRequests() throws SQLException, java.io.IOException {
        try (Connection conn = SchemaSetup.connect(":memory:")) {
            SchemaSetup.createTables(conn);
            DatasetLoader.loadCsvIntoDb(conn, "data");

            assertEquals(100, DatasetLoader.loadRoadsFromDb(conn).size());
            assertEquals(30, DatasetLoader.loadResourcesFromDb(conn).size());
            assertEquals(300, DatasetLoader.loadServiceRequestsFromDb(conn).size());
        }
    }

    @Test
    void insertLocationsThenReadBackPreservesFields() throws SQLException {
        try (Connection conn = SchemaSetup.connect(":memory:")) {
            SchemaSetup.createTables(conn);

            Location loc = new Location("L999", "Test Hall", "Legon", "Hostel", 5.65, 0.17);
            DatasetLoader.insertLocations(conn, List.of(loc));

            List<Location> result = DatasetLoader.loadLocationsFromDb(conn);
            assertEquals(1, result.size());
            Location fetched = result.get(0);
            assertEquals("L999", fetched.getLocationId());
            assertEquals("Test Hall", fetched.getName());
            assertEquals(5.65, fetched.getXCoord(), 0.0001);
        }
    }

    @Test
    void insertLocationsWithSameIdReplacesNotDuplicates() throws SQLException {
        try (Connection conn = SchemaSetup.connect(":memory:")) {
            SchemaSetup.createTables(conn);

            DatasetLoader.insertLocations(conn, List.of(
                    new Location("L500", "Old Name", "Legon", "Hostel", 1.0, 1.0)));
            DatasetLoader.insertLocations(conn, List.of(
                    new Location("L500", "New Name", "Legon", "Hostel", 1.0, 1.0)));

            List<Location> result = DatasetLoader.loadLocationsFromDb(conn);
            assertEquals(1, result.size(), "INSERT OR REPLACE should not create a duplicate row");
            assertEquals("New Name", result.get(0).getName());
        }
    }
}