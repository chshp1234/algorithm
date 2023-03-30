package com.study.algorithm.algorithms.structure;

public class Node4 {
    public boolean val;
    public boolean isLeaf;
    public Node4 topLeft;
    public Node4 topRight;
    public Node4 bottomLeft;
    public Node4 bottomRight;


    public Node4() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node4(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node4(boolean val, boolean isLeaf, Node4 topLeft, Node4 topRight, Node4 bottomLeft, Node4 bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
