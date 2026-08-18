package com.ug.dsaproject.datastructures.hashtable;

import com.ug.dsaproject.model.Resource;

/**
 * OWNER: Khalid Fahd
 * ROLE: Hash Table — fast request/resource lookup
 *
 * Build this as a custom hash table with your own hash function and
 * collision handling (chaining or open addressing) — do NOT use
 * java.util.HashMap for the core logic.
 *
 * Your derived parameter (from team_parameters.csv, hash_table_size=101)
 * should be used as the initial table size — it's prime, which reduces
 * clustering for common hash functions.
 *
 * Minimum operations to implement:
 *  - put(String resourceId, Resource resource)
 *  - get(String resourceId)
 *  - remove(String resourceId)
 *  - your own hash(String key) function
 */
public class ResourceHashTable {

    private static class Entry {
        String key;
        Resource value;
        Entry next; // for chaining
        Entry(String key, Resource value) { this.key = key; this.value = value; }
    }

    private Entry[] table;
    private final int tableSize; // from your team_parameters.csv row

    public ResourceHashTable(int tableSize) {
        this.tableSize = tableSize;
        table = new Entry[tableSize];
    }

    private int hash(String key) {
        // TODO: implement your own hash function, e.g. polynomial rolling hash
        return 0;
    }

    public void put(String resourceId, Resource resource) {
        // TODO: implement
    }

    public Resource get(String resourceId) {
        // TODO: implement
        return null;
    }

    public void remove(String resourceId) {
        // TODO: implement
    }
}
