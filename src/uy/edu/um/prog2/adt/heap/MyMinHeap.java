package uy.edu.um.prog2.adt.heap;

import uy.edu.um.prog2.adt.collection.MyCollection;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyMinHeap<ElementT extends Comparable<ElementT>> implements MyHeap<ElementT> {
    private ElementT[] elements;
    private int size;
    private final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public MyMinHeap(int capacity) {
        if (capacity < 0) capacity = DEFAULT_CAPACITY;

        this.elements = (ElementT[]) new Comparable[capacity];
    }

    public MyMinHeap() {
        this(-1);
    }

    @Override
    public boolean add(ElementT element) {
        if (size + 1 > elements.length) {
            grow(1);
        }

        int elementIndex = size;
        elements[elementIndex] = element;
        size++;

        while (elementIndex > 0 && parentOf(elementIndex).compareTo(element) > 0) {
            swapAt(parentIndex(elementIndex), elementIndex);
            elementIndex = parentIndex(elementIndex);
        }
        return true;
    }

    @Override
    public boolean addAll(MyCollection<? extends ElementT> collection) {
        if (size + collection.size() > elements.length) {
            grow(collection.size());
        }

        for (ElementT element : collection) {
            add(element);
        }
        return true;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    @Override
    public boolean contains(ElementT element) {
        for (int i = 0; i < size; i++) {
            if (areEqual(element, elements[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(MyCollection<? extends ElementT> collection) {
        for (ElementT element : collection) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ElementT peek() {
        if (size <= 0) return null;

        return elements[0];
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        MyMinHeap<?> otherHeap = (MyMinHeap<?>) other;
        if (this.size() != otherHeap.size()) return false;
        if (this.size() == 0) return true;
        if (this.peek().getClass() != otherHeap.peek().getClass()) return false;

        @SuppressWarnings("unchecked") MyMinHeap<ElementT> that = (MyMinHeap<ElementT>) otherHeap;
        for (ElementT thatElement : that) {
            if (!this.contains(thatElement)) return false;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public ElementT remove() {
        if (size <= 0) return null;

        ElementT removedElement = elements[0];
        size--;

        ElementT swappedElement = elements[size];
        int swappedElementIndex = 0;
        elements[swappedElementIndex] = swappedElement;

        while (
                swappedElementIndex < size && (
                    (leftChildIndex(swappedElementIndex) < size && leftChildOf(swappedElementIndex).compareTo(swappedElement) < 0) ||
                    (rightChildIndex(swappedElementIndex) < size && rightChildOf(swappedElementIndex).compareTo(swappedElement) < 0)
                )
        ) {
            if (rightChildIndex(swappedElementIndex) >= size || leftChildOf(swappedElementIndex).compareTo(rightChildOf(swappedElementIndex)) < 0) {
                swapAt(leftChildIndex(swappedElementIndex), swappedElementIndex);
                swappedElementIndex = leftChildIndex(swappedElementIndex);
            } else {
                swapAt(rightChildIndex(swappedElementIndex), swappedElementIndex);
                swappedElementIndex = rightChildIndex(swappedElementIndex);
            }
        }
        return removedElement;
    }

    @Override
    public boolean remove(ElementT element) {
        int elementIndex = -1;
        for (int i = 0; i < size; i++) {
            if (areEqual(element, elements[i])) {
                elementIndex = i;
                break;
            }
        }
        if (elementIndex == -1) return false;

        size--;
        ElementT swappedElement = elements[size];
        int swappedElementIndex = elementIndex;
        elements[swappedElementIndex] = swappedElement;

        while (
                swappedElementIndex < size && (
                    (leftChildIndex(swappedElementIndex) < size && leftChildOf(swappedElementIndex).compareTo(swappedElement) < 0) ||
                    (rightChildIndex(swappedElementIndex) < size && rightChildOf(swappedElementIndex).compareTo(swappedElement) < 0)
                )
        ) {
            if (rightChildIndex(swappedElementIndex) >= size || leftChildOf(swappedElementIndex).compareTo(rightChildOf(swappedElementIndex)) < 0) {
                swapAt(leftChildIndex(swappedElementIndex), swappedElementIndex);
                swappedElementIndex = leftChildIndex(swappedElementIndex);
            } else {
                swapAt(rightChildIndex(swappedElementIndex), swappedElementIndex);
                swappedElementIndex = rightChildIndex(swappedElementIndex);
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(MyCollection<? extends ElementT> collection) {
        boolean removedAll = true;
        for (ElementT element : collection) {
            if (!remove(element)) {
                removedAll = false;
            }
        }
        return removedAll;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ElementT[] toArray(ElementT[] array) {
        if (array.length < size) {
            array = (ElementT[]) Array.newInstance(array.getClass().getComponentType(), size);
        }

        for (int i = 0; i < array.length; i++) {
            if (i < size) {
                array[i] = elements[i];
            } else {
                array[i] = null;
            }
        }

        return array;
    }

    @Override
    public Iterator<ElementT> iterator() {
        return new MyIterator();
    }

    @SuppressWarnings("unchecked")
    private void grow(int minimumGrowth) {
        int currentCapacity = elements.length;
        int minimumCapacity = currentCapacity + minimumGrowth;
        int preferredCapacity = currentCapacity / 2;

        if (currentCapacity > 0) {
            int newCapacity = Math.max(minimumCapacity, preferredCapacity);
            elements = Arrays.copyOf(elements, newCapacity);
        } else {
            int newCapacity = Math.max(DEFAULT_CAPACITY, minimumCapacity);
            elements = (ElementT[]) Array.newInstance(elements.getClass().getComponentType(), newCapacity);
        }
    }

    private boolean areEqual(ElementT a, ElementT b) {
        boolean areBothNull = (a == null && b == null);
        boolean isAEqualB = (a != null && a.equals(b));
        return areBothNull || isAEqualB;
    }

    private ElementT parentOf(int index) {
        return elements[parentIndex(index)];
    }

    private ElementT leftChildOf(int index) {
        return elements[leftChildIndex(index)];
    }

    private ElementT rightChildOf(int index) {
        return elements[rightChildIndex(index)];
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int rightChildIndex(int index) {
        return 2 * index + 2;
    }

    private void swapAt(int posA, int posB) {
        ElementT elementA = elements[posA];
        ElementT elementB = elements[posB];

        elements[posA] = elementB;
        elements[posB] = elementA;
    }

    private class MyIterator implements Iterator<ElementT> {
        private int index;

        MyIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index >= elements.length;
        }

        @Override
        public ElementT next() {
            if (!hasNext()) throw new NoSuchElementException();

            return elements[index++];
        }
    }
}
