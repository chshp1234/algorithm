package com.study.algorithm.algorithms.algorithmsKT

//给你一个 m x n 大小的矩阵 grid ，由若干正整数组成。
//
//执行下述操作，直到 grid 变为空矩阵：
//
//从每一行删除值最大的元素。如果存在多个这样的值，删除其中任何一个。
//将删除元素中的最大值与答案相加。
//注意 每执行一次操作，矩阵中列的数据就会减 1 。
//
//返回执行上述操作后的答案。
//
// 
//
//示例 1：
//
//
//
//输入：grid = [[1,2,4],[3,3,1]]
//输出：8
//解释：上图展示在每一步中需要移除的值。
//- 在第一步操作中，从第一行删除 4 ，从第二行删除 3（注意，有两个单元格中的值为 3 ，我们可以删除任一）。在答案上加 4 。
//- 在第二步操作中，从第一行删除 2 ，从第二行删除 3 。在答案上加 3 。
//- 在第三步操作中，从第一行删除 1 ，从第二行删除 1 。在答案上加 1 。
//最终，答案 = 4 + 3 + 1 = 8 。
//示例 2：
//
//
//
//输入：grid = [[10]]
//输出：10
//解释：上图展示在每一步中需要移除的值。
//- 在第一步操作中，从第一行删除 10 。在答案上加 10 。
//最终，答案 = 10 。
// 
//
//提示：
//
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 50
//1 <= grid[i][j] <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/delete-greatest-value-in-each-row
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 删除每行中的最大值 {
    fun deleteGreatestValue(grid: Array<IntArray>): Int {
        //排序
        //每次选出每一行中的最大值,然后再从中选出最大值加入结果中
        //那么可以先对每一行进行排序
        //然后按列进行遍历,从中选出最大值加入结果中
        for (arr in grid) {
            arr.sort()
        }
        var res = 0

        for (c in grid[0].indices) {
            var max = 0
            for (r in grid.indices) {
                max = Math.max(max, grid[r][c])
            }
            res += max
        }

        return res
    }
}