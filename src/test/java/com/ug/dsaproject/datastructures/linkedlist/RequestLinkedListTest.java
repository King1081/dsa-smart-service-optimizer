package com.ug.dsaproject.datastructures.linkedlist;

import com.ug.dsaproject.model.ServiceRequest;
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

    @Test
    void addLastThenRemoveFirstReturnsTheSameRequest() {
        RequestLinkedList list = new RequestLinkedList();
        ServiceRequest request = new ServiceRequest(
                "R001", "L1", "L2", "repair", 3,
                java.time.LocalDateTime.of(2026, 8, 1, 9, 0),
                java.time.LocalDateTime.of(2026, 8, 1, 10, 0),
                "NEW"
        );

        list.addLast(request);

        assertEquals(request, list.removeFirst());
        assertTrue(list.isEmpty());
    }

    @Test
    void undoRevertsTheMostRecentOperation() {
        RequestLinkedList list = new RequestLinkedList();
        ServiceRequest first = new ServiceRequest(
                "R001", "L1", "L2", "repair", 3,
                java.time.LocalDateTime.of(2026, 8, 1, 9, 0),
                java.time.LocalDateTime.of(2026, 8, 1, 10, 0),
                "NEW"
        );
        ServiceRequest second = new ServiceRequest(
                "R002", "L2", "L3", "cleaning", 5,
                java.time.LocalDateTime.of(2026, 8, 1, 9, 5),
                java.time.LocalDateTime.of(2026, 8, 1, 10, 5),
                "NEW"
        );

        list.addLast(first);
        list.addLast(second);
        list.undo();

        assertEquals(1, list.size());
        assertEquals(first, list.removeFirst());
    }

    @Test
    void removeFirstOnEmptyListReturnsNull() {
        RequestLinkedList list = new RequestLinkedList();
        assertNull(list.removeFirst());
    }
}
