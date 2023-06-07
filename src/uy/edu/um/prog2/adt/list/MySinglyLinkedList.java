package uy.edu.um.prog2.adt.list;

import uy.edu.um.prog2.adt.collection.MyCollection;

import java.util.Iterator;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class MySinglyLinkedList<ElementT> implements MyList<ElementT> {
    private int size;
    private Node firstNode;
    private Node lastNode;

    public MySinglyLinkedList() {
        this.size = 0;
        this.firstNode = null;
        this.lastNode = null;
    }

    @Override
    public boolean add(ElementT element) {
        addLastNode(element);
        return true;
    }

    @Override
    public boolean add(int index, ElementT element) {
        checkPositionIndex(index);

        if (index == size) {
            addLastNode(element);
        } else if (index == 0) {
            addFirstNode(element);
        } else {
            addAfterNode(element, nodeAtIndex(index - 1));
        }

        return true;
    }

    @Override
    public boolean addAll(MyCollection<? extends ElementT> collection) {
        for (ElementT element : collection) {
            addLastNode(element);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, MyCollection<? extends ElementT> collection) {
        checkPositionIndex(index);

        if (index == size) {
            for (ElementT element : collection) {
                addLastNode(element);
            }
        } else if (index == 0) {
            Node referanceNode = null;
            for (ElementT element : collection) {
                if (referanceNode == null) {
                    addFirstNode(element);
                    referanceNode = firstNode;
                } else {
                    referanceNode = addAfterNode(element, referanceNode);
                }
            }
        } else {
            Node referenceNode = nodeAtIndex(index - 1);
            for (ElementT element : collection) {
                referenceNode = addAfterNode(element, referenceNode);
            }
        }
        return true;
    }

    private Node addAfterNode(ElementT element, Node referenceNode) {
        Node newNode = new Node(element);
        newNode.nextNode = referenceNode;

        if (referenceNode.hasNextNode()) {
            newNode.nextNode = referenceNode.nextNode;
        } else {
            lastNode = newNode;
        }
        referenceNode.nextNode = newNode;
        size++;
        return newNode;
    }

    private void addFirstNode(ElementT element) {
        Node newNode = new Node(element);

        if (lastNode == null) {
            lastNode = newNode;
        } else {
            newNode.nextNode = firstNode;
        }
        firstNode = newNode;
        size++;
    }

    private void addLastNode(ElementT element) {
        Node newNode = new Node(element);

        if (lastNode == null) {
            firstNode = newNode;
        } else {
            lastNode.nextNode = newNode;
        }
        lastNode = newNode;
        size++;
    }

    private boolean areEqual(ElementT a, ElementT b) {
        boolean areBothNull = (a == null && b == null);
        boolean isAEqualB = (a != null && a.equals(b));
        return areBothNull || isAEqualB;
    }

    private void checkElementIndex(int index) throws IndexOutOfBoundsException {
        if (!isValidElementIndex(index)) throw new IndexOutOfBoundsException(outOfBoundsMessage(index));
    }

    private void checkPositionIndex(int index) throws IndexOutOfBoundsException {
        if (!isValidPositionIndex(index)) throw new IndexOutOfBoundsException(outOfBoundsMessage(index));
    }

    @Override
    public void clear() {
        for (Node currentNode = firstNode; currentNode != null;) {
            Node nextNode = currentNode.nextNode;

            currentNode.data = null;
            currentNode.nextNode = null;

            currentNode = nextNode;
        }

        firstNode = lastNode = null;
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
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        MySinglyLinkedList<?> that = (MySinglyLinkedList<?>) other;
        if (this.size != that.size) return false;

        for (int i = 0; i < this.size; i++) {
            if (this.get(i) != that.get(i)) return false;
        }
        return true;
    }

    @Override
    public ElementT get(int index) throws IndexOutOfBoundsException {
        checkElementIndex(index);

        return nodeAtIndex(index).data;
    }

    @Override
    public int indexOf(ElementT element) {
        int index = 0;
        for (Node currentNode = firstNode; currentNode != null; currentNode = currentNode.nextNode) {
            if (areEqual(currentNode.data, element)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isValidElementIndex(int index) {
        return 0 <= index && index < size;
    }

    private boolean isValidPositionIndex(int index) {
        return 0 <= index && index <= size;
    }

    @Override
    public int lastIndexOf(ElementT element) {
        int index = 0;
        int lastIndex = -1;
        for (Node currentNode = firstNode; currentNode != null; currentNode = currentNode.nextNode) {
            if (areEqual(currentNode.data, element)) {
                lastIndex = index;
            }
            index++;
        }
        return lastIndex;
    }

    private Node nodeAtIndex(int index) {
        Node currentNode;

        currentNode = firstNode;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.nextNode;
        }

        return currentNode;
    }

    private String outOfBoundsMessage(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public ElementT remove(int index) {
        checkElementIndex(index);

        Node nodeToRemove;
        if (index == 0) {
            nodeToRemove = firstNode;
            removeFirstNode();
        } else {
            Node previousToNodeToRemove = nodeAtIndex(index - 1);
            nodeToRemove = previousToNodeToRemove.nextNode;
            removeNode(nodeToRemove, previousToNodeToRemove);
        }
        return nodeToRemove.data;
    }

    @Override
    public boolean remove(ElementT element) {
        for (Node currentNode = firstNode, previousNode = null; currentNode != null; previousNode = currentNode, currentNode = currentNode.nextNode) {
            if (areEqual(currentNode.data, element)) {
                if (previousNode == null) {
                    removeFirstNode();
                } else {
                    removeNode(currentNode, previousNode);
                }
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

    private void removeFirstNode() {
        if (firstNode == lastNode) {
            firstNode = null;
            lastNode = null;
        } else {
            firstNode = firstNode.nextNode;
        }
        size--;
    }

    private void removeNode(Node node, Node previousNode) {
        if (node == lastNode) {
            lastNode = previousNode;
        }
        previousNode.nextNode = node.nextNode;
        size--;
    }

    @Override
    public ElementT set(int index, ElementT element) {
        checkElementIndex(index);

        Node node = nodeAtIndex(index);
        ElementT previousElement = node.data;
        node.data = element;
        return previousElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public MyList<ElementT> subList(int fromIndex, int toIndex) {
        checkElementIndex(fromIndex);
        checkElementIndex(toIndex);

        MyList<ElementT> subList = new MySinglyLinkedList<>();

        Node currentNode = nodeAtIndex(fromIndex);
        for (int i = fromIndex; i <= toIndex; i++) {
            subList.add(currentNode.data);
            currentNode = currentNode.nextNode;
        }

        return subList;
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

        MyIterator(MySinglyLinkedList<ElementT> list) {
            nextNode = list.firstNode;
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

        public boolean hasNextNode() {
            return nextNode != null;
        }
    }
}
