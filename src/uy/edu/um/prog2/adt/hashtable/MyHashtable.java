package uy.edu.um.prog2.adt.hashtable;

import uy.edu.um.prog2.adt.collection.MyCollection;

public interface MyHashtable<KeyT, ValueT> {
    void clear();

    boolean containsKey(KeyT key);

    boolean containsValue(ValueT value);

    boolean equals(Object obj);

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

    void addValuesTo(MyCollection<ValueT> collection);
}