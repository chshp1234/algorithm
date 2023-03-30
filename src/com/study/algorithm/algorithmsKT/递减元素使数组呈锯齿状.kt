package com.study.algorithm.algorithmsKT

//给你一个整数数组 nums，每次 操作 会从中选择一个元素并 将该元素的值减少 1。
//
//如果符合下列情况之一，则数组 A 就是 锯齿数组：
//
//每个偶数索引对应的元素都大于相邻的元素，即 A[0] > A[1] < A[2] > A[3] < A[4] > ...
//或者，每个奇数索引对应的元素都大于相邻的元素，即 A[0] < A[1] > A[2] < A[3] > A[4] < ...
//返回将数组 nums 转换为锯齿数组所需的最小操作次数。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,3]
//输出：2
//解释：我们可以把 2 递减到 0，或把 3 递减到 1。
//示例 2：
//
//输入：nums = [9,6,1,6,2]
//输出：4
// 
//
//提示：
//
//1 <= nums.length <= 1000
//1 <= nums[i] <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/decrease-elements-to-make-array-zigzag
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 递减元素使数组呈锯齿状 {
    fun movesToMakeZigzag(nums: IntArray): Int {
        //贪心
        //注意此题,元素值只能减少
        //分类讨论,分别求出锯齿一开始是向上或向下的两种情况下的最小步骤
        //如果一开始是向上,那么我们从下标2开始遍历,并且进步为2,此下标下的元素将是锯齿凹槽处,可以进行减少,分别和左右两个元素比较,求出将要减少的最少步骤
        //如果一开始是向下,那么我们从下标1开始遍历,并且进步为2,此下标下的元素将是锯齿凹槽处,可以进行减少,分别和左右两个元素比较,求出将要减少的最少步骤
        //因为进步是2,所以最右侧元素可能没遍历到,所以:
        //当一开始是向上且元素数量为偶数,最后一个元素需要小于等于倒数第二个元素
        //当一开始是向下且元素数量为奇数,最后一个元素需要小于等于倒数第二个元素
        //如果一开始是向上的,因为是从下标2开始遍历,所以下标0和下标1的元素还需要进行一次比较
        if (nums.size == 1) {
            return 0
        }
        return Math.min(minStepDown(nums), minStepUp(nums))
    }

    fun minStepDown(nums: IntArray): Int {
        var step = 0

        for (i in 1 until nums.size - 1 step 2) {
            val min = Math.min(nums[i - 1], nums[i + 1])
            if (nums[i] >= min) {
                step += nums[i] - min + 1
            }
        }

        if (nums.size % 2 == 0 && nums[nums.size - 1] >= nums[nums.size - 2]) {
            step += nums[nums.size - 1] - nums[nums.size - 2] + 1
        }

        return step
    }

    fun minStepUp(nums: IntArray): Int {
        var step = 0

        for (i in 2 until nums.size - 1 step 2) {
            val min = Math.min(nums[i - 1], nums[i + 1])
            if (nums[i] >= min) {
                step += nums[i] - min + 1
            }
        }

        if (nums[0] >= nums[1]) {
            step += nums[0] - nums[1] + 1
        }

        if (nums.size % 2 == 1 && nums[nums.size - 1] >= nums[nums.size - 2]) {
            step += nums[nums.size - 1] - nums[nums.size - 2] + 1
        }

        return step
    }

    fun minStep2(nums: IntArray, start: Boolean): Int {
        var step = 0
        var last = nums[0]
        var up = start
        for (i in 1 until nums.size) {
            if (up) {
                if (nums[i] <= last) {
                    val n = last + 1
                    step += n - nums[i]
                    last = n
                } else {
                    last = nums[i]
                }
            } else {
                if (nums[i] >= last) {
                    val n = last - 1
                    step += nums[i] - n
                    last = n
                } else {
                    last = nums[i]
                }
            }
            up = !up
        }
        return step
    }

}