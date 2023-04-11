package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

@Deprecated
public class 岛屿数量 {
    @Test
    public void 岛屿数量() {
        // 求 1+2+...+n
        char[][] chars =
                new char[][] {
                        {'1', '1', '1', '1', '0'},
                        {'1', '1', '0', '1', '0'},
                        {'1', '1', '0', '0', '0'},
                        {'0', '0', '0', '0', '0'}
                };

        System.out.println("岛屿数量:" + numIslands(chars));
    }
    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     *
     * <p>岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     *
     * <p>此外，你可以假设该网格的四条边均被水包围。
     *
     * <p>示例 1:
     *
     * <p>输入: 11110 11010 11000 00000 输出: 1 示例 2:
     *
     * <p>输入: 11000 11000 00100 00011 输出: 3 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/number-of-islands
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int numIslands(char[][] grid) {
        /*todo 未完成*/
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int num = 0;
        int row = grid.length, col = grid[0].length, i, j;

        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    if ((i == 0 || grid[i - 1][j] == '0')
                            && (row == 1 || (i < row - 1 && grid[i + 1][j] == '0'))
                            && (j == 0 || grid[i][j - 1] == '0')
                            && (col == 1 || (j < col - 1 && grid[i][j + 1] == '0'))) {
                        num++;
                    }
                }
            }
        }

        return num;
    }
}
