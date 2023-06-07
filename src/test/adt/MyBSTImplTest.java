package test.adt;

import uy.edu.um.prog2.adt.bst.MyBST;
import uy.edu.um.prog2.adt.bst.MyBSTImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyBSTImplTest {
    MyBST<Integer> bst;

    @BeforeEach
    void setUp() {
        bst = new MyBSTImpl<>();
    }

    @AfterEach
    void tearDown() {
        bst.clear();
    }

    @Test
    void testAddAndContains() {
        assertAll(
                () -> assertTrue(bst.add(1)),
                () -> assertTrue(bst.add(2)),
                () -> assertTrue(bst.add(3)),
                () -> assertTrue(bst.add(4)),

                () -> assertTrue(bst.contains(1)),
                () -> assertTrue(bst.contains(2)),
                () -> assertTrue(bst.contains(3)),
                () -> assertTrue(bst.contains(4)),

                () -> assertFalse(bst.contains(5)),
                () -> assertFalse(bst.contains(6)),
                () -> assertFalse(bst.contains(7)),
                () -> assertFalse(bst.contains(8))
        );
    }

    @Test
    void testAddAllAndContainsAll() {
        MyBST<Integer> secondBST = new MyBSTImpl<>();
        MyBST<Integer> thirdBST = new MyBSTImpl<>();
        assertAll(
                () -> assertTrue(bst.add(1)),
                () -> assertTrue(bst.add(2)),

                () -> assertTrue(secondBST.add(3)),
                () -> assertTrue(secondBST.add(4)),

                () -> assertTrue(thirdBST.add(1)),
                () -> assertTrue(thirdBST.add(2)),
                () -> assertTrue(thirdBST.add(3)),
                () -> assertTrue(thirdBST.add(4)),

                () -> assertTrue(bst.addAll(secondBST)),

                () -> assertTrue(bst.containsAll(thirdBST))
        );
    }

    @Test
    void testClearAndIsEmpty() {
        assertAll(
                () -> assertTrue(bst.add(1)),
                () -> assertTrue(bst.add(2)),
                () -> {
                    bst.clear();
                    assertTrue(bst.isEmpty());
                }
        );
    }

    @Test
    void testEquals() {
        MyBST<Integer> otherBST = new MyBSTImpl<>();
        assertAll(
                () -> assertTrue(bst.add(1)),
                () -> assertTrue(bst.add(2)),

                () -> assertTrue(otherBST.add(1)),
                () -> assertTrue(otherBST.add(2)),

                () -> assertEquals(bst, otherBST)
        );
    }

    @Test
    void testInorderIterator() {
    }

    @Test
    void testPreorderIterator() {
    }

    @Test
    void testPostorderIterator() {
    }

    @Test
    void testMaximum() {
        assertAll(
                () -> assertTrue(bst.add(1)),
                () -> assertTrue(bst.add(2)),
                () -> assertTrue(bst.add(3)),
                () -> assertTrue(bst.add(4)),

                () -> assertEquals(4, bst.maximum())
        );
    }

    @Test
    void testMinimum() {
        assertAll(
                () -> assertTrue(bst.add(1)),
                () -> assertTrue(bst.add(2)),
                () -> assertTrue(bst.add(3)),
                () -> assertTrue(bst.add(4)),

                () -> assertEquals(1, bst.minimum())
        );
    }

    @Test
    void testPredecessor() {
        assertAll(
                () -> assertTrue(bst.add(1)),
                () -> assertTrue(bst.add(2)),
                () -> assertTrue(bst.add(3)),
                () -> assertTrue(bst.add(4)),

                () -> assertNull(bst.predecessor(1)),
                () -> assertEquals(1, bst.predecessor(2)),
                () -> assertEquals(2, bst.predecessor(3)),
                () -> assertEquals(3, bst.predecessor(4))
        );
    }

    @Test
    void testRemove() {
        assertAll(
                () -> assertTrue(bst.add(1)),
                () -> assertTrue(bst.add(2)),
                () -> assertTrue(bst.add(3)),
                () -> assertTrue(bst.add(4)),

                () -> assertTrue(bst.remove(1)),
                () -> assertTrue(bst.remove(2)),
                () -> assertTrue(bst.remove(3)),
                () -> assertTrue(bst.remove(4)),

                () -> assertTrue(bst.isEmpty())
        );
    }

    @Test
    void testRemoveAll() {
        MyBST<Integer> otherBST = new MyBSTImpl<>();
        assertAll(
                () -> assertTrue(bst.add(1)),
                () -> assertTrue(bst.add(2)),
                () -> assertTrue(bst.add(3)),
                () -> assertTrue(bst.add(4)),

                () -> assertTrue(otherBST.add(1)),
                () -> assertTrue(otherBST.add(2)),
                () -> assertTrue(otherBST.add(3)),
                () -> assertTrue(otherBST.add(4)),

                () -> assertTrue(bst.removeAll(otherBST)),

                () -> assertTrue(bst.isEmpty())
        );
    }

    @Test
    void testRemoveMaximum() {
        assertAll(
                () -> assertTrue(bst.add(1)),
                () -> assertTrue(bst.add(2)),
                () -> assertTrue(bst.add(3)),
                () -> assertTrue(bst.add(4)),

                () -> assertEquals(4, bst.removeMaximum()),
                () -> assertEquals(3, bst.removeMaximum()),
                () -> assertEquals(2, bst.removeMaximum()),
                () -> assertEquals(1, bst.removeMaximum())
        );
    }

    @Test
    void testRemoveMinimum() {
        assertAll(
                () -> assertTrue(bst.add(1)),
                () -> assertTrue(bst.add(2)),
                () -> assertTrue(bst.add(3)),
                () -> assertTrue(bst.add(4)),

                () -> assertEquals(1, bst.removeMinimum()),
                () -> assertEquals(2, bst.removeMinimum()),
                () -> assertEquals(3, bst.removeMinimum()),
                () -> assertEquals(4, bst.removeMinimum())
        );
    }

    @Test
    void testSize() {
        assertAll(
                () -> assertEquals(0, bst.size()),

                () -> assertTrue(bst.add(1)),
                () -> assertEquals(1, bst.size()),

                () -> assertTrue(bst.add(2)),
                () -> assertEquals(2, bst.size()),

                () -> assertTrue(bst.add(3)),
                () -> assertEquals(3, bst.size()),

                () -> assertTrue(bst.add(4)),
                () -> assertEquals(4, bst.size())
        );
    }

    @Test
    void testSuccessor() {
        assertAll(
                () -> assertTrue(bst.add(1)),
                () -> assertTrue(bst.add(2)),
                () -> assertTrue(bst.add(3)),
                () -> assertTrue(bst.add(4)),

                () -> assertNull(bst.successor(4)),
                () -> assertEquals(4, bst.successor(3)),
                () -> assertEquals(3, bst.successor(2)),
                () -> assertEquals(2, bst.successor(1))
        );
    }

    @Test
    void toArray() {
    }
}