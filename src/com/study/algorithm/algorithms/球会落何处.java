package com.study.algorithm.algorithms;

//用一个大小为 m x n 的二维网格 grid 表示一个箱子。你有 n 颗球。箱子的顶部和底部都是开着的。
//
//箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，可以将球导向左侧或者右侧。
//
//将球导向右侧的挡板跨过左上角和右下角，在网格中用 1 表示。
//将球导向左侧的挡板跨过右上角和左下角，在网格中用 -1 表示。
//在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。如果球恰好卡在两块挡板之间的 "V" 形图案，或者被一块挡导向到箱子的任意一侧边上，就会卡住。
//
//返回一个大小为 n 的数组 answer ，其中 answer[i] 是球放在顶部的第 i 列后从底部掉出来的那一列对应的下标，如果球卡在盒子里，则返回 -1 。
//
// 
//
//示例 1：
//
//
//
//输入：grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
//输出：[1,-1,-1,-1,-1]
//解释：示例如图：
//b0 球开始放在第 0 列上，最终从箱子底部第 1 列掉出。
//b1 球开始放在第 1 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
//b2 球开始放在第 2 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
//b3 球开始放在第 3 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
//b4 球开始放在第 4 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
//示例 2：
//
//输入：grid = [[-1]]
//输出：[-1]
//解释：球被卡在箱子左侧边上。
//示例 3：
//
//输入：grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
//输出：[0,1,2,3,4,-1]
// 
//
//提示：
//
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 100
//grid[i][j] 为 1 或 -1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/where-will-the-ball-fall
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 球会落何处 {
    public int[] findBall(int[][] grid) {
        //模拟
        //直接模拟小球下落过程即可
        //根据小球下落的方向，是从左到右滚动，从右到左滚动还是从上到下滚动。不同滚动方向遇到不同挡板会有不同的动作。
        int row = grid.length;
        int col = grid[0].length;
        int[] result = new int[col];

        for (int i = 0; i < col; i++) {
            result[i] = fall(0, 0, i, grid);
        }
        return result;
    }

    public int fall(int direction, int r, int c, int[][] grid) {
        if (c < 0 || c == grid[0].length) {
            return -1;
        }
        if (r == grid.length) {
            return c;
        }

        if (direction == -1) {
            if (grid[r][c] == -1) {
                return -1;
            }
            return fall(0, r + 1, c, grid);
        }

        if (direction == 0) {
            if (grid[r][c] == -1) {
                return fall(1, r, c - 1, grid);
            }
            return fall(-1, r, c + 1, grid);
        }

        if (direction == 1) {
            if (grid[r][c] == 1) {
                return -1;
            }
            return fall(0, r + 1, c, grid);
        }
        return -1;
    }
}
