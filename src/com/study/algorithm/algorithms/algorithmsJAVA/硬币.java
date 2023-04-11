package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 硬币 {

    @Test
    public void 面试题08_11_硬币() {

        System.out.println("面试题08_11_硬币:" + waysToChangeByDp(900750));
    }

    /**
     * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
     *
     * <p>示例1:
     *
     * <p>输入: n = 5 输出：2 解释: 有两种方式可以凑成总金额: 5=5 5=1+1+1+1+1 示例2:
     *
     * <p>输入: n = 10 输出：4 解释: 有四种方式可以凑成总金额: 10=10 10=5+5 10=5+1+1+1+1+1 10=1+1+1+1+1+1+1+1+1+1 说明：
     *
     * <p>注意:
     *
     * <p>你可以假设：
     *
     * <p>0 <= n (总金额) <= 1000000
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/coin-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int waysToChange(int n) {
        if (n == 0) {
            return 0;
        }
        return count(n);
    }

    // 只有25（没有10）+只有10（没有25）+只有5（没有25,10）+只有1（1）
    private int count(int n) {
        if (n < 5 && n > 0) {
            return 1;
        } else if (n == 0) {
            return 1;
        } else if (n < 0) {
            return 0;
        } else {
            return count(n - 25)
                    + count(n - 10)
                    - count(n - 35)
                    + count(n - 5)
                    - count(n - 15)
                    - count(n - 30)
                    + count(n - 40)
                    + 1;
        }
    }

    public int waysToChangeByDp(int n) {
        long[] dp = new long[n + 1];

        dp[0] = 1;

        // 只有25（没有10、5）+只有10（没有25、5）+只有5（没有25,10）+只有1（1）+全都有（有5、10、25）
        for (int i = 1; i <= n; i++) {

            if (i >= 5) {
                dp[i] += dp[i - 5];
            }
            if (i >= 10) {
                dp[i] += dp[i - 10];
            }
            if (i >= 15) {
                dp[i] -= dp[i - 15];
            }
            if (i >= 25) {
                dp[i] += dp[i - 25];
            }
            if (i >= 30) {
                dp[i] -= dp[i - 30];
            }
            if (i >= 35) {
                dp[i] -= dp[i - 35];
            }
            if (i >= 40) {
                dp[i] += dp[i - 40];
            }
            dp[i] += 1;
        }

        return (int) (dp[n] % 1000000007);
    }
}
