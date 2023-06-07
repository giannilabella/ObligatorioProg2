package uy.edu.um.prog2.adt.stack;

import uy.edu.um.prog2.adt.collection.MyCollection;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedStack<ElementT> implements MyStack<ElementT> {
    private int size;
    private Node firstNode;

    @Override
    public boolean add(ElementT element) {
        Node newNode = new Node(element);
        newNode.nextNode = firstNode;

        firstNode = newNode;
        size++;
        return true;
    }

    @Override
    public boolean addAll(MyCollection<? extends ElementT> collection) {
        for (ElementT element : collection) {
            add(element);
        }
        return true;
    }

    private boolean areEqual(ElementT a, ElementT b) {
        boolean areBothNull = (a == null && b == null);
        boolean isAEqualB = (a != null && a.equals(b));
        return areBothNull || isAEqualB;
    }

    @Override
    public void clear() {
        for (Node currentNode = firstNode; currentNode != null;) {
            Node nextNode = currentNode.nextNode;

            currentNode.data = null;
            currentNode.nextNode = null;

            currentNode = nextNode;
        }

        firstNode = null;
        size = 0;
    }

    @Override
    public boolean contains(ElementT element) {
        for (Node currentNode = firstNode; currentNode != null; currentNode = currentNode.nextNode) {
            if (areEqual(currentNode.data, element)) {
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
        if (firstNode == null) {
            return null;
        }

        return firstNode.data;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        MyLinkedStack<?> otherStack = (MyLinkedStack<?>) other;
        if (this.size != otherStack.size) return false;
        if (this.size == 0) return true;

        if (this.peek().getClass() != otherStack.peek().getClass()) return false;
        @SuppressWarnings("unchecked") MyLinkedStack<ElementT> that = (MyLinkedStack<ElementT>) otherStack;

        Node currentNode = firstNode;
        for (ElementT element : that) {
            if (!areEqual(currentNode.data, element)) return false;

            currentNode = currentNode.nextNode;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public ElementT remove() {
        ElementT removedElement = firstNode.data;
        firstNode = firstNode.nextNode;
        size--;
        return removedElement;
    }

    @Override
    public boolean remove(ElementT element) {
        for (Node previousNode = null, currentNode = firstNode; currentNode != null; previousNode = currentNode, currentNode = currentNode.nextNode) {
            if (areEqual(currentNode.data, element)) {
                if (previousNode == null) {
                    firstNode = firstNode.nextNode;
                } else {
                    previousNode.nextNode = currentNode.nextNode;
                }
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(MyCollection<? extends ElementT> collection) {
        boolean changed = false;
        for (ElementT element : collection) {
            if (remove(element)) changed = true;
        }
        return changed;
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

        Node currentNode = firstNode;
        for (int i = 0; i < array.length; i++) {
            if (currentNode != null) {
                array[i] = currentNode.data;
                currentNode = currentNode.nextNode;
            } else {
                array[i] = null;
            }
        }

        return array;
    }

    @Override
    public Iterator<ElementT> iterator() {
        return new MyIterator(this);
    }

    private class MyIterator implements Iterator<ElementT> {
        private Node nextNode;

        MyIterator(MyLinkedStack<ElementT> stack) {
            nextNode = stack.firstNode;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public ElementT next() {
            if (!hasNext()) throw new NoSuchElementException();

            ElementT element = nextNode.data;
            nextNode = nextNode.nextNode;
            return element;
        }
    }

    private class Node {
        public ElementT data;
        public Node nextNode;

        public Node(ElementT data) {
            this.data = data;
        }
    }
}
