package test.adt.stack;

import uy.edu.um.prog2.adt.stack.MyStack;
import uy.edu.um.prog2.adt.stack.MyLinkedStack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class MyLinkedStackTest {
    MyStack<String> stack;

    @BeforeEach
    void setUp() {
        stack = new MyLinkedStack<>();
    }

    @AfterEach
    void tearDown() {
        stack.clear();
    }

    @Test
    void testAddPeekAndRemove() {
        assertAll(
                () -> assertTrue(stack.add("first element")),
                () -> assertTrue(stack.add("second element")),
                () -> assertTrue(stack.add("third element")),
                () -> assertTrue(stack.add("fourth element")),
                () -> assertTrue(stack.add("fifth element")),

                () -> assertEquals("fifth element", stack.peek()),
                () -> assertEquals("fifth element", stack.remove()),

                () -> assertEquals("fourth element", stack.peek()),
                () -> assertEquals("fourth element", stack.remove()),

                () -> assertEquals("third element", stack.peek()),
                () -> assertEquals("third element", stack.remove()),

                () -> assertEquals("second element", stack.peek()),
                () -> assertEquals("second element", stack.remove()),

                () -> assertEquals("first element", stack.peek()),
                () -> assertEquals("first element", stack.remove())
        );
    }

    @Test
    void testAddAll() {
        MyStack<String> otherStack = new MyLinkedStack<>();
        assertAll(
                () -> assertTrue(stack.add("first element")),
                () -> assertTrue(stack.add("second element")),

                () -> assertTrue(otherStack.add("third element")),
                () -> assertTrue(otherStack.add("fourth element")),
                () -> assertTrue(otherStack.add("fifth element")),

                () -> assertTrue(stack.addAll(otherStack)),

                () -> assertTrue(stack.contains("first element")),
                () -> assertTrue(stack.contains("fourth element")),
                () -> assertTrue(stack.contains("third element")),
                () -> assertTrue(stack.contains("fourth element")),
                () -> assertTrue(stack.contains("fifth element"))
        );
    }

    @Test
    void testClearAndIsEmpty() {
        assertAll(
                () -> assertTrue(stack.add("first element")),
                () -> assertTrue(stack.add("second element")),
                () -> assertTrue(stack.add("third element")),
                () -> assertTrue(stack.add("fourth element")),
                () -> assertTrue(stack.add("fifth element")),

                () -> {
                    stack.clear();
                    assertTrue(stack.isEmpty());
                }
        );
    }

    @Test
    void testContains() {
        assertAll(
                () -> assertTrue(stack.add("first element")),
                () -> assertTrue(stack.add("second element")),
                () -> assertTrue(stack.add("third element")),
                () -> assertTrue(stack.add("fourth element")),
                () -> assertTrue(stack.add("fifth element")),

                () -> assertTrue(stack.contains("first element")),
                () -> assertTrue(stack.contains("second element")),
                () -> assertTrue(stack.contains("third element")),
                () -> assertTrue(stack.contains("fourth element")),
                () -> assertTrue(stack.contains("fifth element"))
        );
    }

    @Test
    void testContainsAll() {
        MyStack<String> otherStack = new MyLinkedStack<>();
        assertAll(
                () -> assertTrue(stack.add("first element")),
                () -> assertTrue(stack.add("second element")),
                () -> assertTrue(stack.add("third element")),
                () -> assertTrue(stack.add("fourth element")),
                () -> assertTrue(stack.add("fifth element")),

                () -> assertTrue(otherStack.add("first element")),
                () -> assertTrue(otherStack.add("third element")),
                () -> assertTrue(otherStack.add("fifth element")),

                () -> assertTrue(stack.containsAll(otherStack))
        );
    }

    @Test
    void testEquals() {
        MyStack<String> otherStack = new MyLinkedStack<>();
        assertAll(
                () -> assertTrue(stack.add("first element")),
                () -> assertTrue(stack.add("second element")),
                () -> assertTrue(stack.add("third element")),
                () -> assertTrue(stack.add("fourth element")),
                () -> assertTrue(stack.add("fifth element")),

                () -> assertTrue(otherStack.add("first element")),
                () -> assertTrue(otherStack.add("second element")),
                () -> assertTrue(otherStack.add("third element")),
                () -> assertTrue(otherStack.add("fourth element")),
                () -> assertTrue(otherStack.add("fifth element")),

                () -> assertEquals(stack, otherStack)
        );
    }

    @Test
    void testNotEquals() {
        MyStack<String> otherStack = new MyLinkedStack<>();
        assertAll(
                () -> assertTrue(stack.add("first element")),
                () -> assertTrue(stack.add("second element")),
                () -> assertTrue(stack.add("third element")),
                () -> assertTrue(stack.add("fourth element")),
                () -> assertTrue(stack.add("fifth element")),

                () -> assertTrue(otherStack.add("first element")),
                () -> assertTrue(otherStack.add("second element")),
                () -> assertTrue(otherStack.add("fourth element")),
                () -> assertTrue(otherStack.add("fifth element")),

                () -> assertNotEquals(stack, otherStack)
        );
    }

    @Test
    void testRemove() {
        assertAll(
                () -> assertTrue(stack.add("first element")),
                () -> assertTrue(stack.add("second element")),
                () -> assertTrue(stack.add("third element")),
                () -> assertTrue(stack.add("fourth element")),
                () -> assertTrue(stack.add("fifth element")),

                () -> assertEquals("fifth element", stack.remove()),
                () -> assertFalse(stack.contains("fifth element")),

                () -> assertEquals("fourth element", stack.remove()),
                () -> assertFalse(stack.contains("fourth element")),

                () -> assertEquals("third element", stack.remove()),
                () -> assertFalse(stack.contains("third element")),

                () -> assertEquals("second element", stack.remove()),
                () -> assertFalse(stack.contains("second element")),

                () -> assertEquals("first element", stack.remove()),
                () -> assertFalse(stack.contains("first element")),

                () -> assertTrue(stack.isEmpty())
        );
    }

    @Test
    void testRemoveByElement() {
        assertAll(
                () -> assertTrue(stack.add("first element")),
                () -> assertTrue(stack.add("second element")),
                () -> assertTrue(stack.add("third element")),
                () -> assertTrue(stack.add("fourth element")),
                () -> assertTrue(stack.add("fifth element")),

                () -> assertTrue(stack.remove("first element")),
                () -> assertFalse(stack.contains("first element")),

                () -> assertTrue(stack.remove("third element")),
                () -> assertFalse(stack.contains("third element")),

                () -> assertTrue(stack.remove("fifth element")),
                () -> assertFalse(stack.contains("fifth element")),

                () -> assertTrue(stack.remove("second element")),
                () -> assertFalse(stack.contains("second element")),

                () -> assertTrue(stack.remove("fourth element")),
                () -> assertFalse(stack.contains("fourth element")),

                () -> assertTrue(stack.isEmpty())
        );
    }

    @Test
    void testRemoveAll() {
        MyStack<String> otherStack = new MyLinkedStack<>();
        assertAll(
                () -> assertTrue(stack.add("first element")),
                () -> assertTrue(stack.add("second element")),
                () -> assertTrue(stack.add("third element")),
                () -> assertTrue(stack.add("fourth element")),
                () -> assertTrue(stack.add("fifth element")),

                () -> assertTrue(otherStack.add("first element")),
                () -> assertTrue(otherStack.add("third element")),
                () -> assertTrue(otherStack.add("fifth element")),

                () -> assertTrue(stack.removeAll(otherStack)),

                () -> assertFalse(stack.contains("first element")),
                () -> assertFalse(stack.contains("third element")),
                () -> assertFalse(stack.contains("fifth element")),

                () -> assertTrue(stack.contains("second element")),
                () -> assertTrue(stack.contains("fourth element"))
        );
    }

    @Test
    void testSize() {
        assertAll(
                () -> assertEquals(0, stack.size()),

                () -> assertTrue(stack.add("first element")),
                () -> assertEquals(1, stack.size()),

                () -> assertTrue(stack.add("second element")),
                () -> assertEquals(2, stack.size()),

                () -> assertTrue(stack.add("third element")),
                () -> assertEquals(3, stack.size())
        );
    }

    @Test
    void testToArray() {
        String[] smallArray = new String[1];
        String[] rightSizedArray = new String[5];
        String[] bigArray = new String[10];

        assertAll(
                () -> assertTrue(stack.add("fifth element")),
                () -> assertTrue(stack.add("fourth element")),
                () -> assertTrue(stack.add("third element")),
                () -> assertTrue(stack.add("second element")),
                () -> assertTrue(stack.add("first element")),

                () -> assertEquals("first element", stack.toArray(smallArray)[0]),
                () -> assertEquals("second element", stack.toArray(smallArray)[1]),
                () -> assertEquals("third element", stack.toArray(rightSizedArray)[2]),
                () -> assertEquals("fourth element", stack.toArray(bigArray)[3]),
                () -> assertEquals("fifth element", stack.toArray(bigArray)[4])
        );
    }
}