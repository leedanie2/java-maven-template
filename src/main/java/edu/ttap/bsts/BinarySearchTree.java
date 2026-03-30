package edu.ttap.bsts;

import java.util.LinkedList;
import java.util.List;

/**
 * A binary tree that satisifies the binary search tree invariant.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    ///// From the reading

    /**
     * A node of the binary search tree.
     */
    private static class Node<T> {
        public T value;
        public Node<T> left;
        public Node<T> right;

        /**
         * @param value the value of the node
         * @param left the left child of the node
         * @param right the right child of the node
         */
        public Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /**
         * @param value the value of the node
         */
        public Node(T value) {
            this(value, null, null);
        }
    }

    private Node<T> root;

    /**
     * Constructs a new empty binary search tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * @param node the root of the tree
     * @return the number of elements in the specified tree
     */
    private int sizeH(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + sizeH(node.left) + sizeH(node.right);
        }
    }

    /**
     * @return the number of elements in this tree
     */
    public int size() {
        return sizeH(root);
    }

    ///// Part 1: Insertion

    /**
     * Inserts the given value into this binary search tree.
     * @param v the value to insert
     */
    public void insert(T v) {
        root = insertH(v, root);
    }

    /** @return the updated tree after inserting h into the given tree */
    private Node<T> insertH(T v, Node<T> cur) {
        if (cur == null) {
            return new Node<>(v);
        } else {
            if (v.compareTo(cur.value) < 0) {
                cur.left = insertH(v, cur.left);
            } else {
                cur.right = insertH(v, cur.right);
            }
            return cur;
        }
    }

    ///// Part 2: Contains
   
    /**
     * @param v the value to find
     * @return true iff this tree contains <code>v</code>
     */
    public boolean contains(T v) {
        return containsH(root, v);
    }

    public boolean containsH(Node<T> cur, T v) {
        if (cur == null) {
            return false;
        } else if (cur.value == v) {
            return true;
        } else if (cur.value.compareTo(v) > 0) {
            return containsH(cur.left, v);
        } else {
            return containsH(cur.right, v);
        }
    }

    ///// Part 3: Ordered Traversals

    /**
     * @return the (linearized) string representation of this BST
     */
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }

    /**
     * @return a list contains the elements of this BST in-order.
     */
    public List<T> toList() {
        LinkedList<T> list = new LinkedList<>();
        toListH(this.root, list);
        return list;
    }

    public void toListH (Node<T> cur, LinkedList<T> list) {
        if(cur == null) {
            return;
        }
        toListH(cur.left, list);
        list.add(cur.value);
        toListH(cur.right, list);
    }


    ///// Part 4: BST Sorting

    /**
     * @param <T> the carrier type of the lists
     * @param lst the list to sort
     * @return a copy of <code>lst</code> but sorted
     * @implSpec <code>sort</code> runs in ___ time if the tree remains balanced. 
     */
    public static <T extends Comparable<? super T>> List<T> sort(List<T> lst) {
        return lst.toList();
    }

    ///// Part 5: Deletion
  
    /*
     * The three cases of deletion are:
     * 1. (TODO: fill me in!)
     * 2. (TODO: fill me in!)
     * 3. (TOOD: fill me in!)
     */

    /**
     * Modifies the tree by deleting the first occurrence of <code>value</code> found
     * in the tree.
     *
     * @param value the value to delete
     */
    public void delete(T value) {
        throw new UnsupportedOperationException();
    }
}