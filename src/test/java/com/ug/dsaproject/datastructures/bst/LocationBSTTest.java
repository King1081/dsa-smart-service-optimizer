package com.ug.dsaproject.datastructures.bst;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

    // TODO: insert several locations, find() retrieves the correct one
    // TODO: delete() a leaf, a one-child, and a two-child node
    // TODO: inorderTraversal() returns locations in sorted ID order
}
