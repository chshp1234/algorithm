package com.study.algorithm.algorithms.algorithmsKT

//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
//岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
//
//此外，你可以假设该网格的四条边均被水包围。
//
// 
//
//示例 1：
//
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
//示例 2：
//
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
//提示：
//
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 300
//grid[i][j] 的值为 '0' 或 '1'
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/number-of-islands
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 岛屿数量 {

    var res = 0
    fun numIslands(grid: Array<CharArray>): Int {
        //深度优先搜索
        //遍历矩阵,遇到'1'时进行深度优先搜索,并用一个布尔值的矩阵记录改格子是否被遍历过
        //如果在深度优先搜索中,遇到'1',将改网格记录为已查找,下次遇到时直接跳过
        //每次遇到新的'1'的格子,并且没被记录过,则说明遇到一个新的"岛屿",将数量+1
        val visit = Array(grid.size) {
            BooleanArray(grid[0].size)
        }
        for (r in grid.indices) {
            for (c in grid[0].indices) {
                if (!visit[r][c] && grid[r][c] == '1') {
                    res++
                    dfs(r, c, grid, visit)
                }
            }
        }
        return res
    }

    fun dfs(row: Int, col: Int, grid: Array<CharArray>, visit: Array<BooleanArray>) {
        if (!check(row, col, grid)) {
            return
        }
        if (visit[row][col]) {
            return
        }
        if (grid[row][col] != '1') {
            return
        }
        visit[row][col] = true
        dfs(row - 1, col, grid, visit)
        dfs(row, col + 1, grid, visit)
        dfs(row + 1, col, grid, visit)
        dfs(row, col - 1, grid, visit)
    }

    fun check(row: Int, col: Int, grid: Array<CharArray>): Boolean {
        if (row < 0 || col < 0 || row == grid.size || col == grid[0].size) {
            return false
        }
        return true
    }
}