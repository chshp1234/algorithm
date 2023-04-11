package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 预测赢家 {

    @Test
    public void 预测赢家() {

        System.out.println("预测赢家:" + PredictTheWinner(new int[] {1, 5, 2}));
    }

    // 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，……
    // 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
    //
    // 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
    //
    //
    //
    // 示例 1：
    //
    // 输入：[1, 5, 2]
    // 输出：False
    // 解释：一开始，玩家1可以从1和2中进行选择。
    // 如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
    // 所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
    // 因此，玩家 1 永远不会成为赢家，返回 False 。
    // 示例 2：
    //
    // 输入：[1, 5, 233, 7]
    // 输出：True
    // 解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
    //     最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 True，表示玩家 1 可以成为赢家。
    //
    //
    // 提示：
    //
    // 1 <= 给定的数组长度 <= 20.
    // 数组里所有分数都为非负数且不会大于 10000000 。
    // 如果最终两个玩家的分数相等，那么玩家 1 仍为赢家。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/predict-the-winner
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int[][] dp;

    public boolean PredictTheWinner(int[] nums) {
        // 动态规划解：定义方程dp[l][h],表示数组区间l~h中，第一个获取的人可获得的最大分数
        // dp[l][h]=Math.max(dp[l+1][h]+nums[l],dp[l][h-1]+nums[h])（不准确，还得根据总和进行计算）
        // *todo*/ 可优化，滚动数组

        int len = nums.length;

        if (len < 2) {
            return true;
        }

        dp = new int[len][len];

        // 数组前缀和
        int[] sum = new int[len + 1];

        sum[1] = nums[0];
        dp[0][0] = nums[0];
        for (int i = 1; i < len; i++) {
            // 初始化数组前缀和
            sum[i + 1] = sum[i] + nums[i];

            // 初始化dp
            dp[i][i] = nums[i];
        }

        // 获取区间0~len的最大值
        int maxResult = getMaxResult(nums, 0, len - 1, sum);

        return maxResult >= (sum[len] - maxResult);
    }

    public int getMaxResult(int[] nums, int low, int high, int[] sum) {

        // 边界条件
        if (low >= nums.length) {
            return 0;
        }

        // 若已有记录，则直接获取
        if (dp[low][high] != 0) {
            return dp[low][high];
        }

        // 若高低位相等，则返回当前元素
        if (low == high) {
            return nums[low];
        }

        // 当选左边元素时，剩余元素中可获得的最大值
        int L = getMaxResult(nums, low + 1, high, sum);

        // 当选右边元素时，剩余元素中可获得的最大值
        int R = getMaxResult(nums, low, high - 1, sum);

        // 当前区间最大值，为下一个区间的元素和-下一个区间的最大值+当前选择元素值
        int i = sum[high + 1] - sum[low + 1] - L + nums[low];
        int j = sum[high] - sum[low] - R + nums[high];

        // 选择最大值记录并返回
        if (i > j) {
            dp[low][high] = i;
            return i;
        } else {
            dp[low][high] = j;
            return j;
        }
    }
}
