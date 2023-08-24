package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//给你一个数组 seats 表示一排座位，其中 seats[i] = 1 代表有人坐在第 i 个座位上，seats[i] = 0 代表座位 i 上是空的（下标从 0 开始）。
//
//至少有一个空座位，且至少有一人已经坐在座位上。
//
//亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。
//
//返回他到离他最近的人的最大距离。
//
//
//
//示例 1：
//
//
//输入：seats = [1,0,0,0,1,0,1]
//输出：2
//解释：
//如果亚历克斯坐在第二个空位（seats[2]）上，他到离他最近的人的距离为 2 。
//如果亚历克斯坐在其它任何一个空位上，他到离他最近的人的距离为 1 。
//因此，他到离他最近的人的最大距离是 2 。
//示例 2：
//
//输入：seats = [1,0,0,0]
//输出：3
//解释：
//如果亚历克斯坐在最后一个座位上，他离最近的人有 3 个座位远。
//这是可能的最大距离，所以答案是 3 。
//示例 3：
//
//输入：seats = [0,1]
//输出：1
//
//
//提示：
//
//2 <= seats.length <= 2 * 104
//seats[i] 为 0 或 1
//至少有一个 空座位
//至少有一个 座位上有人
class 到最近的人的最大距离 {

    //模拟
    //要找大最大距离，就只需要找到两个有人的座位的最大间距max就行
    //如果两个有人的座位的间距是distance，那么坐在这两个人中间时，最大的距离就是distance/2
    //最后在判断坐在头尾时（如果此时座位没人），距离下一个人是多少距离head和tail，因为是坐在头尾，所以旁边只有一个人，那么距离就不用除以2
    //最后返回max(max/2,head,tail)即可
    @Test
    fun 到最近的人的最大距离() {
        println("到最近的人的最大距离:${maxDistToClosest(intArrayOf(1, 0, 0, 0, 1, 0, 1))}")
    }

    fun maxDistToClosest(seats: IntArray): Int {
        var last = 0
        var max = 0
        while (last < seats.size) {
            if (seats[last] == 1) {
                max = last * 2
                break
            }
            last++
        }
        for (i in last + 1 until seats.size) {
            if (seats[i] == 1) {
                max = Math.max(max, i - last)
                last = i
            }
        }

        return Math.max(max / 2, seats.size - last - 1)
    }
}