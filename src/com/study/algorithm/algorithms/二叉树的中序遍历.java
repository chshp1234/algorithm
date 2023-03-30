package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的中序遍历 {

    @Test
    public void 二叉树的中序遍历() {
        TreeNode treeNode = new TreeNode(3);
        treeNode.setLeft(new TreeNode(5));
        treeNode.setRight(new TreeNode(1));
        treeNode.getLeft().setLeft(new TreeNode(4));
        treeNode.getLeft().setRight(new TreeNode(2));
        treeNode.getRight().setLeft(new TreeNode(8));
        treeNode.getRight().getLeft().setRight(new TreeNode(9));
        treeNode.getLeft().getLeft().setLeft(new TreeNode(7));
        treeNode.getLeft().getLeft().setRight(new TreeNode(6));
        //        int[] b = {5, 2, 6, 2, 3, 2};
        System.out.println("二叉树的中序遍历：" + inorderTraversal(treeNode));
    }

    public List<Integer> inorderTraversal(TreeNode root) {

        // 二叉树的中序遍历，左子节点——根节点——右子节点
        // 可使用深度优先遍历（递归），先遍历左子节点，在遍历根节点，此时把根节点加入列表，最后遍历右子节点
        // 在遍历左右子节点的过程中，也应遵循以上中序遍历的顺序
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    public void dfs(TreeNode root, List<Integer> result) {
        // 递归结束条件
        if (root == null) {
            return;
        }
        // 遍历左子节点
        dfs(root.left, result);
        // 遍历根节点
        result.add(root.val);
        // 遍历右子节点
        dfs(root.right, result);
    }

    public List<Integer> inorderTraversalByMorris(TreeNode root) {

        // 思路与算法
        //
        // Morris 遍历算法是另一种遍历二叉树的方法，它能将非递归的中序遍历空间复杂度降为O(1)。
        //
        // Morris 遍历算法整体步骤如下（假设当前遍历到的节点为 x）：
        //
        // 如果 x 无左孩子，先将 x 的值加入答案数组，再访问 x 的右孩子，即 x=x.right。
        // 如果 x 有左孩子，则找到 x 左子树上最右的节点（即左子树中序遍历的最后一个节点，x 在中序遍历中的前驱节点），我们记为
        // predecessor。根据 predecessor 的右孩子是否为空，进行如下操作。
        // 如果 predecessor 的右孩子为空，则将其右孩子指向 x，然后访问 x 的左孩子，即 x=x.left。
        // 如果 predecessor 的右孩子不为空，则此时其右孩子指向 x，说明我们已经遍历完 x 的左子树，我们将predecessor 的右孩子置空，
        // 将 x 的值加入答案数组，然后访问 x 的右孩子，即 x=x.right。
        // 重复上述操作，直至访问完整棵树。
        //
        // 其实整个过程我们就多做一步：假设当前遍历到的节点为 x，将 x 的左子树中最右边的节点的右孩子指向 x，
        // 这样在左子树遍历完成后我们通过这个指向走回了 x，且能通过这个指向知晓我们已经遍历完成了左子树，而不用再通过栈来维护，
        // 省去了栈的空间复杂度。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/er-cha-shu-de-zhong-xu-bian-li-by-leetcode-solutio/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        List<Integer> res = new ArrayList<Integer>();
        TreeNode predecessor = null;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    res.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}
