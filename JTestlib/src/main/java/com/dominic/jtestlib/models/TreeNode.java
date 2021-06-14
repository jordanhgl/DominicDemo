package com.dominic.jtestlib.models;

public class TreeNode {
    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int value;
    public TreeNode left;
    public TreeNode right;

    @Override
    public String toString() {
        return " " + value;
    }
}
