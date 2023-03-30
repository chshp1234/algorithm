package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class 从前序与中序遍历序列构造二叉树 {

    @Test
    public void 从前序与中序遍历序列构造二叉树() {

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        System.out.println("从前序与中序遍历序列构造二叉树:" + buildTree(preorder, inorder));
    }

    // 根据一棵树的前序遍历与中序遍历构造二叉树。
    //
    // 注意:
    // 你可以假设树中没有重复的元素。
    //
    // 例如，给出
    //
    // 前序遍历 preorder = [3,9,20,15,7]
    // 中序遍历 inorder = [9,3,15,20,7]
    // 返回如下的二叉树：
    //
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        // 前序遍历结果第一个肯定为根节点
        TreeNode root = new TreeNode(preorder[0]);

        int len = preorder.length;

        // 记录中序遍历中，值的位置
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }

        // 思路：
        // 1.依次遍历前序遍历的结果数组
        // 2.在中序遍历结果中，若当前值和根节点相比，位置靠左，则当前节点在根节点左侧；
        // 3.若当前根节点左子树不为空（已构造），则再把当前节点和左子树作对比（回到2，左子树作为根节点）
        // 4.反之，若当前值和根节点相比，位置靠右，则当前节点在根节点右侧；
        // 5.若当前根节点右子树不为空（已构造），则再把当前节点和右子树作对比（回到4，右子树作为根节点）
        // 5.循环1，直到前序遍历结束，二叉树构造完毕。
        TreeNode temp;
        int index;
        for (int i = 1; i < len; i++) {
            temp = root;

            index = map.get(preorder[i]);

            while (true) {
                if (index < map.get(temp.val)) {
                    if (temp.left != null) {
                        temp = temp.left;
                    } else {
                        temp.left = new TreeNode(preorder[i]);
                        break;
                    }
                } else {
                    if (temp.right != null) {
                        temp = temp.right;
                    } else {
                        temp.right = new TreeNode(preorder[i]);
                        break;
                    }
                }
            }
        }

        return root;
    }
}
