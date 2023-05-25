package adts.stack;

import adts.collection.MyCollection;

public interface MyStack<ElementT> extends MyCollection<ElementT> {
    boolean add(ElementT element);

    boolean addAll(MyCollection<? extends ElementT> collection);

    void clear();

    boolean contains(ElementT element);

    boolean containsAll(MyCollection <? extends ElementT> collection);

    ElementT peek();

    boolean equals(Object other);

    boolean isEmpty();

    ElementT remove();

    boolean remove(ElementT element);

    boolean removeAll(MyCollection<? extends ElementT> collection);

    int size();

    ElementT[] toArray(ElementT[] array);
}
