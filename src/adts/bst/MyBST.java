package adts.bst;

import java.util.Iterator;

import adts.collection.MyCollection;

public interface MyBST<ElementT extends Comparable<ElementT>> extends MyCollection<ElementT> {
    boolean add(ElementT element);

    boolean addAll(MyCollection<? extends ElementT> collection);

    void clear();

    boolean contains(ElementT element);

    boolean containsAll(MyCollection<? extends ElementT> collection);

    boolean equals(Object obj);

    boolean isEmpty();

    Iterator<ElementT> inorderIterator();

    Iterator<ElementT> preorderIterator();

    Iterator<ElementT> postorderIterator();

    ElementT maximum();

    ElementT minimum();

    ElementT predecessor(ElementT element);

    boolean remove(ElementT element);

    boolean removeAll(MyCollection<? extends ElementT> collection);

    ElementT removeMaximum();

    ElementT removeMinimum();

    int size();

    ElementT successor(ElementT element);

    ElementT[] toArray(ElementT[] array);
}
