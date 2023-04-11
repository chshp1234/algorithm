package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode.Node;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class 填充每个节点的下一个右侧节点指针II {
    @Test
    public void 填充每个节点的下一个右侧节点指针II() {
        Node treeNode = new Node(3);
        treeNode.setLeft(new Node(5));
        treeNode.setRight(new Node(1));
        treeNode.getLeft().setLeft(new Node(4));
        treeNode.getLeft().setRight(new Node(2));
        treeNode.getRight().setLeft(new Node(8));
        treeNode.getRight().getLeft().setRight(new Node(9));
        treeNode.getLeft().getLeft().setLeft(new Node(7));
        treeNode.getLeft().getLeft().setRight(new Node(6));
        //        int[] b = {5, 2, 6, 2, 3, 2};
        System.out.println("填充每个节点的下一个右侧节点指针II：" + connect(treeNode));
    }

    // 给定一个二叉树
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
    // 进阶：
    //
    // 你只能使用常量级额外空间。
    // 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
    //
    //
    // 示例：
    //
    //
    //
    // 输入：root = [1,2,3,4,5,null,7]
    // 输出：[1,#,2,3,#,4,5,7,#]
    // 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
    //
    //
    // 提示：
    //
    // 树中的节点数小于 6000
    // -100 <= node.val <= 100
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public Node connect(Node root) {
        // 当前节点的next指向其右侧节点
        // 层次遍历，使当前节点指向下一个节点即可

        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();

        queue.offer(root);

        Node temp;
        int size;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            size = queue.size();
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
            for (int i = 0; i < size; i++) {
                temp.next = queue.poll();
                temp = temp.next;
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
        }
        return root;
    }

    public Node connectByRecursion(Node root) {
        // next指向其右侧节点，相当于每一层都是从左指向右一个单链表
        // 相比 Ⅰ ，这里不是完美二叉树，所以会出现子节点为空的情况（叶子节点除外）
        // 这里分两种情况：
        // 1.当前节点为左子节点时，其next节点是父节点的右子节点，若右子节点为空，则为父节点的next的左子节点（同理，左子节点为空就为右子节点），
        // 如果next节点左右子节点都为空，重复此步骤，直到next为空或者找到不为空的子节点
        // 2.当前节点为右子节点，则直接找next节点中不为空的最左侧节点（重复步骤1的后半部分）
        // 为方便找到单链表中的next节点，我们可以使用逆前序遍历，从右往左构造next节点
        connect(root, null);
        return root;
    }

    public void connect(Node root, Node next) {
        if (root == null) {
            return;
        }
        // 找到next节点链表中最左侧的子节点
        Node temp = null;
        while (next != null) {
            if (next.left != null && next.left != root) {
                root.next = next.left;
                temp = next.left;
                break;
            } else if (next.right != null && next.right != root) {
                root.next = next.right;
                temp = next.right;
                break;
            } else {
                next = next.next;
            }
        }
        // 为方便找到单链表中的next节点，我们可以使用逆前序遍历（这里递归左子节点时，next为其父节点，因为left的next节点优先指向right节点）
        connect(root.right, temp);
        connect(root.left, root);
    }
}
