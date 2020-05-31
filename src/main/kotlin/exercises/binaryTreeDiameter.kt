package exercises

import kotlin.math.max

/*
https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/529/week-2/3293/

Given a binary tree, you need to compute the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \
      4   5
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
*/

fun main() {
    println(diameterOfBinaryTree(TreeNode(1, TreeNode(2, TreeNode(4), TreeNode(5)), TreeNode(3))))
}

fun diameterOfBinaryTree(root: TreeNode?): Int {
    var leftMax = 0
    var rightMax = 0
    var leftDiameter = 0
    var rightDiameter = 0
    if (root?.left != null) {
        leftMax = goDeeper(root?.left!!)
        leftDiameter = diameterOfBinaryTree(root?.left)
    }
    if (root?.right != null) {
        rightMax = goDeeper(root?.right!!)
        rightDiameter = diameterOfBinaryTree(root?.right)
    }
    return max(leftMax + rightMax, max(leftDiameter, rightDiameter))
}

fun goDeeper(node: TreeNode): Int {
    var leftMax = 1
    var rightMax = 1
    if (node.left != null) leftMax = goDeeper(node.left!!) + 1
    if (node.right != null) rightMax = goDeeper(node.right!!) + 1
    return max(rightMax, leftMax)
}

class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)