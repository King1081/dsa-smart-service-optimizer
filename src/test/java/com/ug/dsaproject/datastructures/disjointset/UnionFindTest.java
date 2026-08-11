package com.ug.dsaproject.datastructures.disjointset;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * OWNER: Akubia Edith Elorm
 * Aim for 3-4 tests here toward the group's 40+ total.
 */
class UnionFindTest {

    @Test
    void newSetIsSelfConnected() {
        UnionFind uf = new UnionFind();
        uf.makeSet("L001");
        assertTrue(uf.connected("L001", "L001"));
    }

    // TODO: union() two sets, connected() returns true afterward
    // TODO: union() on an already-connected pair returns false (cycle detected)
    // TODO: find() with path compression still returns the correct root
}
