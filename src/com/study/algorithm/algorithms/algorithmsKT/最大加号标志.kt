package com.study.algorithm.algorithms.algorithmsKT

//在一个 n x n 的矩阵 grid 中，除了在数组 mines 中给出的元素为 0，其他每个元素都为 1。mines[i] = [xi, yi]表示 grid[xi][yi] == 0
//
//返回  grid 中包含 1 的最大的 轴对齐 加号标志的阶数 。如果未找到加号标志，则返回 0 。
//
//一个 k 阶由 1 组成的 “轴对称”加号标志 具有中心网格 grid[r][c] == 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。注意，只有加号标志的所有网格要求为 1 ，别的网格可能为 0 也可能为 1 。
//
// 
//
//示例 1：
//
//
//
//输入: n = 5, mines = [[4, 2]]
//输出: 2
//解释: 在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
//示例 2：
//
//
//
//输入: n = 1, mines = [[0, 0]]
//输出: 0
//解释: 没有加号标志，返回 0 。
// 
//
//提示：
//
//1 <= n <= 500
//1 <= mines.length <= 5000
//0 <= xi, yi < n
//每一对 (xi, yi) 都 不重复
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/largest-plus-sign
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 最大加号标志 {
    fun orderOfLargestPlusSign(n: Int, mines: Array<IntArray>): Int {
        //模拟,动态规划
        //先将mines数组中的坐标展平加入哈希表中,方便后续判断
        //一个十字的最长臂长,为其上下左右4个方向的最短臂长,并且十字中心点处的值必须为1
        //设dp[i][0]为:在坐标(i/n,i%n)处,其横向(左右)的最短臂长
        //设dp[i][1]为:在坐标(i/n,i%n)处,其纵向(上下)的最短臂长
        //只要某一坐标处上下左右中有一点为0,那么此处的臂长就为0,否则为其上下左右4个方向中最短臂长+1
        //先从左上角到右下角遍历矩阵,得出某一点处左边和上边的最短臂长
        //再从右下角到左上角遍历矩阵,得出某一点处右边和下边的最短臂长
        //这样就可以比较一个点的上下左右4个方向的最短臂长了
        //遍历时,只遍历矩阵中间部分,即行列下标1..n-2,因为边界处的臂长只会为0
        //结果中,只有本身为1,才能计算臂长,并且需要最短臂长加上本身加上1
        //如果结果为0,则需继续计算4个边界处(边界处的结果最多为1),如果有值为1,则返回1
        val dp = Array(n * n) {
            IntArray(2)
        }

        val map = mutableSetOf<Int>()
        for (i in mines) {
            map.add(i[0] * n + i[1])
        }

        var largest = 0

        for (r in 1 until n - 1) {
            for (c in 1 until n - 1) {
                val index = r * n + c
                dp[index][0] = if (map.contains(index - 1)) 0 else dp[index - 1][0] + 1
                dp[index][1] = if (map.contains(index - n)) 0 else dp[index - n][1] + 1
            }
        }

        for (r in n - 2 downTo 1) {
            for (c in n - 2 downTo 1) {
                val index = r * n + c
                val sumR = if (map.contains(index + 1)) 0 else dp[index + 1][0] + 1
                val sumB = if (map.contains(index + n)) 0 else dp[index + n][1] + 1

                if (!map.contains(index)) {
                    largest =
                        (dp[index][0].coerceAtMost(dp[index][1]).coerceAtMost(sumR).coerceAtMost(sumB) + 1)
                            .coerceAtLeast(largest)
                }

                dp[index][0] = sumR
                dp[index][1] = sumB
            }
        }

        if (largest == 0) {
            for (c in 0 until n) {
                if (!map.contains(c)) {
                    return 1
                }
                if (!map.contains(n * (n - 1) + c)) {
                    return 1
                }
            }
            for (r in 0 until n) {
                if (!map.contains(r * n)) {
                    return 1
                }
                if (!map.contains(r * n + n - 1)) {
                    return 1
                }
            }
        }

        return largest
    }
}