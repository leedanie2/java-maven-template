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

    /**
     * @param cur the current node we are checking
     * @param v the value we want to find
     */
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
        return this.toList().toString();
    }

    /**
     * @return a list contains the elements of this BST in-order.
     */
    public List<T> toList() {
        LinkedList<T> list = new LinkedList<>();
        toListH(this.root, list);
        return list;
    }

    /**
     * @param cur current node you are traversing through in order
     * @param list cumulative list that contains elements of bst
     */
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
        BinarySearchTree bst = new BinarySearchTree<>();
        for(int i = 0; i < lst.size(); i++) {
            bst.insert(lst.get(i));
        }
        return bst.toList();
    }

    ///// Part 5: Deletion
  
    /*
     * The three cases of deletion are:
     * 1. Node has 0 children
     * 2. Node has 1 child
     * 3. Node has 2 children
     */

    /**
     * Modifies the tree by deleting the first occurrence of <code>value</code> found
     * in the tree.
     * Precondition: value is contained in tree
     * 
     * @param value the value to delete
     */
    public void delete(T value) {
        root = deleteH(root, value);
    }

    /**
     * deletes cur by replacing its contents with specified value
     * @param cur node to delete
     * @param value new content of cur
     */
    public Node<T> deleteH(Node<T> cur, T value) {
        if (cur == null) {
            return null;
        }
        int compare = value.compareTo(cur.value);
        if (compare < 0) { // left subtree
            cur.left = deleteH(cur.left, value);
        } else if (compare > 0) { // right subtree
            cur.right = deleteH(cur.right, value);
        } else {
            if (cur.left == null) { // case 1
                return cur.right;
            } 
            if (cur.right == null) { // case 2
                return cur.left;
            }
            // case 3
            Node<T> min = findMin(cur.right);
            cur.value = min.value;
            cur.right = deleteH(cur.right, min.value);
        }
        return cur;
    }
    
    /**
     * Finds the minimum value in a binary search tree
     * @param cur root of tree in which we want to find min value of
     */
    public Node<T> findMin(Node<T> cur) {
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }
}