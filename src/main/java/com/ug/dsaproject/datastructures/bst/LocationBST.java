package com.ug.dsaproject.datastructures.bst;

import com.ug.dsaproject.model.Location;
import java.util.ArrayList;
import java.util.List;

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
        root = insertRec(root, location);
    }

    private Node insertRec(Node node, Location location) {
        if (node == null) return new Node(location);
        String key = location.getLocationId();
        String nodeKey = node.data.getLocationId();
        if (key.compareTo(nodeKey) < 0) {
            node.left = insertRec(node.left, location);
        } else if (key.compareTo(nodeKey) > 0) {
            node.right = insertRec(node.right, location);
        } else {
            node.data = location; // replace existing
        }
        return node;
    }

    public Location find(String locationId) {
        Node cur = root;
        while (cur != null) {
            int cmp = locationId.compareTo(cur.data.getLocationId());
            if (cmp == 0) return cur.data;
            if (cmp < 0) cur = cur.left; else cur = cur.right;
        }
        return null;
    }

    public void delete(String locationId) {
        root = deleteRec(root, locationId);
    }

    private Node deleteRec(Node node, String key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.data.getLocationId());
        if (cmp < 0) node.left = deleteRec(node.left, key);
        else if (cmp > 0) node.right = deleteRec(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // two children: find inorder successor
            Node succ = node.right;
            while (succ.left != null) succ = succ.left;
            node.data = succ.data;
            node.right = deleteRec(node.right, succ.data.getLocationId());
        }
        return node;
    }

    public List<Location> inorderTraversal() {
        List<Location> res = new ArrayList<>();
        inorderRec(root, res);
        return res;
    }

    private void inorderRec(Node node, List<Location> out) {
        if (node == null) return;
        inorderRec(node.left, out);
        out.add(node.data);
        inorderRec(node.right, out);
    }
}
