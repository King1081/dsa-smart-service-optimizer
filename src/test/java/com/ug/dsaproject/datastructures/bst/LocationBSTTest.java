package com.ug.dsaproject.datastructures.bst;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

import com.ug.dsaproject.model.Location;

/**
 * OWNER: Marfo Kwadwo Ampofo Senior
 * Aim for 3-4 tests here toward the group's 40+ total.
 */
class LocationBSTTest {

    @Test
    void findOnEmptyTreeReturnsNull() {
        LocationBST bst = new LocationBST();
        assertNull(bst.find("L001"));
    }

    @Test
    void insertAndFindRetrievesCorrectLocations() {
        LocationBST bst = new LocationBST();
        Location location1 = new Location("L001", "Main Hall", "North", "Building", 0.0, 0.0);
        Location location2 = new Location("L003", "Library", "East", "Building", 1.0, 1.0);
        Location location3 = new Location("L002", "Cafeteria", "West", "Facility", 2.0, 2.0);

        bst.insert(location1);
        bst.insert(location2);
        bst.insert(location3);

        assertSame(location1, bst.find("L001"));
        assertSame(location3, bst.find("L002"));
        assertSame(location2, bst.find("L003"));
        assertNull(bst.find("L999"));
    }

    @Test
    void deleteLeafOneChildAndTwoChildNodes() {
        LocationBST bst = new LocationBST();
        Location root = new Location("L003", "Root", "Zone", "Campus", 0.0, 0.0);
        Location left = new Location("L001", "Left", "Zone", "Campus", 1.0, 0.0);
        Location leftRight = new Location("L002", "LeftRight", "Zone", "Campus", 2.0, 0.0);
        Location right = new Location("L005", "Right", "Zone", "Campus", 3.0, 0.0);
        Location rightLeft = new Location("L004", "RightLeft", "Zone", "Campus", 4.0, 0.0);

        bst.insert(root);
        bst.insert(left);
        bst.insert(leftRight);
        bst.insert(right);
        bst.insert(rightLeft);

        bst.delete("L002");
        assertNull(bst.find("L002"));
        assertNotNull(bst.find("L001"));

        bst.delete("L005");
        assertNull(bst.find("L005"));
        assertNotNull(bst.find("L004"));

        bst.delete("L003");
        assertNull(bst.find("L003"));
        assertNotNull(bst.find("L001"));
        assertNotNull(bst.find("L004"));
    }

    @Test
    void inorderTraversalReturnsLocationsInSortedIdOrder() {
        LocationBST bst = new LocationBST();
        bst.insert(new Location("L005", "E", "Area", "Type", 0.0, 0.0));
        bst.insert(new Location("L001", "A", "Area", "Type", 0.0, 0.0));
        bst.insert(new Location("L004", "D", "Area", "Type", 0.0, 0.0));
        bst.insert(new Location("L002", "B", "Area", "Type", 0.0, 0.0));
        bst.insert(new Location("L003", "C", "Area", "Type", 0.0, 0.0));

        List<Location> sorted = bst.inorderTraversal();

        assertEquals(5, sorted.size());
        assertEquals("L001", sorted.get(0).getLocationId());
        assertEquals("L002", sorted.get(1).getLocationId());
        assertEquals("L003", sorted.get(2).getLocationId());
        assertEquals("L004", sorted.get(3).getLocationId());
        assertEquals("L005", sorted.get(4).getLocationId());
    }
}