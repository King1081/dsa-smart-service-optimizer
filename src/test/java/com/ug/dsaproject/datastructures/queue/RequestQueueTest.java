package com.ug.dsaproject.datastructures.queue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * OWNER: Joseph Kobina Acquah
 * Aim for 3-4 tests here toward the group's 40+ total.
 */
class RequestQueueTest {

    @Test
    void newQueueIsEmpty() {
        RequestQueue queue = new RequestQueue(10);
        assertTrue(queue.isEmpty());
    }

    // TODO: enqueue then dequeue returns requests in FIFO order
    // TODO: circular wraparound works after multiple enqueue/dequeue cycles
    // TODO: dequeue on empty queue — decide and test expected behavior
}
