package com.ug.dsaproject.datastructures.hashtable;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * OWNER: Khalid Fahd
 * Aim for 3-4 tests here toward the group's 40+ total.
 */
class ResourceHashTableTest {

    @Test
    void getOnEmptyTableReturnsNull() {
        ResourceHashTable table = new ResourceHashTable(101);
        assertNull(table.get("V001"));
    }

    // TODO: put then get returns the same resource
    // TODO: collision handling — two keys that hash to the same bucket both retrievable
    // TODO: remove() then get() returns null
}
