package com.ug.dsaproject.datastructures.stack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * OWNER: Marie-Anne Dzifa Hayibor
 * Aim for 3-4 tests here toward the group's 40+ total.
 */
class RouteStackTest {

    @Test
    void newStackIsEmpty() {
        RouteStack stack = new RouteStack(10);
        assertTrue(stack.isEmpty());
    }

    // TODO: push then peek returns same location without removing it
    // TODO: push then pop returns same location and removes it
    // TODO: push beyond initial capacity resizes correctly
}
