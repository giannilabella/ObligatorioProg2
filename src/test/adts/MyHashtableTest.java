package test.adts;

import adts.hashtable.MyHashtable;
import adts.hashtable.MyClosedHashingHashtable;

import adts.list.MyDoublyLinkedList;
import adts.collection.MyCollection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class MyHashtableTest {
    MyHashtable<String, String> hashtable;

    @BeforeEach
    void setUp() {
        hashtable = new MyClosedHashingHashtable<>(1, 1);
    }

    @AfterEach
    void tearDown() {
        hashtable.clear();
    }

    @Test
    void testClearAndIsEmpty() {
        assertAll(
                () -> assertNull(hashtable.put("A", "value of A")),
                () -> assertNull(hashtable.put("B", "value of B")),
                () -> assertNull(hashtable.put("C", "value of C")),

                () -> hashtable.clear(),
                () -> assertEquals(0, hashtable.size()),
                () -> assertTrue(hashtable.isEmpty()),

                () -> assertFalse(hashtable.containsKey("A")),
                () -> assertFalse(hashtable.containsKey("B")),
                () -> assertFalse(hashtable.containsKey("C")),
                () -> assertFalse(hashtable.containsValue("value of A")),
                () -> assertFalse(hashtable.containsValue("value of B")),
                () -> assertFalse(hashtable.containsValue("value of C"))
        );
    }

    @Test
    void testContainsKey() {
        assertAll(
                () -> assertNull(hashtable.put("A", "value of A")),
                () -> assertNull(hashtable.put("B", "value of B")),
                () -> assertNull(hashtable.put("C", "value of C")),

                () -> assertTrue(hashtable.containsKey("A")),
                () -> assertTrue(hashtable.containsKey("B")),
                () -> assertTrue(hashtable.containsKey("C"))
        );
    }

    @Test
    void testContainsValue() {
        assertAll(
                () -> assertNull(hashtable.put("A", "value of A")),
                () -> assertNull(hashtable.put("B", "value of B")),
                () -> assertNull(hashtable.put("C", "value of C")),

                () -> assertTrue(hashtable.containsValue("value of A")),
                () -> assertTrue(hashtable.containsValue("value of B")),
                () -> assertTrue(hashtable.containsValue("value of C"))
        );
    }

    @Test
    void testEquals() {
        MyHashtable<String, String> otherHashtable = new MyClosedHashingHashtable<>();
        assertAll(
                () -> assertNull(hashtable.put("A", "value of A")),
                () -> assertNull(hashtable.put("B", "value of B")),
                () -> assertNull(hashtable.put("C", "value of C")),

                () -> assertNull(otherHashtable.put("A", "value of A")),
                () -> assertNull(otherHashtable.put("B", "value of B")),
                () -> assertNull(otherHashtable.put("C", "value of C")),

                () -> assertEquals(hashtable, otherHashtable)
        );
    }

    @Test
    void testGet() {
        assertAll(
                () -> assertNull(hashtable.put("A", "value of A")),
                () -> assertNull(hashtable.put("B", "value of B")),
                () -> assertNull(hashtable.put("C", "value of C")),

                () -> assertEquals("value of A", hashtable.get("A")),
                () -> assertEquals("value of B", hashtable.get("B")),
                () -> assertEquals("value of C", hashtable.get("C")),
                () -> assertNull(hashtable.get("D")),
                () -> assertNull(hashtable.get("E"))
        );
    }

    @Test
    void testGetOrDefault() {
        assertAll(
                () -> assertNull(hashtable.put("A", "value of A")),
                () -> assertNull(hashtable.put("B", "value of B")),
                () -> assertNull(hashtable.put("C", "value of C")),

                () -> assertEquals("value of A", hashtable.get("A")),
                () -> assertEquals("value of B", hashtable.get("B")),
                () -> assertEquals("value of C", hashtable.get("C")),
                () -> assertEquals("default value D", hashtable.getOrDefault("D", "default value D")),
                () -> assertEquals("default value E", hashtable.getOrDefault("E", "default value E"))
        );
    }

    @Test
    void testGetKeys() {
        MyCollection<String> keys = new MyDoublyLinkedList<>();
        keys.add("A");
        keys.add("B");
        keys.add("C");

        assertAll(
                () -> assertNull(hashtable.put("A", "value of A")),
                () -> assertNull(hashtable.put("B", "value of B")),
                () -> assertNull(hashtable.put("C", "value of C")),

                () -> assertEquals(keys, hashtable.keys())
        );
    }

    @Test
    void testPut() {
        assertAll(
                () -> assertNull(hashtable.put("A", "value of A")),
                () -> assertNull(hashtable.put("B", "value of B")),
                () -> assertNull(hashtable.put("C", "value of C")),

                () -> assertEquals("value of A", hashtable.put("A", "new value of A")),
                () -> assertEquals("value of B", hashtable.put("B", "new value of B")),
                () -> assertEquals("value of C", hashtable.put("C", "new value of C")),

                () -> assertTrue(hashtable.containsValue("new value of A")),
                () -> assertTrue(hashtable.containsValue("new value of B")),
                () -> assertTrue(hashtable.containsValue("new value of C"))
        );
    }

    @Test
    void testPutIfAbsent() {
        assertAll(
                () -> assertNull(hashtable.putIfAbsent("A", "value of A")),
                () -> assertNull(hashtable.putIfAbsent("B", "value of B")),
                () -> assertNull(hashtable.putIfAbsent("C", "value of C")),

                () -> assertEquals("value of A", hashtable.putIfAbsent("A", "new value of A")),
                () -> assertEquals("value of B", hashtable.putIfAbsent("B", "new value of B")),
                () -> assertEquals("value of C", hashtable.putIfAbsent("C", "new value of C")),

                () -> assertFalse(hashtable.containsValue("new value of A")),
                () -> assertFalse(hashtable.containsValue("new value of B")),
                () -> assertFalse(hashtable.containsValue("new value of C"))
        );
    }

    @Test
    void testRemoveByKey() {
        assertAll(
                () -> assertNull(hashtable.put("A", "value of A")),
                () -> assertNull(hashtable.put("B", "value of B")),
                () -> assertNull(hashtable.put("C", "value of C")),

                () -> assertEquals("value of A", hashtable.remove("A")),
                () -> assertEquals("value of B", hashtable.remove("B")),
                () -> assertEquals("value of C", hashtable.remove("C")),

                () -> assertFalse(hashtable.containsKey("A")),
                () -> assertFalse(hashtable.containsKey("B")),
                () -> assertFalse(hashtable.containsKey("C"))
        );
    }

    @Test
    void testRemoveByKeyAndValue() {
        assertAll(
                () -> assertNull(hashtable.put("A", "value of A")),
                () -> assertNull(hashtable.put("B", "value of B")),
                () -> assertNull(hashtable.put("C", "value of C")),

                () -> assertTrue(hashtable.remove("A", "value of A")),
                () -> assertFalse(hashtable.remove("B", "not value of B")),
                () -> assertTrue(hashtable.remove("B", "value of B")),
                () -> assertFalse(hashtable.remove("C", "not value of C")),
                () -> assertTrue(hashtable.remove("C", "value of C")),

                () -> assertFalse(hashtable.containsKey("A")),
                () -> assertFalse(hashtable.containsKey("B")),
                () -> assertFalse(hashtable.containsKey("C"))
        );
    }

    @Test
    void testReplaceByKey() {
        assertAll(
                () -> assertNull(hashtable.put("A", "value of A")),
                () -> assertNull(hashtable.put("B", "value of B")),
                () -> assertNull(hashtable.put("C", "value of C")),

                () -> assertEquals("value of A", hashtable.replace("A", "new value of A")),
                () -> assertEquals("value of B", hashtable.replace("B", "new value of B")),
                () -> assertEquals("value of C", hashtable.replace("C", "new value of C")),

                () -> assertEquals("new value of A", hashtable.get("A")),
                () -> assertEquals("new value of B", hashtable.get("B")),
                () -> assertEquals("new value of C", hashtable.get("C"))
        );
    }

    @Test
    void testReplaceByKeyAndValue() {
        assertAll(
                () -> assertNull(hashtable.put("A", "value of A")),
                () -> assertNull(hashtable.put("B", "value of B")),
                () -> assertNull(hashtable.put("C", "value of C")),

                () -> assertTrue(hashtable.replace("A", "value of A", "new value of A")),
                () -> assertFalse(hashtable.replace("B", "not value of B", "new value of B")),
                () -> assertTrue(hashtable.replace("B", "value of B", "new value of B")),
                () -> assertFalse(hashtable.replace("C", "not value of C", "new value of C")),
                () -> assertTrue(hashtable.replace("C", "value of C", "new value of C")),

                () -> assertEquals("new value of A", hashtable.get("A")),
                () -> assertEquals("new value of B", hashtable.get("B")),
                () -> assertEquals("new value of C", hashtable.get("C"))
        );
    }

    @Test
    void testSize() {
        assertAll(
                () -> assertEquals(0, hashtable.size()),

                () -> assertNull(hashtable.put("A", "value of A")),
                () -> assertEquals(1, hashtable.size()),

                () -> assertNull(hashtable.put("B", "value of B")),
                () -> assertEquals(2, hashtable.size()),

                () -> assertNull(hashtable.put("C", "value of C")),
                () -> assertEquals(3, hashtable.size())
        );
    }

    @Test
    void testGetValues() {
        MyCollection<String> values = new MyDoublyLinkedList<>();
        values.add("value of A");
        values.add("value of B");
        values.add("value of C");

        assertAll(
                () -> assertNull(hashtable.put("A", "value of A")),
                () -> assertNull(hashtable.put("B", "value of B")),
                () -> assertNull(hashtable.put("C", "value of C")),

                () -> assertEquals(values, hashtable.values())
        );
    }
}
