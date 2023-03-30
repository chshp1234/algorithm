package com.study.algorithm.algorithmsKT

//给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
//
// 
//
//示例 1：
//
//输入：nums = [1,1,1], k = 2
//输出：2
//示例 2：
//
//输入：nums = [1,2,3], k = 3
//输出：2
// 
//
//提示：
//
//1 <= nums.length <= 2 * 104
//-1000 <= nums[i] <= 1000
//-107 <= k <= 107
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/subarray-sum-equals-k
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 和为K的子数组 {
    fun subarraySum(nums: IntArray, k: Int): Int {
        //前缀和,哈希表
        //题目要求子数组是连续的,那么可以用数组前缀和快速的求解某个连续子数组的和
        //用哈希表记录某个前缀和以及其对应的数组结尾的下标
        //最后判断时,只要判断当前的前缀和-k,差值是否在哈希表中,以及结尾的下标是否小于当前下标,是的话就可以得到和为k的从结尾下标到当前下标的连续子数

        //可以优化:从左到右,一趟遍历,统计当前前缀和以及其出现的次数,
        //因为需要注意的是，从左往右边更新边计算的时候已经保证了mp[pre[i]−k] 里记录的 pre[j] 的下标范围是 0≤j≤i,
        //并且是根据以当前下标为结尾的前缀和,所以前缀和数组也不需要建立.
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode.cn/problems/subarray-sum-equals-k/solution/he-wei-kde-zi-shu-zu-by-leetcode-solution/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        //前缀和,以及对应的下标的列表(因为元素有负数,所以前缀和有可能相同)

        //前缀和,以及对应的下标的列表(因为元素有负数,所以前缀和有可能相同)
        val map = mutableMapOf<Int, MutableList<Int>>()
        var sum = 0
        //数组前缀和
        val preSum = IntArray(nums.size)
        for ((i, n) in nums.withIndex()) {
            sum += n
            preSum[i] = sum
            (map[sum] ?: let {
                mutableListOf<Int>().also {
                    map[sum] = it
                }
            }).add(i)
        }
        var result = 0
        for (i in nums.indices) {
            //先判断当前前缀和是否等于k
            if (preSum[i] == k) {
                result++
            }
            //从哈希表中取出值为preSum[i] - k的列表元素
            map[preSum[i] - k]?.let {
                for (index in it) {
                    if (index < i) {
                        //只要当下标小于当前元素下标时,才可构成有效的连续的子数组
                        result++
                    } else {
                        break
                    }
                }
            }
        }
        return result
    }
}