package uy.edu.um.prog2.adt.bst;

import uy.edu.um.prog2.adt.collection.MyCollection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyBSTImpl<ElementT extends Comparable<ElementT>> implements MyBST<ElementT> {
    private Node root;
    private int size;

    public MyBSTImpl() {
        root = null;
        size = 0;
    }

    @Override
    public boolean add(ElementT element) {
        if (element == null) throw new IllegalArgumentException();

        if (root == null) {
            root = new Node(element);
            size++;
            return true;
        }
        Node currentNode = root;
        while (currentNode != null) {
            int compareResult = element.compareTo(currentNode.data);
            if (compareResult < 0) {
                if (!currentNode.hasLeftChild()) {
                    currentNode.leftChild = new Node(element, currentNode);
                    size++;
                    return true;
                }
                currentNode = currentNode.leftChild;
            } else {
                if (!currentNode.hasRightChild()) {
                    currentNode.rightChild = new Node(element, currentNode);
                    size++;
                    return true;
                }
                currentNode = currentNode.rightChild;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(MyCollection<? extends ElementT> collection) {
        boolean addedAll = true;
        for (ElementT element : collection) {
            if (!add(element)) {
                addedAll = false;
            }
        }
        return addedAll;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean contains(ElementT element) {
        if (element == null) throw new IllegalArgumentException();

        if (root == null) {
            return false;
        }

        Node currentNode = root;
        while (currentNode != null) {
            int compareResult = element.compareTo(currentNode.data);

            if (compareResult == 0) {
                return true;
            } else if (compareResult < 0) {
                currentNode = currentNode.leftChild;
            } else {
                currentNode = currentNode.rightChild;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(MyCollection<? extends ElementT> collection) {
        boolean containsAll = true;
        for (ElementT element : collection) {
            if (!contains(element)) {
                containsAll = false;
            }
        }
        return containsAll;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<ElementT> inorderIterator() {
        return new MyInorderIterator(this);
    }

    @Override
    public Iterator<ElementT> preorderIterator() {
        return new MyPreorderIterator(this);
    }

    @Override
    public Iterator<ElementT> postorderIterator() {
        return new MyPostorderIterator(this);
    }

    @Override
    public ElementT maximum() {
        if (root == null) return null;

        Node currentNode = root;
        while (currentNode.hasRightChild()) {
            currentNode = currentNode.rightChild;
        }
        return currentNode.data;
    }

    @Override
    public ElementT minimum() {
        if (root == null) return null;

        Node currentNode = root;
        while (currentNode.hasLeftChild()) {
            currentNode = currentNode.leftChild;
        }
        return currentNode.data;
    }

    @Override
    public ElementT predecessor(ElementT element) {
        if (element == null) throw new IllegalArgumentException();

        if (root == null) return null;

        ElementT predecessor = null;
        Node currentNode = root;
        while (currentNode != null) {
            if ((
                    predecessor == null ||
                    predecessor.compareTo(currentNode.data) < 0
                ) &&
                    element.compareTo(currentNode.data) > 0
            ) {
                predecessor = currentNode.data;
            }

            int compareResult = element.compareTo(currentNode.data);
            if (compareResult < 0) {
                currentNode = currentNode.leftChild;
            } else if (compareResult > 0) {
                currentNode = currentNode.rightChild;
            } else {
                if (currentNode.hasLeftChild()) {
                    currentNode = currentNode.leftChild;
                    while (currentNode.hasRightChild()) {
                        currentNode = currentNode.rightChild;
                    }
                    predecessor = currentNode.data;
                }
                return predecessor;
            }
        }
        return null;
    }

    @Override
    public boolean remove(ElementT element) {
        if (element == null) throw new IllegalArgumentException();

        if (root == null) return false;

        Node currentNode = root;
        while (currentNode != null) {
            int compareResult = element.compareTo(currentNode.data);
            if (compareResult < 0) {
                currentNode = currentNode.leftChild;
            } else if (compareResult > 0) {
                currentNode = currentNode.rightChild;
            } else {
                if (!currentNode.hasLeftChild() && !currentNode.hasRightChild()) {
                    if (!currentNode.hasParent()) {
                        root = null;
                    } else if (currentNode.parent.leftChild == currentNode) {
                        currentNode.parent.leftChild = null;
                    } else {
                        currentNode.parent.rightChild = null;
                    }
                    size--;
                    return true;
                } else if (currentNode.hasLeftChild() && !currentNode.hasRightChild()) {
                    if (!currentNode.hasParent()) {
                        root = currentNode.leftChild;
                        root.parent = null;
                    } else if (currentNode.parent.leftChild == currentNode) {
                        currentNode.parent.leftChild = currentNode.leftChild;
                    } else {
                        currentNode.parent.rightChild = currentNode.leftChild;
                    }
                    size--;
                    return true;
                } else if (!currentNode.hasLeftChild()) {
                    if (!currentNode.hasParent()) {
                        root = currentNode.rightChild;
                        root.parent = null;
                    } else if (currentNode.parent.leftChild == currentNode) {
                        currentNode.parent.leftChild = currentNode.rightChild;
                    } else {
                        currentNode.parent.rightChild = currentNode.rightChild;
                    }
                    size--;
                    return true;
                } else {
                    Node successor = currentNode.rightChild;
                    while (successor.hasLeftChild()) {
                        successor = successor.leftChild;
                    }

                    if (successor.hasRightChild()) {
                        if (successor.parent == currentNode) {
                            currentNode.rightChild = successor.rightChild;
                        } else {
                            successor.parent.leftChild = successor.rightChild;
                        }
                    }

                    if (!currentNode.hasParent()) {
                        root = successor;
                        root.parent = null;
                    } else if (currentNode.parent.leftChild == currentNode) {
                        currentNode.parent.leftChild = successor;
                    } else {
                        currentNode.parent.rightChild = successor;
                    }
                    successor.leftChild = currentNode.leftChild;
                    successor.rightChild = currentNode.rightChild;
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(MyCollection<? extends ElementT> collection) {
        boolean removeAll = true;
        for (ElementT element : collection) {
            if (!remove(element)) {
                removeAll = false;
            }
        }
        return removeAll;
    }

    @Override
    public ElementT removeMaximum() {
        if (root == null) return null;

        Node currentNode = root;
        while (currentNode.hasRightChild()) {
            currentNode = currentNode.rightChild;
        }

        if (currentNode.hasParent()) {
            currentNode.parent.rightChild = null;
        } else {
            root = currentNode.leftChild;
            if (root != null)
                root.parent = null;
        }
        size--;
        return currentNode.data;
    }

    @Override
    public ElementT removeMinimum() {
        if (root == null) return null;

        Node currentNode = root;
        while (currentNode.hasLeftChild()) {
            currentNode = currentNode.leftChild;
        }

        if (currentNode.hasParent()) {
            currentNode.parent.leftChild = null;
        } else {
            root = currentNode.rightChild;
            if (root != null)
                root.parent = null;
        }
        size--;
        return currentNode.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ElementT successor(ElementT element) {
        if (element == null) throw new IllegalArgumentException();

        if (root == null) return null;

        ElementT successor = null;
        Node currentNode = root;
        while (currentNode != null) {
            if ((
                    successor == null ||
                    successor.compareTo(currentNode.data) > 0
            ) &&
                    element.compareTo(currentNode.data) < 0
            ) {
                successor = currentNode.data;
            }

            int compareResult = element.compareTo(currentNode.data);
            if (compareResult < 0) {
                currentNode = currentNode.leftChild;
            } else if (compareResult > 0) {
                currentNode = currentNode.rightChild;
            } else {
                if (currentNode.hasRightChild()) {
                    currentNode = currentNode.rightChild;
                    while (currentNode.hasLeftChild()) {
                        currentNode = currentNode.leftChild;
                    }
                    successor = currentNode.data;
                }
                return successor;
            }
        }
        return null;
    }

    @Override
    public ElementT[] toArray(ElementT[] array) {
        return null;
    }

    @Override
    public Iterator<ElementT> iterator() {
        return new MyInorderIterator(this);
    }

    private class MyInorderIterator implements Iterator<ElementT> {
        private Node nextNode;

        MyInorderIterator(MyBSTImpl<ElementT> bst) {
            Node currentNode = bst.root;
            while (currentNode.hasLeftChild()) {
                currentNode = currentNode.leftChild;
            }
            nextNode = currentNode;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public ElementT next() {
            if (!hasNext()) throw new NoSuchElementException();

            ElementT element = nextNode.data;
            if (nextNode.hasRightChild()) {
                Node currentNode = nextNode.rightChild;
                while (currentNode.hasLeftChild()) {
                    currentNode = currentNode.leftChild;
                }
                nextNode = currentNode;
            } else {
                if (nextNode.parent.leftChild == nextNode) {
                    nextNode.alreadyTraversed = true;
                    nextNode = nextNode.parent;
                } else {
                    while (nextNode != null && (!nextNode.hasLeftChild() || nextNode.leftChild.alreadyTraversed)) {
                        if (nextNode.hasLeftChild())
                            nextNode.leftChild.alreadyTraversed = false;
                        nextNode = nextNode.parent;
                    }
                    if (nextNode != null) {
                        nextNode.leftChild.alreadyTraversed = true;
                    }
                }
            }
            return element;
        }
    }

    private class MyPreorderIterator implements Iterator<ElementT> {
        private Node nextNode;

        MyPreorderIterator(MyBSTImpl<ElementT> bst) {
            nextNode = bst.root;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public ElementT next() {
            if (!hasNext()) throw new NoSuchElementException();

            ElementT element = nextNode.data;
            nextNode.alreadyTraversed = true;
            if (nextNode.hasLeftChild()) {
                nextNode = nextNode.leftChild;
            } else {
                if (nextNode.parent.leftChild == nextNode) {
                    while (nextNode != null && !nextNode.hasRightChild()) {
                        nextNode.alreadyTraversed = false;
                        nextNode = nextNode.parent;
                    }
                    if (nextNode != null) {
                        nextNode = nextNode.rightChild;
                    }
                } else {
                    while (nextNode != null && nextNode.alreadyTraversed) {
                        nextNode = nextNode.parent;

                        if (nextNode != null) {
                            if (nextNode.hasRightChild() && !nextNode.rightChild.alreadyTraversed) {
                                nextNode = nextNode.rightChild;
                                break;
                            } else if (nextNode.hasRightChild()) {
                                nextNode.rightChild.alreadyTraversed = false;
                            }
                        }
                    }
                }
            }
            return element;
        }
    }

    private class MyPostorderIterator implements Iterator<ElementT> {
        private Node nextNode;

        MyPostorderIterator(MyBSTImpl<ElementT> bst) {
            Node currentNode = bst.root;
            while (currentNode.hasRightChild()) {
                currentNode = currentNode.rightChild;
            }
            nextNode = currentNode;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public ElementT next() {
            if (!hasNext()) throw new NoSuchElementException();

            ElementT element = nextNode.data;
            nextNode.alreadyTraversed = true;

            while (nextNode != null && nextNode.alreadyTraversed) {
                if (!nextNode.hasParent()) {
                    nextNode.alreadyTraversed = false;
                }
                nextNode = nextNode.parent;

                if (nextNode != null) {
                    if (nextNode.hasLeftChild() && !nextNode.leftChild.alreadyTraversed) {
                        nextNode = nextNode.leftChild;
                        while (nextNode.hasRightChild()) {
                            nextNode = nextNode.rightChild;
                        }
                    } else if (!nextNode.hasRightChild() || nextNode.rightChild.alreadyTraversed) {
                        if (nextNode.hasLeftChild()) nextNode.leftChild.alreadyTraversed = false;
                        if (nextNode.hasRightChild()) nextNode.rightChild.alreadyTraversed = false;
                    }
                }
            }
            return element;
        }
    }

    private class Node {
        public ElementT data;
        public Node parent;
        public Node leftChild;
        public Node rightChild;
        public boolean alreadyTraversed;

        public Node(ElementT element, Node parent, Node leftChild, Node rightChild) {
            this.data = element;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.alreadyTraversed = false;
        }

        public Node(ElementT element, Node parent) {
            this(element, parent, null, null);
        }

        public Node(ElementT element) {
            this(element, null);
        }

        public boolean hasParent() {
            return parent != null;
        }

        public boolean hasLeftChild() {
            return leftChild != null;
        }

        public boolean hasRightChild() {
            return rightChild != null;
        }
    }
}
