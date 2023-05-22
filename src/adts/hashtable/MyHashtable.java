package adts.hashtable;

import adts.collection.MyCollection;

public interface MyHashtable<KeyT, ValueT> {
    void clear();

    boolean containsKey(KeyT key);

    boolean containsValue(ValueT value);

    boolean equals(Object other);

    ValueT get(KeyT key);

    ValueT getOrDefault(KeyT key, ValueT defaultValue);

    boolean isEmpty();

    MyCollection<KeyT> keys();

    ValueT put(KeyT key, ValueT value);

    ValueT putIfAbsent(KeyT key, ValueT value);

    ValueT remove(KeyT key);

    boolean remove(KeyT key, ValueT value);

    ValueT replace(KeyT key, ValueT value);

    boolean replace(KeyT key, ValueT oldValue, ValueT newValue);

    int size();

    MyCollection<ValueT> values();
}