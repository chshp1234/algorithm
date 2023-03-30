package com.study.algorithm.algorithms;

import org.junit.Test;

//给你一个大小为 m x n 的二进制矩阵 grid 。
//
//岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
//
//岛屿的面积是岛上值为 1 的单元格的数目。
//
//计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
//
// 
//
//示例 1：
//
//
//输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
//输出：6
//解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
//示例 2：
//
//输入：grid = [[0,0,0,0,0,0,0,0]]
//输出：0
// 
//
//提示：
//
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 50
//grid[i][j] 为 0 或 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/max-area-of-island
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 岛屿的最大面积 {
    @Test
    public void 岛屿的最大面积() {
        System.out.println("岛屿的最大面积:" + maxAreaOfIsland(
                new int[][]{{0, 1, 0, 0, 0, 0, 0, 0}, {0, 1, 1, 0, 0, 0, 0, 0}}));
    }

    public int maxAreaOfIsland(int[][] grid) {
        //深度优先搜索
        //深度优先搜索遍历二维数组，并用一个数组记录已经遍历过的元素格子
        //当元素为1时，继续遍历其上下左右4个方位的数，累加并+1
        int result = 0;

        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visit = new boolean[row][col];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                //没遍历过，开始遍历并更新result
                if (!visit[r][c]) {
                    result = Math.max(result, dfs(grid, r, c, visit));
                }
            }
        }

        return result;
    }

    public int dfs(int[][] grid, int row, int col, boolean[][] visit) {
        if (!border(grid, row, col) || visit[row][col]) {
            return 0;
        }
        visit[row][col] = true;
        if (grid[row][col] == 0) {
            return 0;
        }
        //遍历4个方位，并+1（此格子没被遍历过，并且元素为1）
        return dfs(grid, row - 1, col, visit) +
                dfs(grid, row + 1, col, visit) +
                dfs(grid, row, col - 1, visit) +
                dfs(grid, row, col + 1, visit) +
                1;
    }

    //边界判断
    public boolean border(int[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }
}
