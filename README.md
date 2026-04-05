# Trees

Authors: Daniel, Thomas

## Build commands

*   Compilation and execution: `mvn compile exec:java`.
*   Compilation and testing: `mvn compile test`.
*   Check style: `mvn checkstyle:checkstyle`.

## Resources

*   (_TODO: fill in resources here!_)
*   ...
*   ...

## Part 1

If the node is empty, it is false.
If the node's value is v, it is true. 
Else, check if the left or the right subtree contains v. 

## Part 2

Pre-order Traversal: If it is a leaf, return the value, and return up in the recursion. If it is a node, add the value for the Node to the list first. Then recursively call and go down the left subtree. After that recursively call go down the right subtree.

In-order Traversal: If it is a leaf, return the value, and return up in the recursion. If it is a node, recursively call the left subtree and go down. Then add value to the list and recursively call the right subtree.

Post-order Traversal: If it is a leaf, return the value, and return up in the recursion. If it is a node, recursively call the left and then the right sub tree. Eventually add the value to the list. 

# Binary Search Trees

## Part 2
If current node is null, return false. If value of current node is v, return true. If value of current node is smaller than v, check if left subtree contains v. If value of current node is greater than v, check if right subtree contains v. 