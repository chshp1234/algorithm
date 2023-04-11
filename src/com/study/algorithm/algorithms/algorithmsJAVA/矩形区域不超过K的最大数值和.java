package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

@Deprecated
public class 矩形区域不超过K的最大数值和 {
    @Test
    public void 矩形区域不超过K的最大数值和() {
        int[][] ints = new int[][]{{5, -4, -3, 4}, {-3, -4, 4, 5}, {5, 1, 5, -4}};
        System.out.println("矩形区域不超过K的最大数值和:" + maxSumSubmatrix(ints, 8));
    }

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int col    = matrix[0].length;
        int result = -101;

        int[][] dp     = new int[col][col];
        int[]   preSum = new int[col + 1];

        //此解法可寻找最大值
        for (int[] rows : matrix) {
            for (int end = 0; end < col; end++) {
                int sum = preSum[end + 1] = preSum[end] + rows[end];
                for (int start = 0; start <= end; start++) {
                    int i = sum - preSum[start];
                    if (dp[start][end] > 0) {
                        dp[start][end] += i;
                    } else {
                        dp[start][end] = i;
                    }

                    if (dp[start][end] <= k && dp[start][end] > result) {
                        result = dp[start][end];
                    }
                }
            }
        }


        return result;
    }
}
