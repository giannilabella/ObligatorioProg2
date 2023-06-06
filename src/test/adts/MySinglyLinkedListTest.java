package test.adts;

import adts.list.MySinglyLinkedList;
import adts.list.MyList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MySinglyLinkedListTest {
    MyList<String> list;

    @BeforeEach
    void setUp() {
        list = new MySinglyLinkedList<>();
    }

    @AfterEach
    void tearDown() {
        list.clear();
    }

    @Test
    void testAddAndGet() {
        assertAll(
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),
                () -> assertTrue(list.add("third element")),
                () -> assertTrue(list.add("fourth element")),
                () -> assertEquals("first element", list.get(0)),
                () -> assertEquals("second element", list.get(1)),
                () -> assertEquals("third element", list.get(2)),
                () -> assertEquals("fourth element", list.get(3))
        );
    }

    @Test
    void testAddAtIndex() {
        assertAll(
                () -> assertTrue(list.add(0, "second element")),
                () -> assertTrue(list.add(0, "first element")),
                () -> assertEquals("first element", list.get(0)),
                () -> assertEquals("second element", list.get(1))
        );
    }

    @Test
    void testAddAll() {
        MyList<String> otherList = new MySinglyLinkedList<>();
        assertAll(
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),
                () -> assertTrue(otherList.add("third element")),
                () -> assertTrue(otherList.add("fourth element")),
                () -> assertTrue(list.addAll(otherList)),
                () -> assertEquals("third element", list.get(2)),
                () -> assertEquals("fourth element", list.get(3))
        );
    }

    @Test
    void testAddAllAtIndex() {
        MyList<String> otherList = new MySinglyLinkedList<>();
        assertAll(
                () -> assertTrue(list.add("third element")),
                () -> assertTrue(list.add("fourth element")),
                () -> assertTrue(otherList.add("first element")),
                () -> assertTrue(otherList.add("second element")),
                () -> assertTrue(list.addAll(0, otherList)),
                () -> assertEquals("first element", list.get(0)),
                () -> assertEquals("second element", list.get(1))
        );
    }

    @Test
    void testClearAndIsEmpty() {
        assertAll(
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),
                () -> {
                    list.clear();
                    assertTrue(list.isEmpty());
                }
        );
    }

    @Test
    void testContains() {
        assertAll(
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),

                () -> assertTrue(list.contains("first element")),
                () -> assertTrue(list.contains("second element"))
        );
    }

    @Test
    void testContainsAll() {
        MyList<String> otherList = new MySinglyLinkedList<>();
        assertAll(
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),

                () -> assertTrue(otherList.add("first element")),
                () -> assertTrue(otherList.add("second element")),

                () -> assertTrue(list.containsAll(otherList))
        );
    }

    @Test
    void testEquals() {
        MyList<String> otherList = new MySinglyLinkedList<>();
        assertAll(
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),

                () -> assertTrue(otherList.add("first element")),
                () -> assertTrue(otherList.add("second element")),

                () -> assertEquals(list, otherList)
        );
    }

    @Test
    void testNotEquals() {
        MyList<String> otherList = new MySinglyLinkedList<>();
        assertAll(
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),
                () -> assertTrue(list.add("third element")),
                () -> assertTrue(list.add("fourth element")),
                () -> assertTrue(list.add("fifth element")),

                () -> assertTrue(otherList.add("first element")),
                () -> assertTrue(otherList.add("second element")),
                () -> assertTrue(otherList.add("fourth element")),
                () -> assertTrue(otherList.add("fifth element")),

                () -> assertNotEquals(list, otherList)
        );
    }

    @Test
    void testGetThrowsIndexOutOfBounds() {
        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, () -> list.get(0)),

                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),
                () -> assertTrue(list.add("third element")),
                () -> assertTrue(list.add("fourth element")),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> list.get(4))
        );
    }

    @Test
    void testIndexOf() {
        assertAll(
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),
                () -> assertTrue(list.add("third element")),
                () -> assertTrue(list.add("fourth element")),
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),
                () -> assertTrue(list.add("third element")),
                () -> assertTrue(list.add("fourth element")),

                () -> assertEquals(0, list.indexOf("first element")),
                () -> assertEquals(1, list.indexOf("second element")),
                () -> assertEquals(2, list.indexOf("third element")),
                () -> assertEquals(3, list.indexOf("fourth element"))
        );
    }

    @Test
    void testLastIndexOf() {
        assertAll(
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),
                () -> assertTrue(list.add("third element")),
                () -> assertTrue(list.add("fourth element")),
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),
                () -> assertTrue(list.add("third element")),
                () -> assertTrue(list.add("fourth element")),

                () -> assertEquals(4, list.lastIndexOf("first element")),
                () -> assertEquals(5, list.lastIndexOf("second element")),
                () -> assertEquals(6, list.lastIndexOf("third element")),
                () -> assertEquals(7, list.lastIndexOf("fourth element"))
        );
    }

    @Test
    void testRemoveAtIndex() {
        assertAll(
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),
                () -> assertTrue(list.add("third element")),
                () -> assertTrue(list.add("fourth element")),

                () -> assertEquals("first element", list.remove(0)),
                () -> assertFalse(list.contains("first element")),

                () -> assertEquals("third element", list.remove(1)),
                () -> assertFalse(list.contains("third element"))
        );
    }

    @Test
    void testRemoveByElement() {
        assertAll(
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),
                () -> assertTrue(list.add("third element")),
                () -> assertTrue(list.add("fourth element")),

                () -> assertTrue(list.remove("first element")),
                () -> assertFalse(list.contains("first element")),

                () -> assertTrue(list.remove("third element")),
                () -> assertFalse(list.contains("third element"))
        );
    }

    @Test
    void testRemoveAll() {
        MyList<String> otherList = new MySinglyLinkedList<>();
        assertAll(
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),
                () -> assertTrue(list.add("third element")),

                () -> assertTrue(otherList.add("first element")),
                () -> assertTrue(otherList.add("second element")),

                () -> assertTrue(list.removeAll(otherList)),

                () -> assertFalse(list.contains("first element")),
                () -> assertFalse(list.contains("second element")),
                () -> assertEquals("third element", list.get(0))
        );
    }

    @Test
    void testSet() {
        assertAll(
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),
                () -> assertTrue(list.add("third element")),

                () -> assertEquals("first element", list.set(0, "new first element")),
                () -> assertEquals("second element", list.set(1, "new second element")),
                () -> assertEquals("third element", list.set(2, "new third element")),

                () -> assertEquals("new first element", list.get(0)),
                () -> assertEquals("new second element", list.get(1)),
                () -> assertEquals("new third element", list.get(2))
        );
    }

    @Test
    void testSize() {
        assertAll(
                () -> assertEquals(0, list.size()),

                () -> assertTrue(list.add("first element")),
                () -> assertEquals(1, list.size()),

                () -> assertTrue(list.add("second element")),
                () -> assertEquals(2, list.size()),

                () -> assertTrue(list.add("third element")),
                () -> assertEquals(3, list.size())
        );
    }

    @Test
    void testSubList() {
        MyList<String> otherList = new MySinglyLinkedList<>();
        assertAll(
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),
                () -> assertTrue(list.add("third element")),
                () -> assertTrue(list.add("fourth element")),
                () -> assertTrue(list.add("fifth element")),

                () -> assertTrue(otherList.add("second element")),
                () -> assertTrue(otherList.add("third element")),
                () -> assertTrue(otherList.add("fourth element")),

                () -> assertEquals(otherList, list.subList(1, 3))
        );
    }

    @Test
    void testToArray() {
        String[] smallArray = new String[1];
        String[] rightSizedArray = new String[5];
        String[] bigArray = new String[10];

        assertAll(
                () -> assertTrue(list.add("first element")),
                () -> assertTrue(list.add("second element")),
                () -> assertTrue(list.add("third element")),
                () -> assertTrue(list.add("fourth element")),
                () -> assertTrue(list.add("fifth element")),

                () -> assertEquals("first element", list.toArray(smallArray)[0]),
                () -> assertEquals("second element", list.toArray(smallArray)[1]),
                () -> assertEquals("third element", list.toArray(rightSizedArray)[2]),
                () -> assertEquals("fourth element", list.toArray(bigArray)[3]),
                () -> assertEquals("fifth element", list.toArray(bigArray)[4])
        );
    }
}