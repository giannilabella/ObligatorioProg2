package uy.edu.um.prog2.adt.bst;

import java.util.Iterator;
import java.util.NoSuchElementException;

class MyPreorderIterator<ElementT> implements Iterator<ElementT> {
    private Node<ElementT> nextNode;

    MyPreorderIterator(Node<ElementT> rootNode) {
        nextNode = rootNode;
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