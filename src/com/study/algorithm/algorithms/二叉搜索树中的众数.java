package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 二叉搜索树中的众数 {
    @Test
    public void 二叉搜索树中的众数() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(0);
        treeNode.right = new TreeNode(2);
        treeNode.right.right = new TreeNode(2);
        treeNode.left.left = new TreeNode(0);
        //        int[] b = {5, 2, 6, 2, 3, 2};
        System.out.println("二叉搜索树中的众数：" + Arrays.toString(findMode(treeNode)));
    }

    // 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
    //
    // 假定 BST 有如下定义：
    //
    // 结点左子树中所含结点的值小于等于当前结点的值
    // 结点右子树中所含结点的值大于等于当前结点的值
    // 左子树和右子树都是二叉搜索树
    // 例如：
    // 给定 BST [1,null,2,2],
    //
    //   1
    //    \
    //     2
    //    /
    //   2
    // 返回[2].
    //
    // 提示：如果众数超过1个，不需考虑输出顺序
    //
    // 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int[] findMode(TreeNode root) {

        // 二叉搜索树中找出众数，只需按照中序遍历的顺序，即可保证相同的数字遍历时是相邻的
        // 记录上一个数出现的最大次数，递归遍历（中序遍历）：
        // 1.若当前数和上一个数相同，则记录次数+1
        // 2.若当前数和上一个数不同：
        // 2.1.如果上一个数记录的次数大于最大次数，则清空列表，将此数加入
        // 2.2.如果上一个数记录的次数等于最大次数，则直接加入列表中
        // 3.重置当前数为上一个数字，并将出现的次数记录为1（继续步骤1的判断）

        List<Integer> result = new ArrayList<>();

        // 临时保存的数（1.记录的上一个众数，2.记录的众数出现的次数，3.上一个数，4.上一个数出现的次数）
        int[] temp = new int[4];

        dfs(result, temp, root);

        // 搜索结束，还需再判断一次
        if (temp[3] > temp[1]) {
            result.clear();
            result.add(temp[2]);
        } else if (temp[3] == temp[1] && temp[3] > 0) {
            result.add(temp[2]);
        }

        int len = result.size();
        int[] findMode = new int[len];

        for (int i = 0; i < len; i++) {
            findMode[i] = result.get(i);
        }
        return findMode;
    }

    public void dfs(List<Integer> result, int[] temp, TreeNode root) {
        if (root == null) {
            return;
        }

        // 遍历左节点
        dfs(result, temp, root.left);

        // 遍历根节点
        // 若当前数和上一个数相同
        if (root.val == temp[2]) {
            temp[3]++;
        } else {
            // 如果上一个数记录的次数大于最大次数
            if (temp[3] > temp[1]) {
                result.clear();
                result.add(temp[2]);
                temp[0] = temp[2];
                temp[1] = temp[3];
            }
            // 如果上一个数记录的次数等于最大次数
            else if (temp[3] == temp[1] && temp[3] > 0) {
                result.add(temp[2]);
            }
            // 重置
            temp[2] = root.val;
            temp[3] = 1;
        }

        // 遍历右节点
        dfs(result, temp, root.right);
    }
}
