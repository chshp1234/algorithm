package com.study.algorithm.algorithmsKT

import org.junit.Test

//给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
//
//子数组大小 至少为 2 ，且
//子数组元素总和为 k 的倍数。
//如果存在，返回 true ；否则，返回 false 。
//
//如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
//
// 
//
//示例 1：
//
//输入：nums = [23,2,4,6,7], k = 6
//输出：true
//解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
//示例 2：
//
//输入：nums = [23,2,6,4,7], k = 6
//输出：true
//解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。
//42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
//示例 3：
//
//输入：nums = [23,2,6,4,7], k = 13
//输出：false
// 
//
//提示：
//
//1 <= nums.length <= 105
//0 <= nums[i] <= 109
//0 <= sum(nums[i]) <= 231 - 1
//1 <= k <= 231 - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/continuous-subarray-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 连续的子数组和 {
    @Test
    fun 连续的子数组和() {
        System.out.printf("连续的子数组和:${checkSubarraySum(intArrayOf(1, 0), 2)}")
    }

    fun checkSubarraySumByDp(nums: IntArray, k: Int): Boolean {
        //动态规划
        //dp[i],k，表示以下标i为结尾，是否存在和为k的子数组
        //每次遍历，判断dp[k-(nums[i])%k]是否为true
        //若不存在，则更新以i为下标的后缀和
        //时间复杂度：O(n^2),超时
        val len = nums.size
        if (len == 1) {
            return false
        }

        if ((nums[0] + nums[1]) % k == 0) {
            return true
        }

        var set = HashSet<Int>()
        set.add((nums[0] + nums[1]) % k)
        set.add(nums[1] % k)

        for (i in 2 until len) {
            val current = nums[i] % k
            if (set.contains((k - current) % k)) {
                return true
            }

            val tempSet = set
            set = HashSet()

            for (num in tempSet) {
                set.add((num + current) % k)
            }
            set.add(current)
        }

        return false
    }

    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
        //同余定理：若(x-y)%z==0,则x%z=y%z
        //据此，我们可以先计算数组前缀和，那么子数组(i..j)的区间和为(preSum[i]-preSum[j])，
        //则若子数组区间和是k的倍数，(preSum[i]-preSum[j])%k==0，同理可得preSum[i]%k=preSum[j]%k
        //那么我们只要找到，在preSum[i]%k=preSum[j]%k的情况下，下标i和j的长度大于等于2，即可
        //使用哈希表记录前缀和presum%k以及其出现的下标，即可判断是否存在相同的(取余后)前缀和，以及区间长度

        val len = nums.size
        if (len == 1) {
            return false
        }

        //当然，我们不用知道下标，只需要知道，是否存在相同的前缀和级即可
        //由于子数组长度最少位2，所以两个前缀和的间隔不能相邻
        var preSumFirst = nums[0] % k
        var preSumSecond = nums[0] % k

        val map = HashMap<Int, Boolean>()
        map[0] = true

        for (i in 1 until len) {
            val temp = preSumSecond
            preSumSecond = (preSumSecond + nums[i]) % k
            if (map[preSumSecond] == true) {
                //若存在
                return true
            }
            //若不存在，将前一个前缀和加入
            map[preSumFirst] = true
            preSumFirst = temp
        }
        return false
    }
}