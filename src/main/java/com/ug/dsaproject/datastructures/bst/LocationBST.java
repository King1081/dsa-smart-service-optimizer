package com.ug.dsaproject.datastructures.bst;

import java.util.ArrayList;
import java.util.List;

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
        root = insert(root, location);
    }

    private Node insert(Node node, Location location) {
        if (node == null) {
            return new Node(location);
        }

        int comparison = location.getLocationId().compareTo(node.data.getLocationId());
        if (comparison < 0) {
            node.left = insert(node.left, location);
        } else if (comparison > 0) {
            node.right = insert(node.right, location);
        } else {
            node.data = location;
        }
        return node;
    }

    public Location find(String locationId) {
        Node node = find(root, locationId);
        return node == null ? null : node.data;
    }

    private Node find(Node node, String locationId) {
        if (node == null) {
            return null;
        }

        int comparison = locationId.compareTo(node.data.getLocationId());
        if (comparison < 0) {
            return find(node.left, locationId);
        } else if (comparison > 0) {
            return find(node.right, locationId);
        }
        return node;
    }

    public void delete(String locationId) {
        root = delete(root, locationId);
    }

    private Node delete(Node node, String locationId) {
        if (node == null) {
            return null;
        }

        int comparison = locationId.compareTo(node.data.getLocationId());
        if (comparison < 0) {
            node.left = delete(node.left, locationId);
        } else if (comparison > 0) {
            node.right = delete(node.right, locationId);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node successor = minValueNode(node.right);
            node.data = successor.data;
            node.right = delete(node.right, successor.data.getLocationId());
        }
        return node;
    }

    private Node minValueNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public List<Location> inorderTraversal() {
        List<Location> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }

    private void inorderTraversal(Node node, List<Location> result) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, result);
        result.add(node.data);
        inorderTraversal(node.right, result);
    }
}
