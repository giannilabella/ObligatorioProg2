package uy.edu.um.prog2.adt.bst;

import uy.edu.um.prog2.adt.collection.MyCollection;

import java.lang.reflect.Array;
import java.util.Iterator;

public class MyBSTImpl<ElementT extends Comparable<ElementT>> implements MyBST<ElementT> {
    private Node<ElementT> root;
    private int size;

    public MyBSTImpl() {
        root = null;
        size = 0;
    }

    @Override
    public boolean add(ElementT element) {
        if (element == null) throw new IllegalArgumentException();

        if (root == null) {
            root = new Node<>(element);
            size++;
            return true;
        }
        Node<ElementT> currentNode = root;
        while (currentNode != null) {
            int compareResult = element.compareTo(currentNode.data);
            if (compareResult < 0) {
                if (!currentNode.hasLeftChild()) {
                    currentNode.leftChild = new Node<>(element, currentNode);
                    size++;
                    return true;
                }
                currentNode = currentNode.leftChild;
            } else {
                if (!currentNode.hasRightChild()) {
                    currentNode.rightChild = new Node<>(element, currentNode);
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

        Node<ElementT> currentNode = root;
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
        return new MyInorderIterator<>(root);
    }

    @Override
    public Iterator<ElementT> preorderIterator() {
        return new MyPreorderIterator<>(root);
    }

    @Override
    public Iterator<ElementT> postorderIterator() {
        return new MyPostorderIterator<>(root);
    }

    @Override
    public ElementT maximum() {
        if (root == null) return null;

        Node<ElementT> currentNode = root;
        while (currentNode.hasRightChild()) {
            currentNode = currentNode.rightChild;
        }
        return currentNode.data;
    }

    @Override
    public ElementT minimum() {
        if (root == null) return null;

        Node<ElementT> currentNode = root;
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
        Node<ElementT> currentNode = root;
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

        Node<ElementT> currentNode = root;
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
                    Node<ElementT> successor = currentNode.rightChild;
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

        Node<ElementT> currentNode = root;
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

        Node<ElementT> currentNode = root;
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
        Node<ElementT> currentNode = root;
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
        return toInorderArray(array);
    }

    @Override
    public ElementT[] toInorderArray(ElementT[] array) {
        return iteratorToArray(array, inorderIterator());
    }

    @Override
    public ElementT[] toPreorderArray(ElementT[] array) {
        return iteratorToArray(array, preorderIterator());
    }

    @Override
    public ElementT[] toPostorderArray(ElementT[] array) {
        return iteratorToArray(array, postorderIterator());
    }

    @SuppressWarnings("unchecked")
    private ElementT[] iteratorToArray(ElementT[] array, Iterator<ElementT> iterator) {
        if (array.length < size) {
            array = (ElementT[]) Array.newInstance(array.getClass().getComponentType(), size);
        }

        for (int i = 0; i < array.length; i++) {
            if (iterator.hasNext()) {
                array[i] = iterator.next();
            } else {
                array[i] = null;
            }
        }

        return array;
    }

    @Override
    public Iterator<ElementT> iterator() {
        return new MyInorderIterator<>(root);
    }
}
