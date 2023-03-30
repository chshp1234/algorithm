package com.study.algorithm.algorithms;

import java.util.Arrays;

//假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
//
//当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的正整数矩阵 costs 来表示的。
//
//例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。
//
//请计算出粉刷完所有房子最少的花费成本。
//
// 
//
//示例 1：
//
//输入: costs = [[17,2,17],[16,16,5],[14,3,19]]
//输出: 10
//解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
//     最少花费: 2 + 5 + 3 = 10。
//示例 2：
//
//输入: costs = [[7,6,2]]
//输出: 2
// 
//
//提示:
//
//costs.length == n
//costs[i].length == 3
//1 <= n <= 100
//1 <= costs[i][j] <= 20
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/JEj789
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 粉刷房子 {
    public int minCost(int[][] costs) {
        //动态规划+滚动数组
        //设dp[i][j]表示将第i个房子粉刷成颜色j（红，蓝，绿）时，需要的最小花费是多少
        //dp[i][0]=Math.min(dp[i-1][1], dp[i-1][2]) + costs[i][0];
        //dp[i][1]=Math.min(dp[i-1][0], dp[i-1][2]) + costs[i][1];
        //dp[i][2]=Math.min(dp[i-1][0], dp[i-1][1]) + costs[i][2];
        //因为当前状态dp[i]只跟上一个状态dp[i-1]有关，所以可以只用3个数来表示将上一个房子粉刷成红蓝绿时各最小花费是多少
        int[] dp = {costs[0][0], costs[0][1], costs[0][2]};

        for (int i = 1, l = costs.length; i < l; i++) {
            int redCost = dp[0];
            dp[0] = Math.min(dp[1], dp[2]) + costs[i][0];
            int blueCost = dp[1];
            dp[1] = Math.min(redCost, dp[2]) + costs[i][1];
            dp[2] = Math.min(redCost, blueCost) + costs[i][2];
        }
        return Arrays.stream(dp).min().getAsInt();
    }
}
