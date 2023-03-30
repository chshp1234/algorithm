package com.study.algorithm.algorithmsKT

import org.junit.Test
import java.util.*

//你还记得那条风靡全球的贪吃蛇吗？
//
//我们在一个 n*n 的网格上构建了新的迷宫地图，蛇的长度为 2，也就是说它会占去两个单元格。蛇会从左上角（(0, 0) 和 (0, 1)）开始移动。我们用 0 表示空单元格，用 1 表示障碍物。蛇需要移动到迷宫的右下角（(n-1, n-2) 和 (n-1, n-1)）。
//
//每次移动，蛇可以这样走：
//
//如果没有障碍，则向右移动一个单元格。并仍然保持身体的水平／竖直状态。
//如果没有障碍，则向下移动一个单元格。并仍然保持身体的水平／竖直状态。
//如果它处于水平状态并且其下面的两个单元都是空的，就顺时针旋转 90 度。蛇从（(r, c)、(r, c+1)）移动到 （(r, c)、(r+1, c)）。
//
//如果它处于竖直状态并且其右面的两个单元都是空的，就逆时针旋转 90 度。蛇从（(r, c)、(r+1, c)）移动到（(r, c)、(r, c+1)）。
//
//返回蛇抵达目的地所需的最少移动次数。
//
//如果无法到达目的地，请返回 -1。
//
// 
//
//示例 1：
//
//
//
//输入：grid = [[0,0,0,0,0,1],
//               [1,1,0,0,1,0],
//               [0,0,0,0,1,1],
//               [0,0,1,0,1,0],
//               [0,1,1,0,0,0],
//               [0,1,1,0,0,0]]
//输出：11
//解释：
//一种可能的解决方案是 [右, 右, 顺时针旋转, 右, 下, 下, 下, 下, 逆时针旋转, 右, 下]。
//示例 2：
//
//输入：grid = [[0,0,1,1,1,1],
//               [0,0,0,0,1,1],
//               [1,1,0,0,0,1],
//               [1,1,1,0,0,1],
//               [1,1,1,0,0,1],
//               [1,1,1,0,0,0]]
//输出：9
// 
//
//提示：
//
//2 <= n <= 100
//0 <= grid[i][j] <= 1
//蛇保证从空单元格开始出发。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-moves-to-reach-target-with-rotations
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 穿过迷宫的最少移动次数 {
    @Test
    fun 穿过迷宫的最少移动次数() {
        println(
            "穿过迷宫的最少移动次数:${
                minimumMoves(
                    arrayOf(
                        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1),
                        intArrayOf(0, 1, 0, 0, 0, 0, 0, 1, 0, 1),
                        intArrayOf(1, 0, 0, 1, 0, 0, 1, 0, 1, 0),
                        intArrayOf(0, 0, 0, 1, 0, 1, 0, 1, 0, 0),
                        intArrayOf(0, 0, 0, 0, 1, 0, 0, 0, 0, 1),
                        intArrayOf(0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
                        intArrayOf(1, 0, 0, 1, 0, 0, 0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                        intArrayOf(1, 1, 0, 0, 0, 0, 0, 0, 0, 0)

                    )
                )
            }"
        )
    }

    fun minimumMoves(grid: Array<IntArray>): Int {
        //广度优先搜索
        //根据小蛇只能4种移动方式，进行广度优先搜索
        //当蛇是横向时，如果没到达右边界可以向右移动，如果没到达下边界向下移动，如果没到达下边界且下面两个位置没有障碍物则可以顺时针旋转
        //当蛇是竖向时，如果没到达右边界可以向右移动，如果没到达下边界向下移动，如果没到达右边界且右面两个位置没有障碍物则可以逆时针旋转
        //使用BFS方式进行遍历，每一层代表一次移动，并按上述规则将下次可移动的位置加入队列中
        //使用data class Point(val isHorizontal: Boolean, val x: Int, val y: Int)数据结构，表示小蛇是否横向移动，以及x，y坐标
        //并且遍历时需要记录已经移动过的位置（Point(),朝向以及x，y都对应）
        //如果某一层遍历中，小蛇是横向，并且x=grid.size-1，y=grid.size-1时，说明到达目的地，返回
        //否则返回-1
        val queue: Queue<Point> = LinkedList()
        val visit = HashSet<Point>()
        queue.offer(Point(true, 1, 0))
        var step = 0
        val boundary = grid.size - 1
        while (!queue.isEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val p = queue.poll()
                if (visit.contains(p)) {
                    continue
                }
                visit.add(p)
                if (p.x == boundary && p.y == boundary && p.isHorizontal) {
                    return step
                }
                when {
                    p.isHorizontal -> {
                        if (p.x != boundary && grid[p.y][p.x + 1] != 1) {
                            queue.offer(Point(true, p.x + 1, p.y))
                        }
                        if (p.y != boundary && grid[p.y + 1][p.x] != 1 && grid[p.y + 1][p.x - 1] != 1) {
                            queue.offer(Point(true, p.x, p.y + 1))
                            queue.offer(Point(false, p.x - 1, p.y + 1))
                        }
                    }

                    else -> {
                        if (p.x != boundary && grid[p.y][p.x + 1] != 1 && grid[p.y - 1][p.x + 1] != 1) {
                            queue.offer(Point(true, p.x + 1, p.y - 1))
                            if (p.x != boundary - 1) {
                                //小蛇垂直状态，到最右边时可以直接忽略，因为已经无法转换成水平状态
                                queue.offer(Point(false, p.x + 1, p.y))
                            }
                        }
                        if (p.y != boundary && grid[p.y + 1][p.x] != 1) {
                            queue.offer(Point(false, p.x, p.y + 1))
                        }
                    }
                }
            }
            step++
        }
        return -1
    }

    data class Point(val isHorizontal: Boolean, val x: Int, val y: Int)
}