package test.adts;

import adts.heap.MyHeap;
import adts.heap.MyMinHeap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyMinHeapTest {
    MyHeap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new MyMinHeap<>();
    }

    @AfterEach
    void tearDown() {
        heap.clear();
    }

    @Test
    void testAddAndPeek() {
        assertAll(
                () -> assertTrue(heap.add(4)),
                () -> assertTrue(heap.add(3)),
                () -> assertTrue(heap.add(2)),
                () -> assertTrue(heap.add(1)),
                () -> assertEquals(1, heap.peek()),
                () -> assertEquals(1, heap.peek())
        );
    }

    @Test
    void testAddAndRemove() {
        assertAll(
                () -> assertTrue(heap.add(4)),
                () -> assertTrue(heap.add(3)),
                () -> assertTrue(heap.add(2)),
                () -> assertTrue(heap.add(1)),
                () -> assertEquals(1, heap.remove()),
                () -> assertEquals(2, heap.remove()),
                () -> assertEquals(3, heap.remove()),
                () -> assertEquals(4, heap.remove())
        );
    }

    @Test
    void testAddAllAndRemove() {
        MyHeap<Integer> otherHeap = new MyMinHeap<>();
        assertAll(
                () -> assertTrue(heap.add(4)),
                () -> assertTrue(heap.add(3)),
                () -> assertTrue(otherHeap.add(2)),
                () -> assertTrue(otherHeap.add(1)),
                () -> assertTrue(heap.addAll(otherHeap)),
                () -> assertEquals(1, heap.remove()),
                () -> assertEquals(2, heap.remove()),
                () -> assertEquals(3, heap.remove()),
                () -> assertEquals(4, heap.remove())
        );
    }

    @Test
    void testClearAndIsEmpty() {
        assertAll(
                () -> assertTrue(heap.add(1)),
                () -> assertTrue(heap.add(2)),
                () -> {
                    heap.clear();
                    assertTrue(heap.isEmpty());
                }
        );
    }

    @Test
    void testContains() {
        assertAll(
                () -> assertTrue(heap.add(1)),
                () -> assertTrue(heap.add(2)),
                () -> assertTrue(heap.contains(1)),
                () -> assertTrue(heap.contains(2)),
                () -> assertFalse(heap.contains(3)),
                () -> assertFalse(heap.contains(4))
        );
    }

    @Test
    void testContainsAll() {
        MyHeap<Integer> otherHeap = new MyMinHeap<>();
        assertAll(
                () -> assertTrue(heap.add(4)),
                () -> assertTrue(heap.add(3)),
                () -> assertTrue(heap.add(2)),
                () -> assertTrue(heap.add(1)),
                () -> assertTrue(otherHeap.add(3)),
                () -> assertTrue(otherHeap.add(2)),
                () -> assertTrue(heap.containsAll(otherHeap))
        );
    }

    @Test
    void testEquals() {
        MyHeap<Integer> otherHeap = new MyMinHeap<>();
        assertAll(
                () -> assertTrue(heap.add(4)),
                () -> assertTrue(heap.add(3)),
                () -> assertTrue(heap.add(2)),
                () -> assertTrue(heap.add(1)),
                () -> assertTrue(otherHeap.add(1)),
                () -> assertTrue(otherHeap.add(2)),
                () -> assertTrue(otherHeap.add(3)),
                () -> assertTrue(otherHeap.add(4)),
                () -> assertEquals(heap, otherHeap)
        );
    }

    @Test
    void testNotEquals() {
        MyHeap<Integer> otherHeap = new MyMinHeap<>();
        assertAll(
                () -> assertTrue(heap.add(4)),
                () -> assertTrue(heap.add(3)),
                () -> assertTrue(heap.add(2)),
                () -> assertTrue(heap.add(1)),
                () -> assertTrue(otherHeap.add(2)),
                () -> assertTrue(otherHeap.add(3)),
                () -> assertTrue(otherHeap.add(4)),
                () -> assertEquals(heap, otherHeap)
        );
    }

    @Test
    void testRemoveByElement() {
        assertAll(
                () -> assertTrue(heap.add(4)),
                () -> assertTrue(heap.add(3)),
                () -> assertTrue(heap.add(2)),
                () -> assertTrue(heap.add(1)),
                () -> assertTrue(heap.remove(1)),
                () -> assertTrue(heap.remove(2)),
                () -> assertTrue(heap.remove(3)),
                () -> assertTrue(heap.remove(4)),
                () -> assertTrue(heap.isEmpty())
        );
    }

    @Test
    void testRemoveAll() {
        MyHeap<Integer> otherHeap = new MyMinHeap<>();
        assertAll(
                () -> assertTrue(heap.add(4)),
                () -> assertTrue(heap.add(3)),
                () -> assertTrue(heap.add(2)),
                () -> assertTrue(heap.add(1)),
                () -> assertTrue(otherHeap.add(4)),
                () -> assertTrue(otherHeap.add(3)),
                () -> assertTrue(otherHeap.add(2)),
                () -> assertTrue(otherHeap.add(1)),
                () -> assertTrue(heap.removeAll(otherHeap)),
                () -> assertTrue(heap.isEmpty())
        );
    }

    @Test
    void testSize() {
        assertAll(
                () -> assertEquals(0, heap.size()),

                () -> assertTrue(heap.add(1)),
                () -> assertEquals(1, heap.size()),

                () -> assertTrue(heap.add(2)),
                () -> assertEquals(2, heap.size()),

                () -> assertTrue(heap.add(3)),
                () -> assertEquals(3, heap.size())
        );
    }

    @Test
    void toArray() {
        Integer[] smallArray = new Integer[1];
        Integer[] rightSizedArray = new Integer[5];
        Integer[] bigArray = new Integer[10];

        assertAll(
                () -> assertTrue(heap.add(6)),
                () -> assertTrue(heap.add(5)),
                () -> assertTrue(heap.add(4)),
                () -> assertTrue(heap.add(3)),
                () -> assertTrue(heap.add(2)),
                () -> assertTrue(heap.add(1)),

                () -> assertEquals(1, heap.toArray(smallArray)[0]),
                () -> assertEquals(2, heap.toArray(smallArray)[1]),
                () -> assertEquals(3, heap.toArray(rightSizedArray)[2]),
                () -> assertEquals(4, heap.toArray(rightSizedArray)[3]),
                () -> assertEquals(5, heap.toArray(bigArray)[4]),
                () -> assertEquals(6, heap.toArray(bigArray)[5])
        );
    }
}