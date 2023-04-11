package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode.Node;

import org.junit.Test;

public class 填充每个节点的下一个右侧节点指针 {
    @Test
    public void 填充每个节点的下一个右侧节点指针II() {
        Node treeNode = new Node(1);
        treeNode.setLeft(new Node(2));
        treeNode.setRight(new Node(3));
        treeNode.getLeft().setLeft(new Node(4));
        treeNode.getLeft().setRight(new Node(5));
        treeNode.getRight().setLeft(new Node(6));
        treeNode.getRight().setRight(new Node(7));
        //        int[] b = {5, 2, 6, 2, 3, 2};
        System.out.println("填充每个节点的下一个右侧节点指针II：" + connect(treeNode));
    }

    // 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
    //
    // struct Node {
    //  int val;
    //  Node *left;
    //  Node *right;
    //  Node *next;
    // }
    // 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
    //
    // 初始状态下，所有 next 指针都被设置为 NULL。
    //
    //
    //
    // 示例：
    //
    //
    //
    // 输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
    //
    // 输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}
    //
    // 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
    //
    //
    // 提示：
    //
    // 你只能使用常量级额外空间。
    // 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public Node connect(Node root) {

        // next指向其右侧节点
        // 因为是完美二叉树，不用考虑空子节点（叶子节点不算），则其左子树可以直接指向右子树，右子树，指向父节点右侧节点的左子树

        if (root == null) {
            return null;
        }
        connect(root.left, root.right, null);
        return root;
    }

    public void connect(Node left, Node right, Node next) {

        if (left == null) {
            return;
        }

        // 左子树指向右子树
        left.next = right;
        // 右子树指向父节点的next节点的左子树
        right.next = next;
        // 递归调用左右子树
        // 递归左子树时，其中next节点参数就为右子树的左子节点
        connect(left.left, left.right, right.left);
        // 递归右子树时，其中next节点参数为当前next节点的左子树（若为空则传空）
        connect(right.left, right.right, next == null ? null : next.left);
    }
}
