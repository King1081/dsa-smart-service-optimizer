package com.ug.dsaproject.datastructures.priorityqueue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * OWNER: Vical Divine Aghe
 * Aim for 3-4 tests here toward the group's 40+ total.
 */
class UrgentRequestHeapTest {

    @Test
    void newHeapIsEmpty() {
        UrgentRequestHeap heap = new UrgentRequestHeap(10, 5);
        assertTrue(heap.isEmpty());
    }

    // TODO: insert requests of varying urgency, extractMax returns highest first
    // TODO: tie-breaking between equal-urgency requests uses priorityWeight correctly
    // TODO: extractMax on empty heap — decide and test expected behavior
}
