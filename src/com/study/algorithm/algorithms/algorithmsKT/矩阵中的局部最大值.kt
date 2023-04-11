package com.study.algorithm.algorithms.algorithmsKT

//给你一个大小为 n x n 的整数矩阵 grid 。
//
//生成一个大小为 (n - 2) x (n - 2) 的整数矩阵  maxLocal ，并满足：
//
//maxLocal[i][j] 等于 grid 中以 i + 1 行和 j + 1 列为中心的 3 x 3 矩阵中的 最大值 。
//换句话说，我们希望找出 grid 中每个 3 x 3 矩阵中的最大值。
//
//返回生成的矩阵。
//
// 
//
//示例 1：
//
//
//
//输入：grid = [[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]]
//输出：[[9,9],[8,6]]
//解释：原矩阵和生成的矩阵如上图所示。
//注意，生成的矩阵中，每个值都对应 grid 中一个相接的 3 x 3 矩阵的最大值。
//示例 2：
//
//
//
//输入：grid = [[1,1,1,1,1],[1,1,1,1,1],[1,1,2,1,1],[1,1,1,1,1],[1,1,1,1,1]]
//输出：[[2,2,2],[2,2,2],[2,2,2]]
//解释：注意，2 包含在 grid 中每个 3 x 3 的矩阵中。
// 
//
//提示：
//
//n == grid.length == grid[i].length
//3 <= n <= 100
//1 <= grid[i][j] <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/largest-local-values-in-a-matrix
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 矩阵中的局部最大值 {
    fun largestLocal(grid: Array<IntArray>): Array<IntArray> {
        //一趟遍历
        //按题意,一趟遍历,得出每3*3方块中的最大值,设置到对应的结果矩阵中

        val res = Array(grid.size - 2) {
            IntArray(grid.size - 2)
        }

        for (r in 2 until grid.size) {
            for (c in 2 until grid.size) {
                res[r - 2][c - 2] = grid[r - 2][c - 2].coerceAtLeast(grid[r - 2][c - 1]).coerceAtLeast(grid[r - 2][c])
                    .coerceAtLeast(grid[r - 1][c - 2]).coerceAtLeast(grid[r - 1][c - 1]).coerceAtLeast(grid[r - 1][c])
                    .coerceAtLeast(grid[r][c - 2]).coerceAtLeast(grid[r][c - 1]).coerceAtLeast(grid[r][c])
            }
        }

        return res
    }
}