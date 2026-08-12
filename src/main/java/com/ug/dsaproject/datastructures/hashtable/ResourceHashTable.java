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
 * - put(String resourceId, Resource resource)
 * - get(String resourceId)
 * - remove(String resourceId)
 * - your own hash(String key) function
 */
public class ResourceHashTable {

    private static class Entry {
        String key;
        Resource value;
        Entry next; // for chaining

        Entry(String key, Resource value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] table;
    private final int tableSize; // from your team_parameters.csv row

    public ResourceHashTable(int tableSize) {
        this.tableSize = tableSize;
        table = new Entry[tableSize];
    }

    private int hash(String key) {
        int hash = 0;

        for (int i = 0; i < key.length(); i++) {
            hash = (31 * hash + key.charAt(i)) % tableSize;
        }
        return hash;
    }

    public void put(String resourceId, Resource resource) {
        int index = hash(resourceId);
        Entry current = table[index];

        while (current != null) {
            if (current.key.equals(resourceId)) {
                current.value = resource;
                return;
            }
            current = current.next;
        }
        Entry newEntry = new Entry(resourceId, resource);
        newEntry.next = table[index];
        table[index] = newEntry;
    }

    public Resource get(String resourceId) {
        int index = hash(resourceId);
        Entry current = table[index];

        while (current != null) {
            if (current.key.equals(resourceId)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void remove(String resourceId) {
        int index = hash(resourceId);
        Entry current = table[index];
        Entry previous = null;

        while (current != null) {
            if (current.key.equals(resourceId)) {
                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous = current.next;
                }
                return;
            }
            previous = current;
            current = current.next;
        }
    }
}
