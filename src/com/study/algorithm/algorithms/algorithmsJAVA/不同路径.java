package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

public class 不同路径 {
    @Test
    public void 不同路径() {
        System.out.println("不同路径:" + uniquePaths(1, 2));
    }

    public int uniquePaths(int m, int n) {

        // 动态规划
        // 由于我们只能一次向右或者乡下移动，所以根据规律可以得出，到达位置(m,n)处的路径数量，等于到达位置（m-1,n）的路径数量+到达位置(m,n-1)的数量。
        // 那么状态转移方程即为：f(m,n)=f(m-1,n)+f(m,n-1)。
        // 其中我们要忽略m=0和n=0的情况，因为此时机器人是“贴墙”的，只能往一个方向行走，所以m=0或者n=0的情况，路径都为1。
        // 最终答案即为f(m-1,n-1)。
        //
        // 我们可以使用滚动数组的思想，对空间进行优化，因为当前位置只和左边位置数量和上面位置数量有关，
        // 所以可得f(m)=f(m-1)+f(m),其中f(m-1)即为左边的路径数量，f(m)即为上边的路径数量。

        int[] dp = new int[m];

        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[j] = dp[j - 1] + dp[j];
            }
        }

        return dp[m - 1];
    }
}
