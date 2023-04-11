package com.study.algorithm.algorithms.algorithmsJAVA;

//你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。
//
//为了使收益最大化，矿工需要按以下规则来开采黄金：
//
//每当矿工进入一个单元，就会收集该单元格中的所有黄金。
//矿工每次可以从当前位置向上下左右四个方向走。
//每个单元格只能被开采（进入）一次。
//不得开采（进入）黄金数目为 0 的单元格。
//矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
// 
//
//示例 1：
//
//输入：grid = [[0,6,0],[5,8,7],[0,9,0]]
//输出：24
//解释：
//[[0,6,0],
// [5,8,7],
// [0,9,0]]
//一种收集最多黄金的路线是：9 -> 8 -> 7。
//示例 2：
//
//输入：grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
//输出：28
//解释：
//[[1,0,7],
// [2,0,6],
// [3,4,5],
// [0,3,0],
// [9,0,20]]
//一种收集最多黄金的路线是：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7。
// 
//
//提示：
//
//1 <= grid.length, grid[i].length <= 15
//0 <= grid[i][j] <= 100
//最多 25 个单元格中有黄金。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/path-with-maximum-gold
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 黄金矿工 {
    int max = 0;

    public int getMaximumGold(int[][] grid) {
        //深度优先搜索+回溯
        //以每个点为起点，深度优先搜索每条可行路径，找获得的黄金数最大的路径
        int row = grid.length;
        int col = grid[0].length;


        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] != 0) {
                    max = Math.max(backTrack(grid, r, c), max);
                }
            }
        }
        return max;
    }

    private int backTrack(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r == grid.length || c == grid[0].length || grid[r][c] == 0 || grid[r][c] == -1) {
            return 0;
        }
        int temp = grid[r][c];
        //将当前节点置为-1，表示已遍历过
        grid[r][c] = -1;
        //遍历4个方向，取出最大值
        int maxPath = Math.max(Math.max(backTrack(grid, r - 1, c), backTrack(grid, r + 1, c)),
                Math.max(backTrack(grid, r, c - 1), backTrack(grid, r, c + 1)));
        //恢复当前节点值
        grid[r][c] = temp;
        return temp + maxPath;
    }
}
