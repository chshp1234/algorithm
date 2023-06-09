package com.study.algorithm.algorithms.algorithmsKT

//给你一个长度为 n 的整数数组 coins ，它代表你拥有的 n 个硬币。第 i 个硬币的值为 coins[i] 。如果你从这些硬币中选出一部分硬币，它们的和为 x ，那么称，你可以 构造 出 x 。
//
//请返回从 0 开始（包括 0 ），你最多能 构造 出多少个连续整数。
//
//你可能有多个相同值的硬币。
//
// 
//
//示例 1：
//
//输入：coins = [1,3]
//输出：2
//解释：你可以得到以下这些值：
//- 0：什么都不取 []
//- 1：取 [1]
//从 0 开始，你可以构造出 2 个连续整数。
//示例 2：
//
//输入：coins = [1,1,1,4]
//输出：8
//解释：你可以得到以下这些值：
//- 0：什么都不取 []
//- 1：取 [1]
//- 2：取 [1,1]
//- 3：取 [1,1,1]
//- 4：取 [4]
//- 5：取 [4,1]
//- 6：取 [4,1,1]
//- 7：取 [4,1,1,1]
//从 0 开始，你可以构造出 8 个连续整数。
//示例 3：
//
//输入：nums = [1,4,10,3,1]
//输出：20
// 
//
//提示：
//
//coins.length == n
//1 <= n <= 4 * 104
//1 <= coins[i] <= 4 * 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-number-of-consecutive-values-you-can-make
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 你能构造出连续值的最大数目 {
    fun getMaximumConsecutive(coins: IntArray): Int {
        //贪心，排序
        //也有点动态规划的思路。
        //首先，既然构造的数值从小到大，那么先对数组进行排序。
        //排序后，遍历数组，其当前的数是否能构造更大的和，是根据上一个数的结果来判断。
        //上一个数遍历结束后，最大能构造的数若为num，当且仅当，当前元素coins[i]<=num，才能继续进行构造（因为构造的数需要连续），切构造后的最大的数为num+coins[i]
        //比如当前数为7，在上一个数中，可凑到8（1..8），那么说明此时可以凑出7+8，1..15个不同数目。当然如果当前数为10，那么连续将中断。
        var res = 1
        coins.sort()
        for (c in coins) {
            if (c <= res) {
                res += c
            } else {
                break
            }
        }
        return res
    }
}