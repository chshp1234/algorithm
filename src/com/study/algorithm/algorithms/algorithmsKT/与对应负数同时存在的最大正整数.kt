package com.study.algorithm.algorithms.algorithmsKT

//给你一个 不包含 任何零的整数数组 nums ，找出自身与对应的负数都在数组中存在的最大正整数 k 。
//
//返回正整数 k ，如果不存在这样的整数，返回 -1 。
//
// 
//
//示例 1：
//
//输入：nums = [-1,2,-3,3]
//输出：3
//解释：3 是数组中唯一一个满足题目要求的 k 。
//示例 2：
//
//输入：nums = [-1,10,6,7,-7,1]
//输出：7
//解释：数组中存在 1 和 7 对应的负数，7 的值更大。
//示例 3：
//
//输入：nums = [-10,8,6,7,-2,-3]
//输出：-1
//解释：不存在满足题目要求的 k ，返回 -1 。
// 
//
//提示：
//
//1 <= nums.length <= 1000
//-1000 <= nums[i] <= 1000
//nums[i] != 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/largest-positive-integer-that-exists-with-its-negative
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 与对应负数同时存在的最大正整数 {
    fun findMaxK(nums: IntArray): Int {
        //哈希表
        //遍历数组,判断当前元素-num是否在哈希表中,并且Math.abs(num)是否大于记录的结果值
        //如果满足,则更新结果res=Math.abs(num)
        //再将当前元素加入哈希表中
        var res = -1
        val map = HashSet<Int>()
        for (n in nums) {
            if (map.contains(-n) && Math.abs(n) > res) {
                res = Math.abs(n)
            }
            map.add(n)
        }
        return res
    }
}