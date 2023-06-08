package uy.edu.um.prog2.adt.bst;

import java.util.Iterator;
import java.util.NoSuchElementException;

class MyPostorderIterator<ElementT> implements Iterator<ElementT> {
    private Node<ElementT> nextNode;

    MyPostorderIterator(Node<ElementT> rootNode) {
        Node<ElementT> currentNode = rootNode;
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
