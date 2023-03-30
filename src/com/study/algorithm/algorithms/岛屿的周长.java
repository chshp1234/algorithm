package com.study.algorithm.algorithms;

import org.junit.Test;
// 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
//
// 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
//
// 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
//
//
//
// 示例 :
//
// 输入:
// [[0,1,0,0],
// [1,1,1,0],
// [0,1,0,0],
// [1,1,0,0]]
//
// 输出: 16
//
// 解释: 它的周长是下面图片中的 16 个黄色的边：
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/island-perimeter
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 岛屿的周长 {
    @Test
    public void 岛屿的周长() {
        // 求 1+2+...+n
        //        int[][] chars =
        //                new int[][] {
        //                    {1, 1, 1, 1, 0},
        //                    {1, 1, 0, 1, 0},
        //                    {1, 1, 0, 0, 0},
        //                    {0, 0, 0, 0, 0}
        //                };

        int[][] chars =
                new int[][] {
                    {1, 1},
                    {1, 1}
                };

        System.out.println("岛屿的周长:" + islandPerimeter(chars));
    }

    int count;
    int border;

    public int islandPerimeter(int[][] grid) {

        // 深度优先搜索
        // 思路一：
        // 遍历得到岛屿格子的数量count，两两相邻的岛屿的边数border
        // 结果就为：4*count-border，因为相邻的岛屿重合后，周长需减去其各自重合的边

        // 思路二：
        // 对于一个陆地格子的每条边，它被算作岛屿的周长当且仅当这条边为网格的边界或者相邻的另一个格子为水域。
        // 因此，我们可以遍历每个陆地格子，看其四个方向是否为边界或者水域，如果是，将这条边的贡献（即 1）加入答案 ans 中即可。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/island-perimeter/solution/dao-yu-de-zhou-chang-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        int w = grid[0].length;
        int h = grid.length;

        boolean[][] visit = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[i][j] == 1) {
                    island(grid, j, i, w, h, visit);
                    return 4 * count - border;
                }
            }
        }

        return 0;
    }

    public void island(int[][] grid, int x, int y, int w, int h, boolean[][] visit) {

        if (x == -1 || x == w || y == -1 || y == h || grid[y][x] == 0 || visit[y][x]) {
            return;
        }

        if (grid[y][x] == 1) {
            count++;
            visit[y][x] = true;
        }

        island(grid, x - 1, y, w, h, visit);
        island(grid, x, y - 1, w, h, visit);
        island(grid, x + 1, y, w, h, visit);
        island(grid, x, y + 1, w, h, visit);

        countBorder(x - 1, y, w, h, visit);
        countBorder(x, y - 1, w, h, visit);
        countBorder(x + 1, y, w, h, visit);
        countBorder(x, y + 1, w, h, visit);
    }

    public void countBorder(int x, int y, int w, int h, boolean[][] visit) {
        if (x == -1 || x == w || y == -1 || y == h) {
            return;
        }

        if (visit[y][x]) {
            border++;
        }
    }
}
