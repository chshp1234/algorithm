package com.study.algorithm.algorithms.algorithmsKT

import java.util.*

//在一根无限长的数轴上，你站在0的位置。终点在target的位置。
//
//你可以做一些数量的移动 numMoves :
//
//每次你可以选择向左或向右移动。
//第 i 次移动（从  i == 1 开始，到 i == numMoves ），在选择的方向上走 i 步。
//给定整数 target ，返回 到达目标所需的 最小 移动次数(即最小 numMoves ) 。
//
// 
//
//示例 1:
//
//输入: target = 2
//输出: 3
//解释:
//第一次移动，从 0 到 1 。
//第二次移动，从 1 到 -1 。
//第三次移动，从 -1 到 2 。
//示例 2:
//
//输入: target = 3
//输出: 2
//解释:
//第一次移动，从 0 到 1 。
//第二次移动，从 1 到 3 。
// 
//
//提示:
//
//-109 <= target <= 109
//target != 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/reach-a-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 到达终点数字 {
    fun reachNumber(target: Int): Int {
        //模拟(超出内存限制)
        //从第一步开始,每一步将其向左和向右走到的数字加入队列中
        //如果向左或向右可以走到target,则返回当前步数step
        val queue = ArrayDeque<Int>()
        queue.offer(0)
        var step = 0
        while (true) {
            step++
            val size = queue.size
            for (n in 0 until size) {
                val num = queue.poll()!!
                if (num + step == target || num - step == target) {
                    return step
                }
                queue.offer(num + step)
                queue.offer(num - step)
            }
        }
    }

    fun reachNumber2(target: Int): Int {
        //数学
        //对于数字1,2,3,4,5...n,把每一项都相加得到数字sum,即每一步都向右移动,那么最大可到达数字sum
        //1.如果sum<|target|,说明步数n不够,还需要继续行走
        //2.如果sum>|target|,说明步数"可能为"sum
        //所以需要找到最小的n,使得sum>|target|,那么这个n就是所需行走的"可能的"最小步数
        //为啥是"可能",因为当sum>|target|时,说明其中有几步需要向左走,即其中有些数字前应该是负号
        //那么当其中一个数字i前面带负号时,这时所有数字的和为多少,不是sum-i,而是sum-2i,因为这里不仅少加了个i,还多减了个i
        //再另其他数字前也带上负号是一样的,如果所有带上负号的数字的和为sumNegative,那么这时所有数字的和为sum-2sumNegative,其实是一样的
        //到这里,就只需要判断是否存在sumNegative,使得sum-2sumNegative等于target
        //令delta=sum-target,那么也就是找到是否存在sumNegative=delta/2,这里首先可以看出delta必须为偶数
        //其实如果delta为偶数的话,必然存在这样的数:
        //1.n>=delta/2,那么直接在数字中选一个数就行
        //2.n<delta/2,那么先选数字n,使得剩下的数为delta/2-n,同理,如果n-1>= delta/2-n,那么在n-1中选取一个数即可
        //3.重复上述步骤即可找到所有数使得sumNegative=delta/2
        //所以delta为偶数的话,必然存在这样的数

        //当前步数
        var step = 1
        //当前步数数字之和
        var sum = 1
        val t = Math.abs(target)
        //直到sum>=|target|
        while (sum < t) {
            step++
            sum += step
        }

        //如果sum==t(可与下面步骤合并)
        if (sum == t) {
            return step
        }
        while (true) {
            val delta = sum - t
            if (delta % 2 == 0) {
                //如果差值为偶数,则可直接返回
                return step
            }
            step++
            sum += step
        }
    }
}