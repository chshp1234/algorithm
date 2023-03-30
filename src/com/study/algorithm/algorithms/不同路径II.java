package com.study.algorithm.algorithms;

import org.junit.Test;

public class 不同路径II {

    @Test
    public void 不同路径II() {
        System.out.println(
                "不同路径II:"
                        + uniquePathsWithObstaclesScrollArray(
                                new int[][] {{0, 0, 0}, {1, 1, 0}, {0, 0, 0}}));
    }

    // 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
    //
    // 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
    //
    // 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
    //
    //
    //
    // 网格中的障碍物和空位置分别用 1 和 0 来表示。
    //
    // 说明：m 和 n 的值均不超过 100。
    //
    // 示例 1:
    //
    // 输入:
    // [
    //   [0,0,0],
    //   [0,1,0],
    //   [0,0,0]
    // ]
    // 输出: 2
    // 解释:
    // 3x3 网格的正中间有一个障碍物。
    // 从左上角到右下角一共有 2 条不同的路径：
    // 1. 向右 -> 向右 -> 向下 -> 向下
    // 2. 向下 -> 向下 -> 向右 -> 向右
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/unique-paths-ii
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    // 方法一：DP
    // 用一个二维数组，记录从obstacleGrid[0][0]到obstacleGrid[row][col]处共有几条路可以到达
    // 用f(r,c)表示从坐标 (0,0) 到坐标 (r,c) 的路径总数，其中，如果obstacleGrid[r][c]=1，f(r,c)=0，因为此处为障碍物，无法到达。
    // 因为「机器人每次只能向下或者向右移动一步」，所以从坐标 (0,0) 到坐标 (r,c) 的路径总数的值只取决于从坐标 (0,0) 到坐标 (r−1,c)
    // 的路径总数和从坐标 (0,0) 到坐标 (r,c−1) 的路径总数，即 f(r,c) 只能通过f(r−1,c) 和 f(r,c−1) 转移得到。
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        // 如果起点有障碍物，直接返回0
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        // 二维数组，记录从obstacleGrid[0][0]到obstacleGrid[row][col]处共有几条路可以到达
        int[][] temp = new int[row][col];

        temp[0][0] = 1;

        for (int i = 1; i < row; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            } else {
                temp[i][0] = 1;
            }
        }

        for (int i = 1; i < col; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            } else {
                temp[0][i] = 1;
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] != 1) {
                    temp[i][j] = temp[i - 1][j] + temp[i][j - 1];
                }
            }
        }

        return temp[row - 1][col - 1];
    }

    // 方法二：DP+滚动数组
    // 由于这里 f(i,j) 只与 f(i−1,j) 和 f(i,j−1) 相关，我们可以运用「滚动数组思想」把空间复杂度优化成O(m)。
    // 「滚动数组思想」是一种常见的动态规划优化方法，在我们的题目中已经多次使用到，例如「剑指 Offer 46. 把数字翻译成字符串」、「70.爬楼梯」等，
    // 当我们定义的状态在动态规划的转移方程中只和某几个状态相关的时候，就可以考虑这种优化方法，目的是给空间复杂度「降维」。
    // 如果你还不知道什么是「滚动数组思想」，一定要查阅相关资料进行学习哦。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/unique-paths-ii/solution/bu-tong-lu-jing-ii-by-leetcode-solution-2/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int uniquePathsWithObstaclesScrollArray(int[][] obstacleGrid) {

        // 如果起点有障碍物，直接返回0
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        int[] temp = new int[col];

        temp[0] = 1;

        for (int i = 1; i < col; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            } else {
                temp[i] = 1;
            }
        }

        for (int i = 1; i < row; i++) {
            if (obstacleGrid[i][0] == 1) {
                temp[0] = 0;
            }
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] != 1) {
                    temp[j] = temp[j - 1] + temp[j];
                } else {
                    temp[j] = 0;
                }
            }
        }

        return temp[col - 1];
    }
}
