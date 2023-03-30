package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

public class 将有序数组转换为二叉搜索树 {
    @Test
    public void 将有序数组转换为二叉搜索树() {
        int[] ints = {-10, -3, 0, 5, 9};

        System.out.println("将有序数组转换为二叉搜索树:" + sortedArrayToBST(ints));
    }
    /**
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     *
     * <p>本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     *
     * <p>示例:
     *
     * <p>给定有序数组: [-10,-3,0,5,9],
     *
     * <p>一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     *
     * <p>0 / \ -3 9 / / -10 5
     *
     * <p>来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        // 每个节点的两棵子树高度差不超过 1，所以每次折中选取根节点
        TreeNode treeNode = new TreeNode(nums[nums.length >> 1]);

        sortedArrayToBST(nums, 0, (nums.length >> 1) - 1, treeNode, true);
        sortedArrayToBST(nums, (nums.length >> 1) + 1, nums.length - 1, treeNode, false);

        return treeNode;
    }

    // 将有序数组转换为二叉搜索树的结果为什么 不唯一 ？
    // 众所周知，二叉搜索树的中序遍历是一个升序序列。
    //
    // 将有序数组作为输入，可以把该问题看做 根据中序遍历序列创建二叉搜索树。
    //
    // 这个问题的答案唯一吗。例如：是否可以根据中序遍历序列和二叉搜索树之间是否一一对应，答案是 否定的。
    //
    // 下面是一些关于 BST 的知识。
    // 中序遍历不能唯一确定一棵二叉搜索树。
    // 先序和后序遍历不能唯一确定一棵二叉搜索树。
    // 先序/后序遍历和中序遍历的关系：
    // inorder = sorted(postorder) = sorted(preorder)，
    // 中序+后序、中序+先序可以唯一确定一棵二叉树。
    //
    // 因此，“有序数组 -> BST”有多种答案。
    //  [-10,-3,0,5,9]
    //
    //       0         5        5       -3
    //     -3 5       0 9     -3 9    -10 5
    //   -10   9    -3      -10 0        0 9
    //            -10
    //
    // 因此，添加一个附件条件：树的高度应该是平衡的、例如：每个节点的两棵子树高度差不超过 1。
    //
    // 这种情况下答案唯一吗？仍然没有。
    // [-10,-3,0,5,9]
    //
    //      0             0            0           0
    //  -10   9        -3   9      -10   5      -3   5
    //    -3 5      -10    5         -3   9   -10      9
    //
    //
    // 高度平衡意味着每次必须选择中间数字作为根节点。这对于奇数个数的数组是有用的，但对偶数个数的数组没有预定义的选择方案。
    //
    //
    //
    // 对于偶数个数的数组，要么选择中间位置左边的元素作为根节点，要么选择中间位置右边的元素作为根节点，不同的选择方案会创建不同的平衡二叉搜索树。
    // 方法一始终选择中间位置左边的元素作为根节点，方法二始终选择中间位置右边的元素作为根节点。方法一和二会生成不同的二叉搜索树，这两种答案都是正确的。
    //
    // 作者：LeetCode
    // 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/solution/jiang-you-xu-shu-zu-zhuan-huan-wei-er-cha-sou-s-15/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    private void sortedArrayToBST(
            int[] nums, int start, int end, TreeNode treeNode, boolean isLeft) {
        if (end == start) {
            TreeNode treeNodeChild = new TreeNode(nums[end]);
            if (isLeft) {
                treeNode.left = treeNodeChild;
            } else {
                treeNode.right = treeNodeChild;
            }
            return;
        }
        if (end < start) {
            return;
        }

        int len = end - start + 1;

        TreeNode treeNodeChild = new TreeNode(nums[start + (len >> 1)]);

        if (isLeft) {
            treeNode.left = treeNodeChild;
        } else {
            treeNode.right = treeNodeChild;
        }
        sortedArrayToBST(nums, start, start + (len >> 1) - 1, treeNodeChild, true);
        sortedArrayToBST(nums, start + (len >> 1) + 1, end, treeNodeChild, false);
    }
}
