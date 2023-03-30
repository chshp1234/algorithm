package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
//
// 
//
//为了让您更好地理解问题，以下面的二叉搜索树为例：
//
// 
//
//
//
// 
//
//我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
//
//下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
//
// 
//
//
//
// 
//
//特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 二叉搜索树与双向链表 {
    @Test
    public void 二叉搜索树与双向链表() {
        Node node = new Node(3);
        System.out.println("二叉搜索树与双向链表:" + treeToDoublyList(node));
    }

    private List<Node> nodeList = new ArrayList<>();

    public Node treeToDoublyList(Node root) {
        //平衡二叉树的中序遍历，即为一个递增的序列
        //先将节点加入数组中，在对数组中的每一个节点的左右节点进行重新链接
        //注意节点数量为1时，需要其左右节点需指向自身
        if (root == null) {
            return null;
        }
        dfs(root);
        int len = nodeList.size();
        for (int i = 1, l = len - 1; i < l; i++) {
            nodeList.get(i).left = nodeList.get(i - 1);
            nodeList.get(i).right = nodeList.get(i + 1);
        }
        if (len > 1) {
            nodeList.get(0).right = nodeList.get(1);
            nodeList.get(0).left = nodeList.get(len - 1);
            nodeList.get(len - 1).left = nodeList.get(len - 2);
            nodeList.get(len - 1).right = nodeList.get(0);
        } else {
            nodeList.get(0).left = nodeList.get(0).right = nodeList.get(0);
        }
        return nodeList.get(0);
    }

    public void dfs(Node node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        nodeList.add(node);
        dfs(node.right);
    }

    class Node {
        public int  val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
