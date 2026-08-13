package com.ug.dsaproject.datastructures.hashtable;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.ug.dsaproject.model.Resource;

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

    @Test
    void putThenGetSameResource() {
        ResourceHashTable table = new ResourceHashTable(101);
        Resource resource = new Resource("RS050", "Porter", "L004", 1, "Available");

        table.put("RS050", resource);
        assertEquals(resource, table.get("RS050"));
    }

    @Test
    void collisionHandlingTwoKeysSameBucket() {
        ResourceHashTable table = new ResourceHashTable(101);
        Resource r1 = new Resource("RS001", "Shuttle Van", "L029", 18, "AVAILABLE");
        Resource r2 = new Resource("RS039", "Maintenance Cart", "L015", 1, "AVAILABLE");

        table.put("RS001", r1);
        table.put("RS039", r2);

        assertEquals(r1, table.get("RS001"));
        assertEquals(r2, table.get("RS039"));
    }

    @Test
    void removeThenGetSameResource() {
        ResourceHashTable table = new ResourceHashTable(101);
        Resource resource = new Resource("RS050", "Porter", "L004", 1, "Available");

        table.put("RS050", resource);
        table.remove("RS050");
        assertNull(table.get("RS050"));
    }
}
