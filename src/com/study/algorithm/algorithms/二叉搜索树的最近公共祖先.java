package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

public class 二叉搜索树的最近公共祖先 {

    @Test
    public void 二叉搜索树的最近公共祖先() {
        TreeNode treeNode = new TreeNode(6);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(8);
        treeNode.left.left = new TreeNode(0);
        treeNode.left.right = new TreeNode(4);
        treeNode.right.left = new TreeNode(7);
        treeNode.right.right = new TreeNode(9);
        treeNode.left.right.left = new TreeNode(3);
        treeNode.left.right.right = new TreeNode(5);

        System.out.println(
                "二叉搜索树的最近公共祖先:"
                        + lowestCommonAncestor(treeNode, treeNode.left, treeNode.left.right).val);
    }

    // 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
    //
    // 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x
    // 的深度尽可能大（一个节点也可以是它自己的祖先）。”
    //
    // 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
    //
    // 示例 1:
    //
    // 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
    // 输出: 6
    // 解释: 节点 2 和节点 8 的最近公共祖先是 6。
    // 示例 2:
    //
    // 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
    // 输出: 2
    // 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
    //
    //
    // 说明:
    //
    // 所有节点的值都是唯一的。
    // p、q 为不同节点且均存在于给定的二叉搜索树中。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // 在二叉搜索树中寻找公共祖先（最小），首先保证p节点小于q节点
        // ·如果p和q分别位于root节点两侧（或者等于其中一个值，满足q>=root&&p<=root）,则祖先节点就为root（一个节点也可以是它自己的祖先）
        // ·如果root节点小于p节点，则递归判断root.right和p、q的关系
        // ·如果root节点大于q节点，则递归判断root.left和p、q的关系

        if (p.val > q.val) {
            return lowestCommonAncestor(root, q, p);
        }

        return commonAncestor(root, p, q);
    }

    public TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val <= root.val && q.val >= root.val) {
            return root;
        }

        if (root.val > q.val) {
            return commonAncestor(root.left, p, q);
        } else {
            return commonAncestor(root.right, p, q);
        }
    }
}
