package com.study.algorithm.algorithmsKT

import java.util.*


//现有一台饮水机，可以制备冷水、温水和热水。每秒钟，可以装满 2 杯 不同 类型的水或者 1 杯任意类型的水。
//
//给你一个下标从 0 开始、长度为 3 的整数数组 amount ，其中 amount[0]、amount[1] 和 amount[2] 分别表示需要装满冷水、温水和热水的杯子数量。返回装满所有杯子所需的 最少 秒数。
//
// 
//
//示例 1：
//
//输入：amount = [1,4,2]
//输出：4
//解释：下面给出一种方案：
//第 1 秒：装满一杯冷水和一杯温水。
//第 2 秒：装满一杯温水和一杯热水。
//第 3 秒：装满一杯温水和一杯热水。
//第 4 秒：装满一杯温水。
//可以证明最少需要 4 秒才能装满所有杯子。
//示例 2：
//
//输入：amount = [5,4,4]
//输出：7
//解释：下面给出一种方案：
//第 1 秒：装满一杯冷水和一杯热水。
//第 2 秒：装满一杯冷水和一杯温水。
//第 3 秒：装满一杯冷水和一杯温水。
//第 4 秒：装满一杯温水和一杯热水。
//第 5 秒：装满一杯冷水和一杯热水。
//第 6 秒：装满一杯冷水和一杯温水。
//第 7 秒：装满一杯热水。
//示例 3：
//
//输入：amount = [5,0,0]
//输出：5
//解释：每秒装满一杯冷水。
// 
//
//提示：
//
//amount.length == 3
//0 <= amount[i] <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-amount-of-time-to-fill-cups
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 装满杯子需要的最短总时长 {
    fun fillCups(amount: IntArray): Int {
        //贪心
        //每次都装最大数量和次大数量的杯子
        //装满一杯时，判断并重新排序三种杯子
        //直到次大数量为0，那么此时只剩最大数量，由于只能装满1个杯子，所以剩下的数量直接加到结果中
        amount.sort()
        var f = amount[2]
        var s = amount[1]
        var t = amount[0]

        var res = 0
        while (s > 0) {
            res++
            f--
            s--
            if (f < t) {
                t = t xor f
                f = t xor f
                t = t xor f
            } else if (s < t) {
                t = t xor s
                s = t xor s
                t = t xor s
            }
        }
        res += f
        return res

    }

    fun fillCupsByLeetcode(amount: IntArray): Int {
        //假设不同类型杯子的数量分别为x, y 和 z,其中x<=y<=z
        //1.如果x+y<=z，那么结果为z
        //2.如果x+y>z，那么结果（x+y+z+1）/2。
        //如果对于每次都能加两杯的情况（至少两种杯子还没加满）中：
        //第一种情况，最后剩下的肯定是x,0,0（x>=2）
        //第二种情况，最后肯定是1，1，1（总和奇数）或者1，1，0（总和偶数）。所以既然每次都是减2，那么答案自然是(sum+1)/2
        Arrays.sort(amount)
        return if (amount[2] > amount[1] + amount[0]) {
            amount[2]
        } else (amount[0] + amount[1] + amount[2] + 1) / 2
    }

}