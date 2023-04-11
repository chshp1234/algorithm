package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

@Deprecated
public class 最佳买卖股票时机含冷冻期 {
    @Test
    public void 最佳观光组合() {

        int[] pillars = {1, 2, 3, 5, 3, 4, 6, 1, 5};

        System.out.println("最佳观光组合:" + maxProfit(pillars));
    }

    // 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
    //
    // 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
    //
    // 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
    // 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
    // 示例:
    //
    // 输入: [1,2,3,0,2]
    // 输出: 3
    // 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        // f[i][0]: 手上持有股票的最大收益
        // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        int[][] f = new int[n][3];
        f[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            f[i][1] = f[i - 1][0] + prices[i];
            f[i][2] = Math.max(f[i - 1][1], f[i - 1][2]);
        }
        return Math.max(f[n - 1][1], f[n - 1][2]);
    }

    private static class Peak {
        int low;
        int high;
    }
}
