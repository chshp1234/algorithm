package com.study.algorithm.algorithms.algorithmsKT

import java.util.PriorityQueue

//Alice 和 Bob 轮流玩一个游戏，Alice 先手。
//
//一堆石子里总共有 n 个石子，轮到某个玩家时，他可以 移出 一个石子并得到这个石子的价值。Alice 和 Bob 对石子价值有 不一样的的评判标准 。双方都知道对方的评判标准。
//
//给你两个长度为 n 的整数数组 aliceValues 和 bobValues 。aliceValues[i] 和 bobValues[i] 分别表示 Alice 和 Bob 认为第 i 个石子的价值。
//
//所有石子都被取完后，得分较高的人为胜者。如果两个玩家得分相同，那么为平局。两位玩家都会采用 最优策略 进行游戏。
//
//请你推断游戏的结果，用如下的方式表示：
//
//如果 Alice 赢，返回 1 。
//如果 Bob 赢，返回 -1 。
//如果游戏平局，返回 0 。
//
//
//示例 1：
//
//输入：aliceValues = [1,3], bobValues = [2,1]
//输出：1
//解释：
//如果 Alice 拿石子 1 （下标从 0开始），那么 Alice 可以得到 3 分。
//Bob 只能选择石子 0 ，得到 2 分。
//Alice 获胜。
//示例 2：
//
//输入：aliceValues = [1,2], bobValues = [3,1]
//输出：0
//解释：
//Alice 拿石子 0 ， Bob 拿石子 1 ，他们得分都为 1 分。
//打平。
//示例 3：
//
//输入：aliceValues = [2,4,3], bobValues = [1,6,7]
//输出：-1
//解释：
//不管 Alice 怎么操作，Bob 都可以得到比 Alice 更高的得分。
//比方说，Alice 拿石子 1 ，Bob 拿石子 2 ， Alice 拿石子 0 ，Alice 会得到 6 分而 Bob 得分为 7 分。
//Bob 会获胜。
//
//
//提示：
//
//n == aliceValues.length == bobValues.length
//1 <= n <= 105
//1 <= aliceValues[i], bobValues[i] <= 100
class `石子游戏 VI` {

    //排序，优先队列，贪心
    //因为，对于选中的下标i处的石子，相当于，选中的那个人得到了stone[i]的分数，而对方失去了stone[i]的分数
    //由于双方对于石头的价值总值时不变的，那么每次可以贪心得选择aliceValues[i]+bobValues[i]的最大值的下标。
    //这样就相当于对方减去了stone[i]的分数，而自己加上了stone[i]的分数，方可使得每次选择后的利益最大化。
    //实例化一个大顶堆queue，按照aliceValues[i] + bobValues[i]的大小进行排序。初始化alice的得分。
    //当queue.size > 1，说石头的剩余堆数大于等于2。
    //令alice += aliceValues[queue.poll()]；alice -= bobValues[queue.poll()]。代表alice的得分，以及bob的得分
    //最后如果queue==1，那么alice还可以再选择一次。
    //返回return if (alice > 0) 1 else if (alice < 0) -1 else 0。
    fun stoneGameVI(aliceValues: IntArray, bobValues: IntArray): Int {
        var alice = 0

        val queue = PriorityQueue(object : Comparator<Int> {
            override fun compare(i1: Int, i2: Int): Int {
                return (aliceValues[i2] + bobValues[i2]) - (aliceValues[i1] + bobValues[i1])
            }
        }).apply { repeat(aliceValues.size) { offer(it) } }

        while (queue.size > 1) {
            alice += aliceValues[queue.poll()]
            alice -= bobValues[queue.poll()]
        }

        if (queue.size == 1) {
            alice += aliceValues[queue.poll()]
        }

        return if (alice > 0) 1 else if (alice < 0) -1 else 0
    }
}