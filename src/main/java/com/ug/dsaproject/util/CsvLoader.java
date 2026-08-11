package com.ug.dsaproject.util;

import com.ug.dsaproject.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads the four shared dataset CSVs (plus team_parameters.csv) into model
 * objects. Everyone should use this instead of writing their own CSV parsing
 * — one source of truth avoids 12 slightly-different bugs.
 *
 * Expects the CSVs to live in the data/ folder at project root, matching the
 * headers of the templates you were given.
 */
public class CsvLoader {

    private static final DateTimeFormatter DT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public static List<Location> loadLocations(String path) throws IOException {
        List<Location> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] p = line.split(",", -1);
                result.add(new Location(
                        p[0], p[1], p[2], p[3],
                        Double.parseDouble(p[4]), Double.parseDouble(p[5])
                ));
            }
        }
        return result;
    }

    public static List<Road> loadRoads(String path) throws IOException {
        List<Road> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] p = line.split(",", -1);
                result.add(new Road(
                        p[0], p[1], p[2],
                        Double.parseDouble(p[3]), Integer.parseInt(p[4]), Double.parseDouble(p[5])
                ));
            }
        }
        return result;
    }

    public static List<Resource> loadResources(String path) throws IOException {
        List<Resource> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] p = line.split(",", -1);
                result.add(new Resource(
                        p[0], p[1], p[2], Integer.parseInt(p[3]), p[4]
                ));
            }
        }
        return result;
    }

    public static List<ServiceRequest> loadServiceRequests(String path) throws IOException {
        List<ServiceRequest> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] p = line.split(",", -1);
                result.add(new ServiceRequest(
                        p[0], p[1], p[2], p[3], Integer.parseInt(p[4]),
                        LocalDateTime.parse(p[5], DT_FORMAT),
                        LocalDateTime.parse(p[6], DT_FORMAT),
                        p[7]
                ));
            }
        }
        return result;
    }

    public static List<TeamParameter> loadTeamParameters(String path) throws IOException {
        List<TeamParameter> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] p = line.split(",", -1);
                result.add(new TeamParameter(
                        p[0], p[1], p[2],
                        Integer.parseInt(p[3]), Double.parseDouble(p[4]),
                        Integer.parseInt(p[5]), Integer.parseInt(p[6])
                ));
            }
        }
        return result;
    }
}
