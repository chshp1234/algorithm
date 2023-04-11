package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
//
// 
//
//示例 1:
//
//输入:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 12
//解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
// 
//
//提示：
//
//0 < grid.length <= 200
//0 < grid[0].length <= 200
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 礼物的最大价值 {
    @Test
    public void 礼物的最大价值() {
        int[][] ints = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println("礼物的最大价值：" + maxValue(ints));
    }

    public int maxValue(int[][] grid) {
        //动态规划
        //动态转移方程：dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]) + grid[i][j];
        //空间优化，滚动数组，当前状态只和上一层状态有关
        //dp[i] = Math.max(dp[i-1], dp[i]) + grid[i][j];
        int row = grid.length;
        int col = grid[0].length;
        int[] dp = new int[col + 1];


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[j + 1] = Math.max(dp[j], dp[j + 1]) + grid[i][j];
            }
        }
        return dp[col];
    }
}
