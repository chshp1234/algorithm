package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

//给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
//
//两个网格块属于同一 连通分量 需满足下述全部条件：
//
//两个网格块颜色相同
//在上、下、左、右任意一个方向上相邻
//连通分量的边界 是指连通分量中满足下述条件之一的所有网格块：
//
//在上、下、左、右四个方向上与不属于同一连通分量的网格块相邻
//在网格的边界上（第一行/列或最后一行/列）
//请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。
//
// 
//
//示例 1：
//
//输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
//输出：[[3,3],[3,2]]
//示例 2：
//
//输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
//输出：[[1,3,3],[2,3,3]]
//示例 3：
//
//输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
//输出：[[2,2,2],[2,1,2],[2,2,2]]
// 
//
//提示：
//
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 50
//1 <= grid[i][j], color <= 1000
//0 <= row < m
//0 <= col < n
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/coloring-a-border
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 边界着色 {
    @Test
    public void 不同路径II() {
        System.out.println(
                "不同路径II:"
                        + Arrays.deepToString(colorBorder(
                        new int[][]{
                                {1, 1, 2, 2, 2, 1, 1},
                                {1, 2, 2, 2, 2, 2, 1},
                                {1, 2, 2, 2, 2, 2, 1},
                                {1, 2, 2, 2, 2, 2, 1},
                                {1, 1, 1, 1, 1, 1, 1},
                        }, 2, 2, 3)));
    }

    int rBorder;
    int cBorder;

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {

        //深度优先搜索
        //在四个方向上做深度优先搜索
        //若搜索到原颜色，置当前格为0（之后恢复）
        //若搜索到边界，设边界为-1（之后设置）

        //边界
        rBorder = grid.length - 1;
        cBorder = grid[0].length - 1;

        int targetColor = grid[row][col];
        borderShader(grid, row, col, targetColor);
        for (int r = 0; r <= rBorder; r++) {
            for (int c = 0; c <= cBorder; c++) {
                if (grid[r][c] == -1) {
                    //将边界涂色
                    grid[r][c] = color;
                } else if (grid[r][c] == 0) {
                    //将原色涂回
                    grid[r][c] = targetColor;
                }
            }
        }
        return grid;
    }

    public boolean borderShader(int[][] grid, int row, int col, int targetColor) {
        //如果超界了，说明上一格式边界
        if (checkGridBorder(row, col)) {
            return true;
        }

        //如果当前值是0或-1，说明上一格是原色（连通，并且遍历过）
        if (grid[row][col] == 0 || grid[row][col] == -1) {
            return false;
        }

        //如果当前值不为原色，则上一格是边界
        if (grid[row][col] != targetColor) {
            return true;
        }

        //把当前格置0（代表遍历过）
        grid[row][col] = 0;

        //继续后续遍历，并判断当前格是否是边界
        boolean shader = borderShader(grid, row - 1, col, targetColor);
        shader |= borderShader(grid, row, col + 1, targetColor);
        shader |= borderShader(grid, row + 1, col, targetColor);
        shader |= borderShader(grid, row, col - 1, targetColor);

        //是边界，则着色-1（代表遍历过）
        if (shader) {
            grid[row][col] = -1;
        }

        return false;

    }

    //超界判断
    public boolean checkGridBorder(int row, int col) {
        return row < 0 || row > rBorder || col < 0 || col > cBorder;
    }
}
