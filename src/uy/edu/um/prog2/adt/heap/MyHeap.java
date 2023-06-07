package uy.edu.um.prog2.adt.heap;

import uy.edu.um.prog2.adt.collection.MyCollection;

public interface MyHeap<ElementT extends Comparable<ElementT>> extends MyCollection<ElementT> {
    boolean add(ElementT element);

    boolean addAll(MyCollection<? extends ElementT> collection);

    void clear();

    boolean contains(ElementT element);

    boolean containsAll(MyCollection <? extends ElementT> collection);

    ElementT peek();

    boolean equals(Object obj);

    boolean isEmpty();

    ElementT remove();

    boolean remove(ElementT element);

    boolean removeAll(MyCollection<? extends ElementT> collection);

    int size();

    ElementT[] toArray(ElementT[] array);
}
