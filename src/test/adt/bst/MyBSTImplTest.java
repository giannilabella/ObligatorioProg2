package test.adt.bst;

import uy.edu.um.prog2.adt.bst.MyBST;
import uy.edu.um.prog2.adt.bst.MyBSTImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

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
        assertAll(
                () -> assertTrue(bst.add(8)),
                () -> assertTrue(bst.add(4)),
                () -> assertTrue(bst.add(12)),

                () -> assertTrue(bst.add(2)),
                () -> assertTrue(bst.add(6)),

                () -> assertTrue(bst.add(10)),
                () -> assertTrue(bst.add(14)),

                () -> assertTrue(bst.add(1)),
                () -> assertTrue(bst.add(3)),
                () -> assertTrue(bst.add(5)),
                () -> assertTrue(bst.add(7)),

                () -> assertTrue(bst.add(9)),
                () -> assertTrue(bst.add(11)),
                () -> assertTrue(bst.add(13)),
                () -> assertTrue(bst.add(15))
        );
        Iterator<Integer> inorderIterator = bst.inorderIterator();
        assertAll(
                () -> assertEquals(1, inorderIterator.next()),
                () -> assertEquals(2, inorderIterator.next()),
                () -> assertEquals(3, inorderIterator.next()),
                () -> assertEquals(4, inorderIterator.next()),
                () -> assertEquals(5, inorderIterator.next()),
                () -> assertEquals(6, inorderIterator.next()),
                () -> assertEquals(7, inorderIterator.next()),
                () -> assertEquals(8, inorderIterator.next()),
                () -> assertEquals(9, inorderIterator.next()),
                () -> assertEquals(10, inorderIterator.next()),
                () -> assertEquals(11, inorderIterator.next()),
                () -> assertEquals(12, inorderIterator.next()),
                () -> assertEquals(13, inorderIterator.next()),
                () -> assertEquals(14, inorderIterator.next()),
                () -> assertEquals(15, inorderIterator.next()),
                () -> assertFalse(inorderIterator.hasNext())
        );
        Iterator<Integer> otherInorderIterator = bst.inorderIterator();
        assertAll(
                () -> assertEquals(1, otherInorderIterator.next()),
                () -> assertEquals(2, otherInorderIterator.next()),
                () -> assertEquals(3, otherInorderIterator.next()),
                () -> assertEquals(4, otherInorderIterator.next()),
                () -> assertEquals(5, otherInorderIterator.next()),
                () -> assertEquals(6, otherInorderIterator.next()),
                () -> assertEquals(7, otherInorderIterator.next()),
                () -> assertEquals(8, otherInorderIterator.next()),
                () -> assertEquals(9, otherInorderIterator.next()),
                () -> assertEquals(10, otherInorderIterator.next()),
                () -> assertEquals(11, otherInorderIterator.next()),
                () -> assertEquals(12, otherInorderIterator.next()),
                () -> assertEquals(13, otherInorderIterator.next()),
                () -> assertEquals(14, otherInorderIterator.next()),
                () -> assertEquals(15, otherInorderIterator.next()),
                () -> assertFalse(otherInorderIterator.hasNext())
        );
    }

    @Test
    void testPreorderIterator() {
        assertAll(
                () -> assertTrue(bst.add(8)),
                () -> assertTrue(bst.add(4)),
                () -> assertTrue(bst.add(12)),

                () -> assertTrue(bst.add(2)),
                () -> assertTrue(bst.add(6)),

                () -> assertTrue(bst.add(10)),
                () -> assertTrue(bst.add(14)),

                () -> assertTrue(bst.add(1)),
                () -> assertTrue(bst.add(3)),
                () -> assertTrue(bst.add(5)),
                () -> assertTrue(bst.add(7)),

                () -> assertTrue(bst.add(9)),
                () -> assertTrue(bst.add(11)),
                () -> assertTrue(bst.add(13)),
                () -> assertTrue(bst.add(15))
        );
        Iterator<Integer> preorderIterator = bst.preorderIterator();

        assertAll(
                () -> assertEquals(8, preorderIterator.next()),
                () -> assertEquals(4, preorderIterator.next()),
                () -> assertEquals(2, preorderIterator.next()),
                () -> assertEquals(1, preorderIterator.next()),
                () -> assertEquals(3, preorderIterator.next()),
                () -> assertEquals(6, preorderIterator.next()),
                () -> assertEquals(5, preorderIterator.next()),
                () -> assertEquals(7, preorderIterator.next()),
                () -> assertEquals(12, preorderIterator.next()),
                () -> assertEquals(10, preorderIterator.next()),
                () -> assertEquals(9, preorderIterator.next()),
                () -> assertEquals(11, preorderIterator.next()),
                () -> assertEquals(14, preorderIterator.next()),
                () -> assertEquals(13, preorderIterator.next()),
                () -> assertEquals(15, preorderIterator.next()),
                () -> assertFalse(preorderIterator.hasNext())
        );
        Iterator<Integer> otherPreorderIterator = bst.preorderIterator();
        assertAll(
                () -> assertEquals(8, otherPreorderIterator.next()),
                () -> assertEquals(4, otherPreorderIterator.next()),
                () -> assertEquals(2, otherPreorderIterator.next()),
                () -> assertEquals(1, otherPreorderIterator.next()),
                () -> assertEquals(3, otherPreorderIterator.next()),
                () -> assertEquals(6, otherPreorderIterator.next()),
                () -> assertEquals(5, otherPreorderIterator.next()),
                () -> assertEquals(7, otherPreorderIterator.next()),
                () -> assertEquals(12, otherPreorderIterator.next()),
                () -> assertEquals(10, otherPreorderIterator.next()),
                () -> assertEquals(9, otherPreorderIterator.next()),
                () -> assertEquals(11, otherPreorderIterator.next()),
                () -> assertEquals(14, otherPreorderIterator.next()),
                () -> assertEquals(13, otherPreorderIterator.next()),
                () -> assertEquals(15, otherPreorderIterator.next()),
                () -> assertFalse(otherPreorderIterator.hasNext())
        );
    }

    @Test
    void testPostorderIterator() {
        assertAll(
                () -> assertTrue(bst.add(8)),
                () -> assertTrue(bst.add(4)),
                () -> assertTrue(bst.add(12)),

                () -> assertTrue(bst.add(2)),
                () -> assertTrue(bst.add(6)),

                () -> assertTrue(bst.add(10)),
                () -> assertTrue(bst.add(14)),

                () -> assertTrue(bst.add(1)),
                () -> assertTrue(bst.add(3)),
                () -> assertTrue(bst.add(5)),
                () -> assertTrue(bst.add(7)),

                () -> assertTrue(bst.add(9)),
                () -> assertTrue(bst.add(11)),
                () -> assertTrue(bst.add(13)),
                () -> assertTrue(bst.add(15))
        );
        Iterator<Integer> postorderIterator = bst.postorderIterator();
        assertAll(
                () -> assertEquals(15, postorderIterator.next()),
                () -> assertEquals(13, postorderIterator.next()),
                () -> assertEquals(14, postorderIterator.next()),
                () -> assertEquals(11, postorderIterator.next()),
                () -> assertEquals(9, postorderIterator.next()),
                () -> assertEquals(10, postorderIterator.next()),
                () -> assertEquals(12, postorderIterator.next()),
                () -> assertEquals(7, postorderIterator.next()),
                () -> assertEquals(5, postorderIterator.next()),
                () -> assertEquals(6, postorderIterator.next()),
                () -> assertEquals(3, postorderIterator.next()),
                () -> assertEquals(1, postorderIterator.next()),
                () -> assertEquals(2, postorderIterator.next()),
                () -> assertEquals(4, postorderIterator.next()),
                () -> assertEquals(8, postorderIterator.next()),
                () -> assertFalse(postorderIterator.hasNext())
        );
        Iterator<Integer> otherPostorderIterator = bst.postorderIterator();
        assertAll(
                () -> assertEquals(15, otherPostorderIterator.next()),
                () -> assertEquals(13, otherPostorderIterator.next()),
                () -> assertEquals(14, otherPostorderIterator.next()),
                () -> assertEquals(11, otherPostorderIterator.next()),
                () -> assertEquals(9, otherPostorderIterator.next()),
                () -> assertEquals(10, otherPostorderIterator.next()),
                () -> assertEquals(12, otherPostorderIterator.next()),
                () -> assertEquals(7, otherPostorderIterator.next()),
                () -> assertEquals(5, otherPostorderIterator.next()),
                () -> assertEquals(6, otherPostorderIterator.next()),
                () -> assertEquals(3, otherPostorderIterator.next()),
                () -> assertEquals(1, otherPostorderIterator.next()),
                () -> assertEquals(2, otherPostorderIterator.next()),
                () -> assertEquals(4, otherPostorderIterator.next()),
                () -> assertEquals(8, otherPostorderIterator.next()),
                () -> assertFalse(otherPostorderIterator.hasNext())
        );
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