package test.adt;

import uy.edu.um.prog2.adt.queue.MyQueue;
import uy.edu.um.prog2.adt.queue.MyLinkedQueue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class MyLinkedQueueTest {
    MyQueue<String> queue;

    @BeforeEach
    void setUp() {
        queue = new MyLinkedQueue<>();
    }

    @AfterEach
    void tearDown() {
        queue.clear();
    }

    @Test
    void testAddPeekAndRemove() {
        assertAll(
                () -> assertTrue(queue.add("first element")),
                () -> assertTrue(queue.add("second element")),
                () -> assertTrue(queue.add("third element")),
                () -> assertTrue(queue.add("fourth element")),
                () -> assertTrue(queue.add("fifth element")),

                () -> assertEquals("first element", queue.peek()),
                () -> assertEquals("first element", queue.remove()),

                () -> assertEquals("second element", queue.peek()),
                () -> assertEquals("second element", queue.remove()),

                () -> assertEquals("third element", queue.peek()),
                () -> assertEquals("third element", queue.remove()),

                () -> assertEquals("fourth element", queue.peek()),
                () -> assertEquals("fourth element", queue.remove()),

                () -> assertEquals("fifth element", queue.peek()),
                () -> assertEquals("fifth element", queue.remove())
        );
    }

    @Test
    void testAddAll() {
        MyQueue<String> otherQueue = new MyLinkedQueue<>();
        assertAll(
                () -> assertTrue(queue.add("first element")),
                () -> assertTrue(queue.add("second element")),

                () -> assertTrue(otherQueue.add("third element")),
                () -> assertTrue(otherQueue.add("fourth element")),
                () -> assertTrue(otherQueue.add("fifth element")),

                () -> assertTrue(queue.addAll(otherQueue)),

                () -> assertTrue(queue.contains("first element")),
                () -> assertTrue(queue.contains("fourth element")),
                () -> assertTrue(queue.contains("third element")),
                () -> assertTrue(queue.contains("fourth element")),
                () -> assertTrue(queue.contains("fifth element"))
        );
    }

    @Test
    void testClearAndIsEmpty() {
        assertAll(
                () -> assertTrue(queue.add("first element")),
                () -> assertTrue(queue.add("second element")),
                () -> assertTrue(queue.add("third element")),
                () -> assertTrue(queue.add("fourth element")),
                () -> assertTrue(queue.add("fifth element")),

                () -> {
                    queue.clear();
                    assertTrue(queue.isEmpty());
                }
        );
    }

    @Test
    void testContains() {
        assertAll(
                () -> assertTrue(queue.add("first element")),
                () -> assertTrue(queue.add("second element")),
                () -> assertTrue(queue.add("third element")),
                () -> assertTrue(queue.add("fourth element")),
                () -> assertTrue(queue.add("fifth element")),

                () -> assertTrue(queue.contains("first element")),
                () -> assertTrue(queue.contains("second element")),
                () -> assertTrue(queue.contains("third element")),
                () -> assertTrue(queue.contains("fourth element")),
                () -> assertTrue(queue.contains("fifth element"))
        );
    }

    @Test
    void testContainsAll() {
        MyQueue<String> otherQueue = new MyLinkedQueue<>();
        assertAll(
                () -> assertTrue(queue.add("first element")),
                () -> assertTrue(queue.add("second element")),
                () -> assertTrue(queue.add("third element")),
                () -> assertTrue(queue.add("fourth element")),
                () -> assertTrue(queue.add("fifth element")),

                () -> assertTrue(otherQueue.add("first element")),
                () -> assertTrue(otherQueue.add("third element")),
                () -> assertTrue(otherQueue.add("fifth element")),

                () -> assertTrue(queue.containsAll(otherQueue))
        );
    }

    @Test
    void testEquals() {
        MyQueue<String> otherQueue = new MyLinkedQueue<>();
        assertAll(
                () -> assertTrue(queue.add("first element")),
                () -> assertTrue(queue.add("second element")),
                () -> assertTrue(queue.add("third element")),
                () -> assertTrue(queue.add("fourth element")),
                () -> assertTrue(queue.add("fifth element")),

                () -> assertTrue(otherQueue.add("first element")),
                () -> assertTrue(otherQueue.add("second element")),
                () -> assertTrue(otherQueue.add("third element")),
                () -> assertTrue(otherQueue.add("fourth element")),
                () -> assertTrue(otherQueue.add("fifth element")),

                () -> assertEquals(queue, otherQueue)
        );
    }

    @Test
    void testNotEquals() {
        MyQueue<String> otherQueue = new MyLinkedQueue<>();
        assertAll(
                () -> assertTrue(queue.add("first element")),
                () -> assertTrue(queue.add("second element")),
                () -> assertTrue(queue.add("third element")),
                () -> assertTrue(queue.add("fourth element")),
                () -> assertTrue(queue.add("fifth element")),

                () -> assertTrue(otherQueue.add("first element")),
                () -> assertTrue(otherQueue.add("second element")),
                () -> assertTrue(otherQueue.add("fourth element")),
                () -> assertTrue(otherQueue.add("fifth element")),

                () -> assertNotEquals(queue, otherQueue)
        );
    }

    @Test
    void testRemove() {
        assertAll(
                () -> assertTrue(queue.add("first element")),
                () -> assertTrue(queue.add("second element")),
                () -> assertTrue(queue.add("third element")),
                () -> assertTrue(queue.add("fourth element")),
                () -> assertTrue(queue.add("fifth element")),

                () -> assertEquals("first element", queue.remove()),
                () -> assertFalse(queue.contains("first element")),

                () -> assertEquals("second element", queue.remove()),
                () -> assertFalse(queue.contains("second element")),

                () -> assertEquals("third element", queue.remove()),
                () -> assertFalse(queue.contains("third element")),

                () -> assertEquals("fourth element", queue.remove()),
                () -> assertFalse(queue.contains("fourth element")),

                () -> assertEquals("fifth element", queue.remove()),
                () -> assertFalse(queue.contains("fifth element"))
        );
    }

    @Test
    void testRemoveByElement() {
        assertAll(
                () -> assertTrue(queue.add("first element")),
                () -> assertTrue(queue.add("second element")),
                () -> assertTrue(queue.add("third element")),
                () -> assertTrue(queue.add("fourth element")),
                () -> assertTrue(queue.add("fifth element")),

                () -> assertTrue(queue.remove("first element")),
                () -> assertFalse(queue.contains("first element")),

                () -> assertTrue(queue.remove("third element")),
                () -> assertFalse(queue.contains("third element")),

                () -> assertTrue(queue.remove("fifth element")),
                () -> assertFalse(queue.contains("fifth element")),

                () -> assertTrue(queue.remove("second element")),
                () -> assertFalse(queue.contains("second element")),

                () -> assertTrue(queue.remove("fourth element")),
                () -> assertFalse(queue.contains("fourth element")),

                () -> assertTrue(queue.isEmpty())
        );
    }

    @Test
    void testRemoveAll() {
        MyQueue<String> otherQueue = new MyLinkedQueue<>();
        assertAll(
                () -> assertTrue(queue.add("first element")),
                () -> assertTrue(queue.add("second element")),
                () -> assertTrue(queue.add("third element")),
                () -> assertTrue(queue.add("fourth element")),
                () -> assertTrue(queue.add("fifth element")),

                () -> assertTrue(otherQueue.add("first element")),
                () -> assertTrue(otherQueue.add("third element")),
                () -> assertTrue(otherQueue.add("fifth element")),

                () -> assertTrue(queue.removeAll(otherQueue)),

                () -> assertFalse(queue.contains("first element")),
                () -> assertFalse(queue.contains("third element")),
                () -> assertFalse(queue.contains("fifth element")),

                () -> assertTrue(queue.contains("second element")),
                () -> assertTrue(queue.contains("fourth element"))
        );
    }

    @Test
    void testSize() {
        assertAll(
                () -> assertEquals(0, queue.size()),

                () -> assertTrue(queue.add("first element")),
                () -> assertEquals(1, queue.size()),

                () -> assertTrue(queue.add("second element")),
                () -> assertEquals(2, queue.size()),

                () -> assertTrue(queue.add("third element")),
                () -> assertEquals(3, queue.size())
        );
    }

    @Test
    void testToArray() {
        String[] smallArray = new String[1];
        String[] rightSizedArray = new String[5];
        String[] bigArray = new String[10];

        assertAll(
                () -> assertTrue(queue.add("first element")),
                () -> assertTrue(queue.add("second element")),
                () -> assertTrue(queue.add("third element")),
                () -> assertTrue(queue.add("fourth element")),
                () -> assertTrue(queue.add("fifth element")),

                () -> assertEquals("first element", queue.toArray(smallArray)[0]),
                () -> assertEquals("second element", queue.toArray(smallArray)[1]),
                () -> assertEquals("third element", queue.toArray(rightSizedArray)[2]),
                () -> assertEquals("fourth element", queue.toArray(bigArray)[3]),
                () -> assertEquals("fifth element", queue.toArray(bigArray)[4])
        );
    }
}