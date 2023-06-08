package uy.edu.um.prog2.adt.bst;

class Node<ElementT> {
    public ElementT data;
    public Node<ElementT> parent;
    public Node<ElementT> leftChild;
    public Node<ElementT> rightChild;
    public boolean alreadyTraversed;

    public Node(ElementT element, Node<ElementT> parent, Node<ElementT> leftChild, Node<ElementT> rightChild) {
        this.data = element;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.alreadyTraversed = false;
    }

    public Node(ElementT element, Node<ElementT> parent) {
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
