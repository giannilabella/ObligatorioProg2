package uy.edu.um.prog2.adt.hashtable;

import uy.edu.um.prog2.adt.list.MyDoublyLinkedList;
import uy.edu.um.prog2.adt.collection.MyCollection;
import uy.edu.um.prog2.adt.list.MySinglyLinkedList;

import java.util.Arrays;
import java.lang.reflect.Array;

public class MyClosedHashingHashtable<KeyT, ValueT> implements MyHashtable<KeyT, ValueT> {
    private Entry[] table;
    private int entriesCount;
    private final float loadFactor;

    @SuppressWarnings("unchecked")
    public MyClosedHashingHashtable(int capacity, float loadFactor) {
        if (capacity < 0 || loadFactor <= 0) throw new IllegalArgumentException();

        this.table = (Entry[]) Array.newInstance(Entry.class, capacity);
        this.loadFactor = loadFactor;
    }

    public MyClosedHashingHashtable(int capacity) {
        this(capacity, 0.75f);
    }

    public MyClosedHashingHashtable() {
        this(10);
    }

    private boolean areEqual(Object a, Object b) {
        boolean areBothNull = (a == null && b == null);
        boolean isAEqualB = (a != null && a.equals(b));
        return areBothNull || isAEqualB;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        entriesCount = 0;
    }

    @Override
    public boolean containsKey(KeyT key) {
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % table.length; // hash & 0x7FFFFFFF for absolute value
        for (
                int count = 0;
                table[index] != null && count < table.length;
                count++, index = (index + probing(count)) % table.length
        ) {
            if (table[index].active && areEqual(table[index].key, key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(ValueT value) {
        for (Entry entry : table) {
            if (entry != null && entry.active && areEqual(entry.value, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        MyClosedHashingHashtable<? ,?> otherHashtable = (MyClosedHashingHashtable<?, ?>) other;
        if (this.size() != otherHashtable.size()) return false;
        if (this.size() == 0) return true;
        if (
                keys().iterator().next().getClass() != otherHashtable.keys().iterator().next().getClass() ||
                values().iterator().next().getClass() != otherHashtable.values().iterator().next().getClass()
        ) return false;

        @SuppressWarnings("unchecked") MyClosedHashingHashtable<KeyT ,ValueT> that = (MyClosedHashingHashtable<KeyT, ValueT>) otherHashtable;
        for (KeyT thatKey : that.keys()) {
            if (!containsKey(thatKey)) return false;
            ValueT thatValue = that.get(thatKey);
            if (!areEqual(get(thatKey), thatValue)) return false;
        }

        return true;
    }

    @Override
    public ValueT get(KeyT key) {
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % table.length; // hash & 0x7FFFFFFF for absolute value
        for (
                int count = 0;
                table[index] != null && count < table.length;
                count++, index = (index + probing(count)) % table.length
        ) {
            if (areEqual(table[index].key, key)) {
                return table[index].value;
            }
        }
        return null;
    }

    @Override
    public ValueT getOrDefault(KeyT key, ValueT defaultValue) {
        ValueT result = get(key);
        return result == null ? defaultValue : result;
    }

    @Override
    public boolean isEmpty() {
        return entriesCount == 0;
    }

    @Override
    public MyCollection<KeyT> keys() {
        MyCollection<KeyT> keys = new MyDoublyLinkedList<>();
        for (Entry entry : table) {
            if (entry != null && entry.active) {
                keys.add(entry.key);
            }
        }
        return keys;
    }

    private int probing(int attemptCount) {
        return attemptCount;
    }

    @Override
    public ValueT put(KeyT key, ValueT value) {
        if (entriesCount + 1 >= table.length * loadFactor) {
            rehash();
        }

        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % table.length; // hash & 0x7FFFFFFF for absolute value
        for (
                int count = 0;
                table[index] != null && count < table.length;
                count++, index = (index + probing(count)) % table.length
        ) {
            if (areEqual(table[index].key, key)) {
                ValueT oldValue = table[index].value;
                table[index].value = value;
                table[index].active = true;
                return oldValue;
            }
        }

        table[index] = new Entry(key, value);
        entriesCount++;
        return null;
    }

    @Override
    public ValueT putIfAbsent(KeyT key, ValueT value) {
        if (entriesCount + 1 >= table.length * loadFactor) {
            rehash();
        }

        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % table.length; // hash & 0x7FFFFFFF for absolute value
        for (
                int count = 0;
                table[index] != null && count < table.length;
                count++, index = (index + probing(count)) % table.length
        ) {
            if (areEqual(table[index].key, key)) {
                if (table[index].active) {
                    return table[index].value;
                } else {
                    table[index].value = value;
                    table[index].active = true;
                }
            }
        }

        table[index] = new Entry(key, value);
        entriesCount++;
        return null;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        Entry[] oldTable = table;
        int newCapacity = oldTable.length * 2;

        entriesCount = 0;
        table = (Entry[]) Array.newInstance(Entry.class, newCapacity);

        for (Entry entry : oldTable) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    @Override
    public ValueT remove(KeyT key) {
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % table.length; // hash & 0x7FFFFFFF for absolute value
        for (
                int count = 0;
                table[index] != null && count < table.length;
                count++, index = (index + probing(count)) % table.length
        ) {
            if (areEqual(table[index].key, key)) {
                ValueT oldValue = table[index].value;
                table[index].active = false;
                return oldValue;
            }
        }

        return null;
    }

    @Override
    public boolean remove(KeyT key, ValueT value) {
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % table.length; // hash & 0x7FFFFFFF for absolute value
        for (
                int count = 0;
                table[index] != null && count < table.length;
                count++, index = (index + probing(count)) % table.length
        ) {
            if (areEqual(table[index].key, key) && areEqual(table[index].value, value)) {
                table[index].active = false;
                return true;
            }
        }

        return false;
    }

    @Override
    public ValueT replace(KeyT key, ValueT value) {
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % table.length; // hash & 0x7FFFFFFF for absolute value
        for (
                int count = 0;
                table[index] != null && count < table.length;
                count++, index = (index + probing(count)) % table.length
        ) {
            if (table[index].active && areEqual(table[index].key, key)) {
                ValueT oldValue = table[index].value;
                table[index].value = value;
                return oldValue;
            }
        }

        return null;
    }

    @Override
    public boolean replace(KeyT key, ValueT oldValue, ValueT newValue) {
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % table.length; // hash & 0x7FFFFFFF for absolute value
        for (
                int count = 0;
                table[index] != null && count < table.length;
                count++, index = (index + probing(count)) % table.length
        ) {
            if (table[index].active && areEqual(table[index].key, key) && areEqual(table[index].value, oldValue)) {
                table[index].value = newValue;
                return true;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return entriesCount;
    }

    @Override
    public MyCollection<ValueT> values() {
        MyCollection<ValueT> values = new MySinglyLinkedList<>();
        for (Entry entry : table) {
            if (entry != null && entry.active) {
                values.add(entry.value);
            }
        }
        return values;
    }

    @Override
    public void addValuesTo(MyCollection<ValueT> collection) {
        for (Entry entry : table) {
            if (entry != null && entry.active) {
                collection.add(entry.value);
            }
        }
    }

    private class Entry {
        public final KeyT key;
        public ValueT value;
        public boolean active;

        public Entry(KeyT key, ValueT value) {
            this.key = key;
            this.value = value;
            this.active = true;
        }
    }
}
