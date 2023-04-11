package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 整数拆分 {
    @Test
    public void 整数拆分() {
        int n = 5;

        System.out.printf("整数拆分%d=" + integerBreak(n), n);
    }

    // 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
    //
    // 示例 1:
    //
    // 输入: 2
    // 输出: 1
    // 解释: 2 = 1 + 1, 1 × 1 = 1。
    // 示例 2:
    //
    // 输入: 10
    // 输出: 36
    // 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
    // 说明: 你可以假设 n 不小于 2 且不大于 58。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/integer-break
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int integerBreak(int n) {
        //n=5时，最大乘积为6（2*3）
        //所以，n不可能分出一个数字5，因为单一个5就可以分出更大的乘积6
        //同时，不可能分出1，因为这将毫无意义
        //而分出4时，和分出2+2，乘积是一样的，所以，分出2得到的乘积不会小于分出4得到的乘积

        //那么n进行拆分时，数字只可能是2和3

        //根据n=6，时，2*2*2<3*3,可以发现，分出尽可能多的3，将会使乘积最大化
        //根据规律7-10，可得：
        //int pow = n / 3;
        //if (n % 3 == 1) {
        //    return (int) (Math.pow(3, pow) + Math.pow(3, pow - 1));
        //} else if (n % 3 == 2) {
        //    return (int) (Math.pow(3, pow + 1) - Math.pow(3, pow));
        //} else {
        //    return (int) Math.pow(3, pow);
        //}
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        if (n == 4) {
            return 4;
        }

        // You may check the breaking results of n ranging from 7 to 10 to discover the
        // regularities.
        int pow = n / 3;
        if (n % 3 == 1) {
            return (int) (Math.pow(3, pow) + Math.pow(3, pow - 1));
        } else if (n % 3 == 2) {
            return (int) (Math.pow(3, pow + 1) - Math.pow(3, pow));
        } else {
            return (int) Math.pow(3, pow);
        }
    }

    public int integerBreakDp(int n) {
        //动态规划
        //思路类似题解一
        //待分解的数只有2和3
        //那么我们可以使用动态规划来解
        //设dp[i]为数字i分解后可得到的最大乘积
        //那么dp[i]=Math.max(dp[i-2]*2,dp[i-3]*3)(i>=5)
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        if (n == 4) {
            return 4;
        }

        int last1 = 4;
        int last2 = 3;
        int last3 = 2;

        for (int i = 5; i <= n; i++) {
            int temp = last1;
            last1 = Math.max(last2 * 2, last3 * 3);
            last3 = last2;
            last2 = temp;

        }

        return last1;
    }
}
