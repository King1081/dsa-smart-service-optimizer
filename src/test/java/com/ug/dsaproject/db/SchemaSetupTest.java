package com.ug.dsaproject.db;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OWNER: Nigel Miranda Sedem Kedman
 * Uses an in-memory SQLite DB (":memory:") so tests never touch a real
 * .db file on disk and each test gets a clean slate.
 */
class SchemaSetupTest {

    @Test
    void connectReturnsOpenConnection() throws SQLException {
        try (Connection conn = SchemaSetup.connect(":memory:")) {
            assertFalse(conn.isClosed());
        }
    }

    @Test
    void createTablesCreatesAllFiveTables() throws SQLException {
        try (Connection conn = SchemaSetup.connect(":memory:")) {
            SchemaSetup.createTables(conn);

            String[] expected = {
                    "locations", "roads", "resources", "service_requests", "team_parameters"
            };
            try (Statement stmt = conn.createStatement()) {
                for (String table : expected) {
                    try (ResultSet rs = stmt.executeQuery(
                            "SELECT name FROM sqlite_master WHERE type='table' AND name='" + table + "'")) {
                        assertTrue(rs.next(), "expected table " + table + " to exist");
                    }
                }
            }
        }
    }

    @Test
    void createTablesIsIdempotent() throws SQLException {
        // calling createTables twice should not throw (CREATE TABLE IF NOT EXISTS)
        try (Connection conn = SchemaSetup.connect(":memory:")) {
            SchemaSetup.createTables(conn);
            assertDoesNotThrow(() -> SchemaSetup.createTables(conn));
        }
    }

    @Test
    void dropTablesRemovesThem() throws SQLException {
        try (Connection conn = SchemaSetup.connect(":memory:")) {
            SchemaSetup.createTables(conn);
            SchemaSetup.dropTables(conn);

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(
                         "SELECT name FROM sqlite_master WHERE type='table' AND name='locations'")) {
                assertFalse(rs.next());
            }
        }
    }
}