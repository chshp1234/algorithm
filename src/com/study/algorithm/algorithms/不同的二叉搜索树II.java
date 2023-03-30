package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 不同的二叉搜索树II {
    @Test
    public void 不同的二叉搜索树II() {
        System.out.println("不同的二叉搜索树II:" + generateTrees(3));
    }

    // 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
    //
    //
    //
    // 示例：
    //
    // 输入：3
    // 输出：
    // [
    //   [1,null,3,2],
    //   [3,2,null,1],
    //   [3,1,null,null,2],
    //   [2,1,3],
    //   [1,null,2,null,3]
    // ]
    // 解释：
    // 以上的输出对应以下 5 种不同结构的二叉搜索树：
    //
    //   1         3     3      2      1
    //    \       /     /      / \      \
    //     3     2     1      1   3      2
    //    /     /       \                 \
    //   2     1         2                 3
    //
    //
    // 提示：
    //
    // 0 <= n <= 8
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        // 思路一：递归+备忘录，备忘录记录数据，递归时先判断是否已有记录，然后再计算起止位置中存在的所有可能的结果
        // 思路二：递推+动态规划，方法如“不同的二叉搜索树”，注意：由于结果需要列出所有可能，所有在计算右子树时，需要对右子树中所有子树的值加上一个偏移量

        // 二维数组备忘录，result[i][j]表示从i到j的数中，共有多少种不同的组合
        ArrayList[][] result = new ArrayList[n + 2][n + 2];

        ArrayList arrayList = dpForGenerateTrees(result, 1, n);

        return arrayList;
    }

    // 递归，备忘录
    private ArrayList dpForGenerateTrees(ArrayList[][] dp, int start, int end) {
        // 如果已有记录
        if (dp[start][end] != null) {
            return dp[start][end];
        }
        // 如果起止相等，则此数当作一个节点
        if (start == end) {
            ArrayList<TreeNode> ele = new ArrayList<>();
            ele.add(new TreeNode(start));
            dp[start][end] = ele;
            return ele;
        }
        // 结果数组
        ArrayList<TreeNode> ele = new ArrayList<>();

        if (start < end) {
            TreeNode treeNode;
            // 计算从start到end的数中，所有的可能结果
            for (int root = start; root <= end; root++) {
                // 获得所有可行的左子树集合
                ArrayList left = dpForGenerateTrees(dp, start, root - 1);
                // 获得所有可行的右子树集合
                ArrayList right = dpForGenerateTrees(dp, root + 1, end);

                // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
                // 由于数组中最少有一个值为null的元素，所以这里不用判断left(right)！=null
                for (int l = 0, ll = left.size(); l < ll; l++) {
                    for (int r = 0, rl = right.size(); r < rl; r++) {
                        treeNode = new TreeNode(root);
                        treeNode.left = (TreeNode) left.get(l);
                        treeNode.right = (TreeNode) right.get(r);
                        ele.add(treeNode);
                    }
                }
            }
            dp[start][end] = ele;
        } else {
            // 如果end>start，则加入null（此时，数组中有一个元素，为null，说明此子树为null，另外，可方便遍历，不用判断数组是否为空）
            ele.add(null);
        }
        return ele;
    }
}
