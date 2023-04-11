package com.study.algorithm.algorithms.algorithmsJAVA;

//给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
//
//一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
//
//返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
//
// 
//
//示例 1：
//
//
//输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
//输出：3
//解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
//示例 2：
//
//
//输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
//输出：0
//解释：所有 1 都在边界上或可以到达边界。
// 
//
//提示：
//
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 500
//grid[i][j] 的值为 0 或 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/number-of-enclaves
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 飞地的数量 {

    public int numEnclaves(int[][] grid) {
        int row = grid.length - 1;
        int col = grid[0].length - 1;
        for (int c = 0; c <= col; c++) {
            dfs(grid, 0, c);
            dfs(grid, row, c);
        }
        for (int r = 0; r <= row; r++) {
            dfs(grid, r, 0);
            dfs(grid, r, col);
        }

        int count = 0;
        for (int r = 1; r < row; r++) {
            for (int c = 1; c < col; c++) {
                if (grid[r][c] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(int[][] grid, int r, int c) {
        if (r < 0 || r == grid.length || c < 0 || c == grid[0].length || grid[r][c] == 0) {
            return;
        }

        grid[r][c] = 0;

        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);

    }

}
