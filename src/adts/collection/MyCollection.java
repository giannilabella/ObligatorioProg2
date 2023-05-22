package adts.collection;

public interface MyCollection<ElementT> extends Iterable<ElementT> {
    boolean add(ElementT element);

    boolean addAll(MyCollection<? extends ElementT> collection);

    void clear();

    boolean contains(ElementT element);

    boolean containsAll(MyCollection<? extends ElementT> collection);

    boolean equals(Object obj);

    boolean isEmpty();

    boolean remove(ElementT element);

    boolean removeAll(MyCollection<? extends ElementT> collection);

    int size();

    ElementT[] toArray(ElementT[] array);
}
