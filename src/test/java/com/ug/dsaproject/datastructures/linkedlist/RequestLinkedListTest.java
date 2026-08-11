package com.ug.dsaproject.datastructures.linkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * OWNER: Josephine Tetteh
 * Aim for 3-4 tests here toward the group's 40+ total.
 * Cover: empty list behavior, add/remove, undo, and at least one edge case.
 */
class RequestLinkedListTest {

    @Test
    void newListIsEmpty() {
        RequestLinkedList list = new RequestLinkedList();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    // TODO: addLast then removeFirst returns the same request
    // TODO: undo() reverts the most recent operation
    // TODO: removeFirst() on empty list — decide and test the expected behavior
}
