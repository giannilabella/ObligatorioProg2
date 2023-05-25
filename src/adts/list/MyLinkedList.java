package adts.list;

import adts.collection.MyCollection;

import java.util.Iterator;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class MyLinkedList<ElementT> implements MyList<ElementT> {
    private int size;
    private Node firstNode;
    private Node lastNode;

    public MyLinkedList() {
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
        } else {
            addBeforeNode(element, nodeAtIndex(index));
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
        } else {
            Node referenceNode = nodeAtIndex(index);
            for (ElementT element : collection) {
                addBeforeNode(element, referenceNode);
            }
        }
        return true;
    }

    private void addLastNode(ElementT element) {
        Node newNode = new Node(element);
        newNode.previousNode = lastNode;

        if (lastNode == null) {
            firstNode = newNode;
        } else {
            lastNode.nextNode = newNode;
        }
        lastNode = newNode;
        size++;
    }

    private void addBeforeNode(ElementT element, Node referenceNode) {
        Node newNode = new Node(element);
        newNode.nextNode = referenceNode;
        newNode.previousNode = referenceNode.previousNode;

        if (referenceNode.hasPreviousNode()) {
            referenceNode.previousNode.nextNode = newNode;
        } else {
            firstNode = newNode;
        }
        referenceNode.previousNode = newNode;
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

            currentNode.previousNode = null;
            currentNode.data = null;
            currentNode.nextNode = null;

            currentNode = nextNode;
        }

        firstNode = lastNode = null;
        size = 0;
    }

    @Override
    public boolean contains(ElementT element) {
        for (
                Node leftNode = firstNode, rightNode = lastNode;
                (
                        leftNode == firstNode && rightNode == lastNode
                ) || ((
                        leftNode != null && rightNode != null
                ) && (
                        leftNode.previousNode != rightNode
                ) && (
                        leftNode.previousNode != rightNode.nextNode
                ));
                leftNode = leftNode.nextNode, rightNode = rightNode.previousNode
        ) {
            if (areEqual(leftNode.data, element) || areEqual(rightNode.data, element)) {
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

        MyLinkedList<?> that = (MyLinkedList<?>) other;
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
            if ( areEqual(currentNode.data, element) ) {
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
        int index = size - 1;
        for (Node currentNode = lastNode; currentNode != null; currentNode = currentNode.previousNode) {
            if ( areEqual(currentNode.data, element) ) {
                return index;
            }
            index--;
        }
        return -1;
    }

    private Node nodeAtIndex(int index) {
        Node currentNode;
        final int reverseIndex = size - 1 - index;

        if (index <= reverseIndex) {
            currentNode = firstNode;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.nextNode;
            }
        } else {
            currentNode = lastNode;
            for (int i = 0; i < reverseIndex; i++) {
                currentNode = currentNode.previousNode;
            }
        }

        return currentNode;
    }

    private String outOfBoundsMessage(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public ElementT remove(int index) {
        checkElementIndex(index);

        Node nodeToRemove = nodeAtIndex(index);
        removeNode(nodeToRemove);
        return nodeToRemove.data;
    }

    @Override
    public boolean remove(ElementT element) {
        for (Node currentNode = firstNode; currentNode != null; currentNode = currentNode.nextNode) {
            if ( areEqual(currentNode.data, element) ) {
                removeNode(currentNode);
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

    private void removeNode(Node node) {
        if (node.hasPreviousNode()) {
            node.previousNode.nextNode = node.nextNode;
        } else {
            firstNode = node.nextNode;
        }

        if (node.hasNextNode()) {
            node.nextNode.previousNode = node.previousNode;
        } else {
            lastNode = node.previousNode;
        }

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

        MyList<ElementT> subList = new MyLinkedList<>();

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

        MyIterator(MyLinkedList<ElementT> list) {
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
        public Node previousNode;
        public Node nextNode;

        public Node(ElementT data) {
            this.data = data;
        }

        public boolean hasPreviousNode() {
            return previousNode != null;
        }

        public boolean hasNextNode() {
            return nextNode != null;
        }
    }
}
