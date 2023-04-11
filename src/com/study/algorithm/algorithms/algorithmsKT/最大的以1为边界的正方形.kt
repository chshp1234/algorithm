package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
//
// 
//
//示例 1：
//
//输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
//输出：9
//示例 2：
//
//输入：grid = [[1,1,0,0]]
//输出：1
// 
//
//提示：
//
//1 <= grid.length <= 100
//1 <= grid[0].length <= 100
//grid[i][j] 为 0 或 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/largest-1-bordered-square
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class `最大的以1为边界的正方形` {
    @Test
    fun 最大的以1为边界的正方形() {
        println(
            "最大的以1为边界的正方形:${
                largest1BorderedSquare(
                    arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 0, 1), intArrayOf(1, 1, 1))

                )
            }"
        )
    }

    fun largest1BorderedSquare(grid: Array<IntArray>): Int {
        //动态规划
        //设:dpRows[x][y]表示以x,y为结束点,最长向上连续1的数量,dpRows[r][c] = if (grid[r][c] == 1) dpRows[r - 1][c] + 1 else 0
        //设:dpCols[x][y]表示以x,y为结束点,最长向左连续1的数量,dpCols[r][c] = if (grid[r][c] == 1) dpCols[r][c - 1] + 1 else 0
        //设:dpLast[y]表示距离下标y最近的'1'的位置,dpLast[c] = if (grid[r][c] == 1) c else dpLast[c - 1]
        //此时,在x,y坐标下,最大可达到的边长就为maxBorder=max(dpRows[r][c],dpCols[x][y])
        //我们令初始的纵坐标下标为index = c - res(小于res长度的边可不用判断了),那么边长就为border = c - dpLast[index]
        //如果变成大于最大可达边长,if (border >= maxBorder),说明向上或向左的最长'1'的数量的边小于border,那么不可能凑成正方形,直接退出循环
        //否则,再判断if (dpRows[r][c - border] > border && dpCols[r - border][c] > border),以border为边长是否满足正方形要求
        //如果满足,res=border+(因为起始时border就大于res),并继续判断下一个点index--
        //最后返回res*res,正方形的总数量
        val dpRows = Array(grid.size) {
            IntArray(grid[0].size)
        }
        val dpCols = Array(grid.size) {
            IntArray(grid[0].size)
        }
        var res = 0

        for (r in grid.indices) {
            val dpLast = IntArray(grid[0].size) {
                -1
            }
            for (c in grid[0].indices) {
                if (r == 0) {
                    dpRows[r][c] = if (grid[r][c] == 1) 1 else 0
                } else {
                    dpRows[r][c] = if (grid[r][c] == 1) dpRows[r - 1][c] + 1 else 0
                }
                if (c == 0) {
                    dpCols[r][c] = if (grid[r][c] == 1) 1 else 0
                } else {
                    dpCols[r][c] = if (grid[r][c] == 1) dpCols[r][c - 1] + 1 else 0
                }
                if (c == 0) {
                    dpLast[c] = if (grid[r][c] == 1) 0 else -1
                } else {
                    dpLast[c] = if (grid[r][c] == 1) c else dpLast[c - 1]
                }

                val maxBorder = dpRows[r][c].coerceAtMost(dpCols[r][c])
                var index = c - res
                while (index >= 0) {
                    val border = c - dpLast[index]
                    if (border >= maxBorder) {
                        break
                    }
                    if (dpRows[r][c - border] > border && dpCols[r - border][c] > border) {
                        res = border + 1
                    }
                    index--
                }
            }
        }

        return res * res
    }
}