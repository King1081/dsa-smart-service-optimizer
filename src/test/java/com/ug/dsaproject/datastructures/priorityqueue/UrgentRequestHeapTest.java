package com.ug.dsaproject.datastructures.priorityqueue;

import com.ug.dsaproject.model.ServiceRequest;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

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
        assertEquals(0, heap.size());
        assertNull(heap.peek());
        assertNull(heap.extractMax());
    }

    @Test
    void testInsertAndExtractMaxOrderingByUrgency() {
        UrgentRequestHeap heap = new UrgentRequestHeap(10, 5);
        LocalDateTime now = LocalDateTime.now();
        
        ServiceRequest r1 = new ServiceRequest("1", "A", "B", "Cat1", 1, now, now.plusDays(1), "NEW");
        ServiceRequest r3 = new ServiceRequest("3", "A", "B", "Cat1", 3, now, now.plusDays(1), "NEW");
        ServiceRequest r5 = new ServiceRequest("5", "A", "B", "Cat1", 5, now, now.plusDays(1), "NEW");
        ServiceRequest r2 = new ServiceRequest("2", "A", "B", "Cat1", 2, now, now.plusDays(1), "NEW");

        heap.insert(r1);
        heap.insert(r3);
        heap.insert(r5);
        heap.insert(r2);

        assertEquals(4, heap.size());
        assertEquals("5", heap.extractMax().getRequestId());
        assertEquals("3", heap.extractMax().getRequestId());
        assertEquals("2", heap.extractMax().getRequestId());
        assertEquals("1", heap.extractMax().getRequestId());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testTieBreaking() {
        // Priority weight = 5
        UrgentRequestHeap heap = new UrgentRequestHeap(10, 5);
        LocalDateTime now = LocalDateTime.now();

        // Same urgency, different deadlines.
        // r2 has an earlier deadline, so it should be prioritized.
        ServiceRequest r1 = new ServiceRequest("1", "A", "B", "Cat", 3, now, now.plusHours(5), "NEW");
        ServiceRequest r2 = new ServiceRequest("2", "A", "B", "Cat", 3, now, now.plusHours(2), "NEW");
        
        heap.insert(r1);
        heap.insert(r2);

        assertEquals("2", heap.extractMax().getRequestId());
        assertEquals("1", heap.extractMax().getRequestId());
    }

    @Test
    void testTieBreakingWithSameDeadline() {
        UrgentRequestHeap heap = new UrgentRequestHeap(10, 5);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deadline = now.plusHours(5);

        // Same urgency, same deadline, different submission time.
        // r2 submitted earlier, so it should be prioritized.
        ServiceRequest r1 = new ServiceRequest("1", "A", "B", "Cat", 3, now.plusMinutes(10), deadline, "NEW");
        ServiceRequest r2 = new ServiceRequest("2", "A", "B", "Cat", 3, now, deadline, "NEW");

        heap.insert(r1);
        heap.insert(r2);

        assertEquals("2", heap.extractMax().getRequestId());
        assertEquals("1", heap.extractMax().getRequestId());
    }

    @Test
    void extractMaxOnEmptyHeap() {
        UrgentRequestHeap heap = new UrgentRequestHeap(10, 5);
        assertNull(heap.extractMax());
    }
}
