package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class 从先序遍历还原二叉树 {

    @Test
    public void 从先序遍历还原二叉树() {
        System.out.println("从先序遍历还原二叉树:" + recoverFromPreorder("1-2--3--4-5--6--7"));
    }

    // 我们从二叉树的根节点 root 开始进行深度优先搜索。
    //
    // 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
    //
    // 如果节点只有一个子节点，那么保证该子节点为左子节点。
    //
    // 给出遍历输出 S，还原树并返回其根节点 root。
    //
    //
    //
    // 示例 1：
    //
    //
    //
    // 输入："1-2--3--4-5--6--7"
    // 输出：[1,2,5,3,4,6,7]
    // 示例 2：
    //
    //
    //
    // 输入："1-2--3---4-5--6---7"
    // 输出：[1,2,5,3,null,6,null,4,null,7]
    // 示例 3：
    //
    //
    //
    // 输入："1-401--349---90--88"
    // 输出：[1,401,null,349,88,90]
    //
    //
    // 提示：
    //
    // 原始树中的节点数介于 1 和 1000 之间。
    // 每个节点的值介于 1 和 10 ^ 9 之间。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public TreeNode recoverFromPreorder(String S) {

        // 既然是深度优先遍历，那就使用数据结构“栈”。
        // 栈中保存需要回溯的节点。
        // 由于题目明确，若一个节点只有一个子节点，则子节点为该节点的左子节点，
        // 所以当向下遍历树时，子节点肯定为左子节点，当遇到的节点深度小于等于当前节点深度时，则此节点肯定为栈中（回溯）某个节点的右子节点
        Deque<TreeNode> deque = new LinkedList<>();

        // 为字符串拼接
        StringBuilder stringBuilder = new StringBuilder();

        // 遍历字符串S的下标
        int index = 0;

        // 当前遍历的节点深度
        int tier = 0;
        int len = S.length();

        // 获取第一个数字，即为根节点，深度为0
        while (index < len && S.charAt(index) >= '0' && S.charAt(index) <= '9') {
            stringBuilder.append(S.charAt(index));
            index++;
        }

        TreeNode treeNode = new TreeNode(Integer.parseInt(stringBuilder.toString()));
        deque.offerFirst(treeNode);

        TreeNode childNode;

        // 循环遍历字符串
        while (index < len) {

            // 下一个节点的深度
            int nextTier = 0;

            // 计算下一个节点的深度
            while (S.charAt(index) == '-') {
                nextTier++;
                index++;
            }

            stringBuilder = new StringBuilder();

            // 获取下一个数字
            while (index < len && S.charAt(index) >= '0' && S.charAt(index) <= '9') {
                stringBuilder.append(S.charAt(index));
                index++;
            }

            // 如果下一个节点深度大于当前深度，则根据题意，下一个节点肯定为当前节点的左子节点
            if (nextTier > tier) {
                // 取出父节点（使用peek函数，并不把此节点移出）
                childNode = deque.peekFirst();

                // 下一个节点肯定为当前节点的左子节点
                childNode.left = new TreeNode(Integer.parseInt(stringBuilder.toString()));

                // 再把左子节点加入栈中
                deque.offerFirst(childNode.left);
            }
            // 如果下一个节点深度小于等于当前节点深度，则此节点肯定为栈中（回溯）某个节点的右子节点
            else {

                // 回溯到栈中的某个节点（为下一个节点的父节点，所以判断为nextTier - 1）
                while (tier > (nextTier - 1)) {
                    deque.pollFirst();
                    tier--;
                }

                // 取出父节点（使用peek函数，并不把此节点移出）
                childNode = deque.peekFirst();

                // 下一个节点肯定为当前节点的右子节点
                childNode.right = new TreeNode(Integer.parseInt(stringBuilder.toString()));

                // 再把右子节点加入栈中
                deque.offerFirst(childNode.right);
            }

            // 此时深度为当前子节点深度
            tier = nextTier;
        }

        return treeNode;
    }
}
