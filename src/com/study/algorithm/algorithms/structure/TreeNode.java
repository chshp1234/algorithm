package com.study.algorithm.algorithms.structure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int x) {
        val = x;
    }

    public int getLeftVal() {
        return left == null ? 0 : left.val;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public int getRightVal() {
        return right == null ? 0 : right.val;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }


    @Override
    public String toString() {
        return "\n\tTreeNode" +
                "{\n"
                + "\t\tval=" + val + "\n"
                + "\t\tleft=" + left + "\n"
                + "\t\tright=" + right + "\n"
                + "\t}";
    }

    public static String toArrayString(TreeNode root) {
        if (root == null) {
            return "";
        }

        ArrayList<Integer> lists = new ArrayList<>();

        // 辅助队列容器
        Queue<TreeNode> containerHelper = new LinkedList<>();
        containerHelper.offer(root);

        TreeNode treeNode;

        // 迭代，tier标识当前层序
        while (!containerHelper.isEmpty()) {
            int size = containerHelper.size();
            for (int i = 0; i < size; i++) {
                treeNode = containerHelper.poll();
                if (treeNode != null) {
                    lists.add(treeNode.val);
                    containerHelper.offer(treeNode.left);
                    containerHelper.offer(treeNode.right);
                } else {
                    lists.add(null);
                }
            }
        }
        return lists.toString();
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" + "val=" + val + ", next=" + next + '}';
        }
    }
}
