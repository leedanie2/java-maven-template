package edu.ttap.trees;

import java.util.List;
import java.util.LinkedList;

/**
 * A generic binary tree implementation.
 */
public class Tree<T> {
    /**
     * A node of the binary tree.
     */
    public static class Node<T> {

        public T value;

        public Node<T> left;

        public Node<T> right;

        /**
         * @param value the value of the node
         * @param left the left child of the node
         * @param right the right child of the node
         */
        Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /**
         * @param value the value of the node
         */
        Node(T value) {
            this(value, null, null);
        }
    }

    ///// From the reading...

    private Node<T> root;

    /**
     * Constructs a new, empty binary tree.
     */
    public Tree() {
        this.root = null;
    }

    /**
     * @return a sample binary tree for testing purposes
     */
    public static Tree<Integer> makeSampleTree() {
        Tree<Integer> tree = new Tree<Integer>();
        tree.root = new Node<>(
            5,
            new Node<>(2,
                new Node<>(1),
                new Node<>(3)
            ),
            new Node<>(8,
                new Node<>(7,
                    new Node<>(6),
                    null),
                new Node<>(9,
                    null,
                    new Node<>(10)))
        );
        return tree;
    }


    /**
     * @param node the root of the tree 
     * @return the number elements found in this tree rooted at node
     */
    private int sizeH(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + sizeH(node.left) + sizeH(node.right);
        }
    }

    /** @return the number of elements in the tree */
    public int size() {
        return sizeH(root);
    }

    ///// Part 1: Contains

    /**
     * @param node the root of the tree
     * @param value the value to search for
     * @return if the tree contains value
     */
    private boolean containsH(Node<T> node, T value) {
        if (node == null) {
            return false;
        } else if (node.value == value) {
            return true;
        } else {
            return containsH(node.left, value) || containsH(node.right, value);
        }
    }

    /**
     * @param value the value to search for
     * @return true iff the tree contains <code>value</code>
     */
    public boolean contains(T value) {
        return containsH(root, value);
    }

    ///// Part 2: Traversals

    /**
     * @return the elements of this tree collected via an in-order traversal
     */
    public List<T> toListInorder() {
        LinkedList<T> list = new LinkedList<>();
        toListInOrderH(this.root, list);
        return list;
    }

    /**
     * @param cur current node that is being traversed in-order
     * @param list cumulative list that contains all the elements in-order
     */
    public void toListInOrderH(Node<T> cur, LinkedList<T> list) {
        if (cur == null) {
            return;
        }
        toListInOrderH(cur.left, list);
        list.add(cur.value);
        toListInOrderH(cur.right, list);
    }

    /**
     * @return the elements of this tree collected via a pre-order traversal
     */
    public List<T> toListPreorder() {
        LinkedList<T> list = new LinkedList<>();
        toListPreOrderH(this.root, list);
        return list;
    }

    /**
     * @param cur current node that is being traversed pre-order
     * @param list cumulative list that contains all the elements pre-order
     */
    public void toListPreOrderH(Node<T> cur, LinkedList<T> list) {
        if (cur == null) {
            return;
        }
        list.add(cur.value);
        toListPreOrderH(cur.left, list);
        toListPreOrderH(cur.right, list);
    }

    /**
     * @return the elements of this tree collected via a post-order traversal
     */
    public List<T> toListPostorder() {
        LinkedList<T> list = new LinkedList<>();
        toListPostOrderH(this.root, list);
        return list;
    }

    /**
     * @param cur current node that is being traversed post-order
     * @param list cumulative list that contains all the elements post-order
     */
    public void toListPostOrderH(Node<T> cur, LinkedList<T> list) {
        if (cur == null) {
            return;
        }
        toListPostOrderH(cur.left, list);
        toListPostOrderH(cur.right, list);
        list.add(cur.value);
    }

    ///// Part 3: Stringifying Trees
   
    /**
     * @return a string represent of this tree in the form, "[x1, ..., xk]."
     * The order of the elements is left unspecified.
     */
    @Override
    public String toString() {
        return this.toListPreorder().toString();
    }

    ///// Extra: Pretty Printing
    
    /**
     * @return a string represent of this tree in bulleted list form.
     */
    public String toPrettyString() {
        throw new UnsupportedOperationException();
    }

    /**
     * The main driver for this program
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Nothing to do. 'Run' via the JUnit tests instead!");
    }
}