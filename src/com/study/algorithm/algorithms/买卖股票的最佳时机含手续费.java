package com.study.algorithm.algorithms;

import org.junit.Test;

public class 买卖股票的最佳时机含手续费 {

    @Test
    public void 乘积最大子数组() {
        System.out.println("乘积最大子数组：" + maxProfitByDp(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }

    public int maxProfit(int[] prices, int fee) {

        //贪心
        //找出股票买入的最佳时机和股票卖出的最佳时机。我们围绕手续费，因为只有差价减去手续费大于0时才能获得收益。
        //我们用一个变量‘sale’代表股票可以在此价格卖出，‘buy’代表股票可以在此价格买入。
        //1.sale=0时，代表需要找到一个合适的卖出点。
        //其中当prices[i] - buy > fee，股票在此价格卖出才有收益，可以设此价格prices[i]为卖出点。
        //若还有更低的价格，prices[i] < buy，则可设此价格为买入价格。
        //2.sale不为0，代表已找到一个合适的卖出点，接下来需要确认最佳卖出点（注意这里‘合适’和‘最佳’，‘合适’说明此价格差能获得收益，但不一定是最佳收益）。
        //当sale - prices[i] > fee时，说明此时价格有很大的回落，可以在此进行买入（重新买入），
        //则上一个卖出和买入将为最佳交易价格，计算收益，并重新设置此价格为买入价格，设卖出价格为0（继续上一步，查找合适的卖出价格）；
        //当prices[i] > sale，毫无疑问，这个价格更高，更适合卖出，设为卖出价格。
        //注意：结束时，需注意是否存在卖出价格（sale不为0），有则说明，找到能获得收益的交易价格，不存在（sale为0），则说明之后的交易不会带来收益，则不进行操作。

        int buy = prices[0];
        int sale = 0;

        int result = 0;

        for (int i = 1, len = prices.length; i < len; i++) {
            if (sale == 0) {
                if (prices[i] - buy > fee) {
                    sale = prices[i];
                } else if (prices[i] < buy) {
                    buy = prices[i];
                }
            } else {
                if (sale - prices[i] > fee) {
                    result += sale - buy - fee;
                    buy = prices[i];
                    sale = 0;
                } else if (prices[i] > sale) {
                    sale = prices[i];
                }
            }
        }
        return sale == 0 ? result : result + sale - buy - fee;
    }

    public int maxProfitByDp(int[] prices, int fee) {

        //动态规划
        //题目给的思路很明确了，当前状态，即当前价格下，股票有2种状态，持有和未持有。
        //我们分别计算出持有股票和未持有股票时，当前的收益情况。
        //要算出最佳收益，那肯定是未持有股票时（即股票已全卖出并获得收益）。
        //持有股票时的最佳收益=Max(上一个未持有股票时的收益-当前买入股票价格,上一个持有股票时的收益)；
        //未持有股票的最佳收益=Max(上一个持有股票时的收益+当前卖出股票价格-手续费，上一个未持有股票时的收益)。
        //最后结果，就返回最后未持有股票时的收益即可。

        //定义两个变量，dp[0]代表持有股票时的收益情况，dp[1]代表未持有股票时的收益情况
        int[] dp = new int[2];
        dp[0] = -prices[0];
        for (int i = 1, len = prices.length; i < len; i++) {
            dp[0] = Math.max(dp[1] - prices[i], dp[0]);
            dp[1] = Math.max(prices[i] + dp[0] - fee, dp[1]);
        }
        return dp[1];
    }
}
