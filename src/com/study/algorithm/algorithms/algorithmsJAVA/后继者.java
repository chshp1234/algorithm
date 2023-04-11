package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;
import org.junit.Test;

//设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
//
//如果指定节点没有对应的“下一个”节点，则返回null。
//
//示例 1:
//
//输入: root = [2,1,3], p = 1
//
//  2
// / \
//1   3
//
//输出: 2
//示例 2:
//
//输入: root = [5,3,6,2,4,null,null,1], p = 6
//
//      5
//     / \
//    3   6
//   / \
//  2   4
// /
//1
//
//输出: null
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/successor-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 后继者 {
    @Test
    public void 后继者() {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(3);
        treeNode.left.left = new TreeNode(2);
        treeNode.left.right = new TreeNode(4);
        treeNode.right = new TreeNode(6);

        TreeNode p = new TreeNode(4);

        System.out.println("后继者:" + inorderSuccessor(treeNode, p));
    }

    TreeNode result;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //逆中序遍历
        //以右根左的顺序遍历二叉树
        //如果当前节点是目标节点，并且右子树不为空，那么后继节点在右子树，并且是右子树中最左端的树
        //如果当前节点是目标节点，但右子树为空，那么后继节点时当前节点的父节点

        dfs(root, p);
        return result;
    }

    public TreeNode dfs(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        TreeNode r = dfs(root.right, p);
        //如果当前节点是目标节点
        if (root.val == p.val) {
            //右子树不为空
            if (r != null) {
                //后继节点为右子树中最左边的树
                result = r;
                return null;
            }
            //返回目标节点（此处只作为标记）
            return p;
        }

        //如果右子树返回的是目标节点，则说明目标节点在右子树中，并且在右子树的最右端节点，那么其后继节点为当前节点父节点
        if (r == p) {
            return r;
        }
        TreeNode l = dfs(root.left, p);
        //如果result不为空，说明后继节点已找到
        if (result != null) {
            return null;
        }
        //如果左子树返回的是目标节点，则说明目标节点在左子树中，并且还未找到目标节点，那么其后继节点为当前节点
        if (l == p) {
            result = root;
        }
        //返回左子树，或者当前节点
        if (l != null) {
            return l;
        }
        return root;
    }
}
