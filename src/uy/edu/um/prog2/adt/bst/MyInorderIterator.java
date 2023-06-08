package uy.edu.um.prog2.adt.bst;

import java.util.Iterator;
import java.util.NoSuchElementException;

class MyInorderIterator<ElementT> implements Iterator<ElementT> {
    private Node<ElementT> nextNode;

    MyInorderIterator(Node<ElementT> rootNode) {
        Node<ElementT> currentNode = rootNode;
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
            Node<ElementT> currentNode = nextNode.rightChild;
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
