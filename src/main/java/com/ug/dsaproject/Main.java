package com.ug.dsaproject;

import com.ug.dsaproject.datastructures.graph.CampusGraph;
import com.ug.dsaproject.model.*;
import com.ug.dsaproject.util.CsvLoader;

import java.util.List;

/**
 * Entry point — demonstrates that the shared dataset loads correctly.
 * Everyone can run this to sanity-check their environment before building
 * their own module. Extend the main() method to wire in your structure
 * once it's ready; don't commit changes here without telling the group
 * since everyone touches this file eventually.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        List<Location> locations = CsvLoader.loadLocations("data/locations.csv");
        List<Road> roads = CsvLoader.loadRoads("data/roads.csv");
        List<Resource> resources = CsvLoader.loadResources("data/resources.csv");
        List<ServiceRequest> requests = CsvLoader.loadServiceRequests("data/service_requests.csv");
        List<TeamParameter> params = CsvLoader.loadTeamParameters("data/team_parameters.csv");

        System.out.println("Locations loaded: " + locations.size());
        System.out.println("Roads loaded: " + roads.size());
        System.out.println("Resources loaded: " + resources.size());
        System.out.println("Service requests loaded: " + requests.size());
        System.out.println("Team parameters loaded: " + params.size());

        CampusGraph graph = new CampusGraph();
        for (Location loc : locations) {
            graph.addVertex(loc.getLocationId());
        }
        for (Road road : roads) {
            graph.addRoad(road);
        }
        System.out.println("Graph built: " + graph.vertexCount() + " vertices");

        // TODO: each owner wires in their module below as it's ready, e.g.
        //   UrgentRequestHeap heap = new UrgentRequestHeap(requests.size(), myPriorityWeight);
        //   for (ServiceRequest r : requests) heap.insert(r);
    }
}
