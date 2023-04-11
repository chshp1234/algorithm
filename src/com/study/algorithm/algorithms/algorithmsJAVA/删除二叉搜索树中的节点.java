package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

//给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
//
//一般来说，删除节点可分为两个步骤：
//
//首先找到需要删除的节点；
//如果找到了，删除它。
// 
//
//示例 1:
//
//
//
//输入：root = [5,3,6,2,4,null,7], key = 3
//输出：[5,4,6,2,null,null,7]
//解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
//一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
//另一个正确答案是 [5,2,6,null,4,null,7]。
//
//
//示例 2:
//
//输入: root = [5,3,6,2,4,null,7], key = 0
//输出: [5,3,6,2,4,null,7]
//解释: 二叉树不包含值为 0 的节点
//示例 3:
//
//输入: root = [], key = 0
//输出: []
// 
//
//提示:
//
//节点数的范围 [0, 104].
//-105 <= Node.val <= 105
//节点值唯一
//root 是合法的二叉搜索树
//-105 <= key <= 105
// 
//
//进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/delete-node-in-a-bst
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 删除二叉搜索树中的节点 {
    public TreeNode deleteNode(TreeNode root, int key) {
        //深度优先搜索
        //前序遍历二叉树，当遍历到目标节点时，将右子树放在当前节点位置，并找到右子树中最左侧的左子树，将当前节点的左子树挂在刚找到的最左侧的左子树的左子树下
        //递归时，我们还需要维护一个当前节点父节点，以及判断当前节点是父节点左子树还是右子树，这样才能将当前节点删除后，正确的将下一个节点挂在父节点的正确位置
        //因为根节点也有可能是要删除的节点，我们初始化一个dummy节点， 将根节点挂在dummy的左子树下，最后返回dummy节点的左子树即可
        TreeNode dummy = new TreeNode();
        dummy.left = root;
        dfs(root, key, true, dummy);
        return dummy.left;
    }

    public boolean dfs(TreeNode node, int key, boolean left, TreeNode parent) {
        if (node == null) {
            return false;
        }
        if (node.val == key) {
            if (left) {
                if (node.left == null) {
                    parent.left = node.right;
                    node.right = null;
                } else if (node.right == null) {
                    parent.left = node.left;
                    node.left = null;
                } else {
                    parent.left = node.right;
                    node.right = null;
                    TreeNode treeNode = parent.left;
                    while (treeNode.left != null) {
                        treeNode = treeNode.left;
                    }
                    treeNode.left = node.left;
                    node.left = null;
                }
            } else {
                if (node.left == null) {
                    parent.right = node.right;
                    node.right = null;
                } else if (node.right == null) {
                    parent.right = node.left;
                    node.left = null;
                } else {
                    parent.right = node.right;
                    node.right = null;
                    TreeNode treeNode = parent.right;
                    while (treeNode.left != null) {
                        treeNode = treeNode.left;
                    }
                    treeNode.left = node.left;
                    node.left = null;
                }
            }
            return true;
        }
        if (dfs(node.left, key, true, node)) {
            return true;
        }

        return dfs(node.right, key, false, node);
    }
}
