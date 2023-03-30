package com.study.algorithm.algorithmsKT

import java.util.*

//给你一个下标从 0 开始的整数数组 nums ，其长度是 2 的幂。
//
//对 nums 执行下述算法：
//
//设 n 等于 nums 的长度，如果 n == 1 ，终止 算法过程。否则，创建 一个新的整数数组 newNums ，新数组长度为 n / 2 ，下标从 0 开始。
//对于满足 0 <= i < n / 2 的每个 偶数 下标 i ，将 newNums[i] 赋值 为 min(nums[2 * i], nums[2 * i + 1]) 。
//对于满足 0 <= i < n / 2 的每个 奇数 下标 i ，将 newNums[i] 赋值 为 max(nums[2 * i], nums[2 * i + 1]) 。
//用 newNums 替换 nums 。
//从步骤 1 开始 重复 整个过程。
//执行算法后，返回 nums 中剩下的那个数字。
//
// 
//
//示例 1：
//
//
//
//输入：nums = [1,3,5,2,4,8,2,2]
//输出：1
//解释：重复执行算法会得到下述数组。
//第一轮：nums = [1,5,4,2]
//第二轮：nums = [1,4]
//第三轮：nums = [1]
//1 是最后剩下的那个数字，返回 1 。
//示例 2：
//
//输入：nums = [3]
//输出：3
//解释：3 就是最后剩下的数字，返回 3 。
// 
//
//提示：
//
//1 <= nums.length <= 1024
//1 <= nums[i] <= 109
//nums.length 是 2 的幂
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/min-max-game
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 极大极小游戏 {
    fun minMaxGame(nums: IntArray): Int {
        //模拟，队列
        //用一个队列来模拟每次修改后的新数组
        //每次将新的数加入队列中
        //当队列长度等于1时，模拟结束，返回队列中剩余的这个元素的值
        val queue = LinkedList<Int>()
        for (n in nums) {
            queue.offer(n)
        }

        while (queue.size > 1) {
            val size = queue.size
            var sign = true
            for (i in 0 until size step 2) {
                if (sign) {
                    //偶数下标
                    queue.offer(Math.min(queue.poll(), queue.poll()))
                } else {
                    //奇数下标
                    queue.offer(Math.max(queue.poll(), queue.poll()))
                }
                sign = !sign
            }
        }
        return queue.poll()
    }
}