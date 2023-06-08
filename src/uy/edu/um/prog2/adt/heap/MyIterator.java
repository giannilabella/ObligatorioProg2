package uy.edu.um.prog2.adt.heap;

import java.util.Iterator;
import java.util.NoSuchElementException;

class MyIterator<ElementT extends Comparable<ElementT>> implements Iterator<ElementT> {
    private final MyHeap<ElementT> heap;

    MyIterator(MyHeap<ElementT> heap) {
        this.heap = heap;
    }

    @Override
    public boolean hasNext() {
        return !heap.isEmpty();
    }

    @Override
    public ElementT next() {
        if (!hasNext()) throw new NoSuchElementException();

        return heap.remove();
    }
}