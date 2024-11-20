package com.study.algorithm.algorithms.algorithmsKT

import java.util.*
import kotlin.Comparator

//车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
//
//给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。
//
//当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
//
//
//
//示例 1：
//
//输入：trips = [[2,1,5],[3,3,7]], capacity = 4
//输出：false
//示例 2：
//
//输入：trips = [[2,1,5],[3,3,7]], capacity = 5
//输出：true
//
//
//提示：
//
//1 <= trips.length <= 1000
//trips[i].length == 3
//1 <= numPassengersi <= 100
//0 <= fromi < toi <= 1000
//1 <= capacity <= 105
class 拼车 {
    fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
        //排序（优先队列）
        //按每个元素的起止位置进行排序，如果在同一点中既有上车又有下车（起止位置相同），那么下车的位置靠前（优先使乘客下车）
        //将trips的每一项i中位置加入优先队列中，开始位置为i*2，结束位置为i*2+1
        //设left初始为capacity，表示车上剩余的空座位。
        //依次取出优先队列中的每个元素trip，其值为trips[(trip / 2)][0]，代表该次旅行的乘客数量。
        //并且，如果trip为偶数，说明这次应该计算上车的乘客，令left-trips[(trip / 2)][0];如果是奇数，应该计算下车的乘客数量,令left+trips[(trip / 2)][0]
        //计算后如果left<0，说明此次旅行将没有空座位，那么返回false；遍历结束，说明可以接送所有乘客，返回true。
        var left = capacity
        val queue = PriorityQueue(object : Comparator<Int> {
            override fun compare(o1: Int, o2: Int): Int {
                //获取起/止位置
                val trip1 = trips[(o1 / 2)][(o1 % 2) + 1]
                val trip2 = trips[(o2 / 2)][(o2 % 2) + 1]
                return if (trip1 == trip2) {
                    -(o1 % 2 - o2 % 2)
                } else {
                    trip1 - trip2
                }
            }
        })
        for (i in trips.indices) {
            //将起止位置加入队列中
            queue.offer((i * 2))
            queue.offer((i * 2) + 1)
        }
        while (!queue.isEmpty()) {
            val trip = queue.poll()!!
            //如果trip为偶数，说明这次应该计算上车的乘客，令left-trips[(trip / 2)][0];如果是奇数，应该计算下车的乘客数量,令left+trips[(trip / 2)][0]
            left += trips[(trip / 2)][0] * -Math.pow(-1.0, (trip % 2).toDouble()).toInt()
            if (left < 0) {
                return false
            }
        }
        return true
    }
}