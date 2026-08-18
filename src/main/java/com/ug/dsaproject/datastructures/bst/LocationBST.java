package com.ug.dsaproject.datastructures.bst;

import com.ug.dsaproject.model.Location;

/**
 * OWNER: Marfo Kwadwo Ampofo Senior
 * ROLE: Binary Search Tree — location/resource lookup by ID
 *
 * Build this as a custom BST keyed on Location.getLocationId() (string
 * comparison, e.g. "L001" < "L002").
 *
 * Suggested use in this project: fast O(log n) lookup of a Location by its
 * ID instead of scanning the full locations list.
 *
 * Minimum operations to implement:
 *  - insert(Location)
 *  - find(String locationId)
 *  - delete(String locationId)
 *  - inorderTraversal() — useful for producing a sorted trace table
 */
public class LocationBST {

    private static class Node {
        Location data;
        Node left, right;
        Node(Location data) { this.data = data; }
    }

    private Node root;

    public void insert(Location location) {
        // TODO: implement
    }

    public Location find(String locationId) {
        // TODO: implement
        return null;
    }

    public void delete(String locationId) {
        // TODO: implement (handle 0/1/2-child cases)
    }
}
