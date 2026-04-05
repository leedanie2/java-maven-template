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

In-order Traversal: Start at the root, but don't record it. If at a node with no left branch, record the value and go down the right branch. If there is a left branch, go down the left. If at a leaf, record the value, then go up a node. If there is no left branch or it has already been visited, go down the right branch and record the value.

In-order Traversal: 

Preorder Traversal: If at a node, go down the left branch. If there is no left branch, go down the right branch. If at a leaf, go up to the node above, then check if the right branch has been visited. If not, go down the right branch. If so, go up to the next node above. 

Post-Order Traversal: Start at the root, but don't record it. If at a node with no left branch, go down the right branch. If there is a left branch, go down the left. If at a leaf, record the value, then go up a node. If there is a right branch, go down the right. If at a leaf, record the value, then go up a node. If there is a left branch, go down it and record. If not, record the current value and go up until you reach the root. Then, go down the right branch if there is one. Record the root at the end. 

# Binary Search Trees

## Part 2
If current node is null, return false. If value of current node is v, return true. If value of current node is smaller than v, check if left subtree contains v. If value of current node is greater than v, check if right subtree contains v. 