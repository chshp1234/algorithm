package com.study.algorithm.algorithms;

//给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。由于节点 0 是根，所以 parents[0] == -1 。
//
//一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，剩余部分是若干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。
//
//请你返回有 最高得分 节点的 数目 。
//
// 
//
//示例 1:
//
//
//
//输入：parents = [-1,2,0,2,0]
//输出：3
//解释：
//- 节点 0 的分数为：3 * 1 = 3
//- 节点 1 的分数为：4 = 4
//- 节点 2 的分数为：1 * 1 * 2 = 2
//- 节点 3 的分数为：4 = 4
//- 节点 4 的分数为：4 = 4
//最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
//示例 2：
//
//
//
//输入：parents = [-1,2,0]
//输出：2
//解释：
//- 节点 0 的分数为：2 = 2
//- 节点 1 的分数为：2 = 2
//- 节点 2 的分数为：1 * 1 = 1
//最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。
// 
//
//提示：
//
//n == parents.length
//2 <= n <= 105
//parents[0] == -1
//对于 i != 0 ，有 0 <= parents[i] <= n - 1
//parents 表示一棵二叉树。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/count-nodes-with-the-highest-score
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 统计最高分的节点数目 {
    public int countHighestScoreNodes(int[] parents) {
        //构图
        //先根据parents数组构造二叉树，找出每个节点的子节点
        //再用深度优先遍历方法，统计每个节点的子节点数量
        //最后遍历每个节点，节点的得分就是其左子节点总数*右子节点总数*（总节点数-子节点总数-1）
        //优化：节点的得分可以在遍历每个节点的字节数时进行统计

        int len = parents.length;
        int[][] childCount = new int[len][3];
        int result = 0;
        long maxCount = 0;

        for (int i = 1; i < len; i++) {
            childCount[parents[i]][0]++;
            if (childCount[parents[i]][1] == 0) {
                childCount[parents[i]][1] = i;
            } else {
                childCount[parents[i]][2] = i;
            }
        }

        dfs(childCount, 0);

        childCount[0][0] = len - 1;

        for (int i = 0; i < len; i++) {
            int parentCount = len - 1 - childCount[i][0];
            if (parentCount == 0) {
                parentCount = 1;
            }

            int leftCount;
            if (childCount[i][1] != 0) {
                leftCount = 1 + childCount[childCount[i][1]][0];
            } else {
                leftCount = 1;
            }

            int rightCount;
            if (childCount[i][2] != 0) {
                rightCount = 1 + childCount[childCount[i][2]][0];
            } else {
                rightCount = 1;
            }

            long count = (long) parentCount * leftCount * rightCount;
            if (count > maxCount) {
                maxCount = count;
                result = 1;
            } else if (count == maxCount) {
                result++;
            }
        }

        return result;
    }

    public void dfs(int[][] childCount, int root) {
        if (childCount[root][1] != 0) {
            dfs(childCount, childCount[root][1]);
            childCount[root][0] = childCount[childCount[root][1]][0] + 1;
        }
        if (childCount[root][2] != 0) {
            dfs(childCount, childCount[root][2]);
            childCount[root][0] += childCount[childCount[root][2]][0] + 1;
        }
    }

}
