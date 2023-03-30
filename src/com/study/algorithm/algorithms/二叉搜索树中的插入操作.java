package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

public class 二叉搜索树中的插入操作 {

    @Test
    public void 二叉搜索树中的插入操作() {
        TreeNode treeNode = new TreeNode(2);
        treeNode.left = new TreeNode(0);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(-4);
        treeNode.left.right = new TreeNode(1);
        //        int[] b = {5, 2, 6, 2, 3, 2};
        System.out.println("二叉搜索树中的插入操作：" + insertIntoBST(treeNode, 4));
    }

    // 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
    //
    // 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
    //
    //
    //
    // 例如,
    //
    // 给定二叉搜索树:
    //
    //        4
    //       / \
    //      2   7
    //     / \
    //    1   3
    //
    // 和 插入的值: 5
    // 你可以返回这个二叉搜索树:
    //
    //         4
    //       /   \
    //      2     7
    //     / \   /
    //    1   3 5
    // 或者这个树也是有效的:
    //
    //         5
    //       /   \
    //      2     7
    //     / \
    //    1   3
    //         \
    //          4
    //
    //
    // 提示：
    //
    // 给定的树上的节点数介于 0 和 10^4 之间
    // 每个节点都有一个唯一整数值，取值范围从 0 到 10^8
    // -10^8 <= val <= 10^8
    // 新值和原始二叉搜索树中的任意节点值都不同
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public TreeNode insertIntoBST(TreeNode root, int val) {

        // 首先回顾二叉搜索树的性质：对于任意节点 root 而言，左子树（如果存在）上所有节点的值均小于root.val，
        // 右子树（如果存在）上所有节点的值均大于 root.val，且它们都是二叉搜索树。
        //
        // 因此，当将 val 插入到以 root 为根的子树上时，根据 val 与 root.val 的大小关系，就可以确定要将 val 插入到哪个子树中。
        //
        // 如果该子树不为空，则问题转化成了将 val 插入到对应子树上。
        // 否则，在此处新建一个以 val 为值的节点，并链接到其父节点 root 上。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/solution/er-cha-sou-suo-shu-zhong-de-cha-ru-cao-zuo-by-le-3/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        // 递归、迭代都OK
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }
}
