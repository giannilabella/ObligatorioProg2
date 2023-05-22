package adts.list;

import adts.collection.MyCollection;

public interface MyList<ElementT> extends MyCollection<ElementT> {
    boolean add(ElementT element);

    boolean add(int index, ElementT element);

    boolean addAll(MyCollection<? extends ElementT> collection);

    boolean addAll(int index, MyCollection<? extends ElementT> collection);

    void clear();

    boolean contains(ElementT element);

    boolean containsAll(MyCollection<? extends ElementT> collection);

    boolean equals(Object other);

    ElementT get(int index);

    int indexOf(ElementT element);

    boolean isEmpty();

    int lastIndexOf(ElementT element);

    ElementT remove(int index);

    boolean remove(ElementT element);

    boolean removeAll(MyCollection<? extends ElementT> collection);

    ElementT set(int index, ElementT element);

    int size();

    MyList<ElementT> subList(int fromIndex, int toIndex);

    ElementT[] toArray();
}