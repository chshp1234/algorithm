package com.study.algorithm.algorithms.algorithmsKT

//给定一个数组 nums ，将其划分为两个连续子数组 left 和 right， 使得：
//
//left 中的每个元素都小于或等于 right 中的每个元素。
//left 和 right 都是非空的。
//left 的长度要尽可能小。
//在完成这样的分组后返回 left 的 长度 。
//
//用例可以保证存在这样的划分方法。
//
// 
//
//示例 1：
//
//输入：nums = [5,0,3,8,6]
//输出：3
//解释：left = [5,0,3]，right = [8,6]
//示例 2：
//
//输入：nums = [1,1,1,0,6,12]
//输出：4
//解释：left = [1,1,1,0]，right = [6,12]
// 
//
//提示：
//
//2 <= nums.length <= 105
//0 <= nums[i] <= 106
//可以保证至少有一种方法能够按题目所描述的那样对 nums 进行划分。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/partition-array-into-disjoint-intervals
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 分割数组 {
    fun partitionDisjoint(nums: IntArray): Int {
        //两趟遍历
        //用一个数组max,记录max[i]为0..i时的最大元素
        //用一个数组min,记录min[i]为size-1..i时的最小元素
        //因为要求left尽可能小,我们可以使用一个数组arr,先从右到左遍历最小元素,再从左到右遍历最大元素
        //当array[i] >= array[i - 1],说明找到一个分割线使得nums[0..i]的所有元素都小于或等于nums[i+1..size-1],那么left=i,并返回left
        val array = IntArray(nums.size)

        array[nums.size - 1] = nums[nums.size - 1]
        for (i in nums.size - 2 downTo 0) {
            if (nums[i] > array[i + 1]) {
                array[i] = array[i + 1]
            } else {
                array[i] = nums[i]
            }
        }

        array[0] = nums[0]
        for (i in 1 until nums.size) {
            if (array[i] >= array[i - 1]) {
                return i
            }
            if (nums[i] < array[i - 1]) {
                array[i] = array[i - 1]
            } else {
                array[i] = nums[i]
            }
        }

        return 0
    }
}