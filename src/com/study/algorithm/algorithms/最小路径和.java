package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

public class 最小路径和 {

    @Test
    public void 最小路径和() {
        int[][] arrs = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};

        System.out.println("最小路径和：" + minPathSum(arrs));
    }

    private int minPathSum(int[][] arrs) {
        int row = arrs.length;
        int col = arrs[0].length;
        int[][] dp = arrs;

        //        dp[0][0] = arrs[0][0];

        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i] + dp[0][i - 1];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + dp[i][j];
            }
        }

        /*int result = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result = Math.min(dp[i - 1][j], dp[i][j - 1]) + arrs[i][j];
            }
        }*/

        System.out.println("输出：" + (Arrays.deepToString(dp)));
        return dp[row - 1][col - 1];
    }
}
