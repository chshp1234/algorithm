package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;
import com.study.algorithm.algorithms.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class 有序链表转换二叉搜索树 {

    // 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
    //
    // 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
    //
    // 示例:
    //
    // 给定的有序链表： [-10, -3, 0, 5, 9],
    //
    // 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
    //
    //      0
    //     / \
    //   -3   9
    //   /   /
    // -10  5
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public TreeNode sortedListToBST(ListNode listNode) {
        // 思路1：把单链表转换成数组，在根据数组中间值作为二叉树根节点，左边部分作为左节点，右边部分作为右节点
        // 思路2：也是找到中间位置，在把中间值作为根节点的思路，但这里不用转换成数组的形式，
        // 使用快慢指针，当快的指针指到头时，慢指针的位置就是中间节点的位置（快指针速度为慢指针两倍）

        if (listNode == null) {
            return null;
        }

        List<TreeNode> nums = new ArrayList<>();

        while (listNode != null) {
            nums.add(new TreeNode(listNode.val));
            listNode = listNode.next;
        }

        TreeNode treeNode = nums.get(nums.size() >> 1);

        sortedArrayToBST(nums, 0, (nums.size() >> 1) - 1, treeNode, true);
        sortedArrayToBST(nums, (nums.size() >> 1) + 1, nums.size() - 1, treeNode, false);

        return treeNode;
    }

    private void sortedArrayToBST(
            List<TreeNode> nums, int start, int end, TreeNode treeNode, boolean isLeft) {
        if (end == start) {
            TreeNode treeNodeChild = nums.get(end);
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

        TreeNode treeNodeChild = nums.get(start + (len >> 1));

        if (isLeft) {
            treeNode.left = treeNodeChild;
        } else {
            treeNode.right = treeNodeChild;
        }
        sortedArrayToBST(nums, start, start + (len >> 1) - 1, treeNodeChild, true);
        sortedArrayToBST(nums, start + (len >> 1) + 1, end, treeNodeChild, false);
    }
}
