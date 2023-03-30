package com.study.algorithm.algorithmsKT

import org.junit.Test

//给你一个长度为 n 的二维整数数组 groups ，同时给你一个整数数组 nums 。
//
//你是否可以从 nums 中选出 n 个 不相交 的子数组，使得第 i 个子数组与 groups[i] （下标从 0 开始）完全相同，且如果 i > 0 ，那么第 (i-1) 个子数组在 nums 中出现的位置在第 i 个子数组前面。（也就是说，这些子数组在 nums 中出现的顺序需要与 groups 顺序相同）
//
//如果你可以找出这样的 n 个子数组，请你返回 true ，否则返回 false 。
//
//如果不存在下标为 k 的元素 nums[k] 属于不止一个子数组，就称这些子数组是 不相交 的。子数组指的是原数组中连续元素组成的一个序列。
//
// 
//
//示例 1：
//
//输入：groups = [[1,-1,-1],[3,-2,0]], nums = [1,-1,0,1,-1,-1,3,-2,0]
//输出：true
//解释：你可以分别在 nums 中选出第 0 个子数组 [1,-1,0,1,-1,-1,3,-2,0] 和第 1 个子数组 [1,-1,0,1,-1,-1,3,-2,0] 。
//这两个子数组是不相交的，因为它们没有任何共同的元素。
//示例 2：
//
//输入：groups = [[10,-2],[1,2,3,4]], nums = [1,2,3,4,10,-2]
//输出：false
//解释：选择子数组 [1,2,3,4,10,-2] 和 [1,2,3,4,10,-2] 是不正确的，因为它们出现的顺序与 groups 中顺序不同。
//[10,-2] 必须出现在 [1,2,3,4] 之前。
//示例 3：
//
//输入：groups = [[1,2,3],[3,4]], nums = [7,7,1,2,3,4,7,7]
//输出：false
//解释：选择子数组 [7,7,1,2,3,4,7,7] 和 [7,7,1,2,3,4,7,7] 是不正确的，因为它们不是不相交子数组。
//它们有一个共同的元素 nums[4] （下标从 0 开始）。
// 
//
//提示：
//
//groups.length == n
//1 <= n <= 103
//1 <= groups[i].length, sum(groups[i].length) <= 103
//1 <= nums.length <= 103
//-107 <= groups[i][j], nums[k] <= 107
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/form-array-by-concatenating-subarrays-of-another-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 通过连接另一个数组的子数组得到一个数组 {

    @Test
    fun 通过连接另一个数组的子数组得到一个数组() {
        println(
            canChoose(
                arrayOf(intArrayOf(1, -1, -1), intArrayOf(3, -2, 0)),
                intArrayOf(1, -1, 0, 1, -1, -1, 3, -2, 0)
            )
        )
    }

    fun canChoose(groups: Array<IntArray>, nums: IntArray): Boolean {
        //KMP+贪心
        //贪心的匹配若存在多个子串，能够匹配数组groups[i]
        //那么贪心的选择下标最左边的的子串，这样还能有足够多的元素能够匹配groups后面的几个数组
        //匹配时每个groups[i]时，可以参照字符串匹配KMP方法
        //记录nums中每个匹配后的子串的下标index，
        // 若能成功匹配，则下一个匹配下标为index+group[i].size
        // 若当前已经不能匹配，则返回false
        //匹配结束返回true
        var right = 0
        for (group in groups) {
            right = indexOfKMP(nums, right, group)
            if (right == -1) {
                return false
            }
            right += group.size
        }
        return true
    }

    private fun indexOfKMP(origin: IntArray, originStart: Int, target: IntArray): Int {
        val originLen = origin.size
        val targetLen = target.size

        var i = originStart
        var j = 0
        val next = getNext(target)
        while (i < originLen && j < targetLen) {
            if (j == -1 || origin[i] == target[j]) {
                // 如果相同就一一比较,j=-1表示不需要比较
                i++
                j++
            } else {
                // j返回到合适的位置，i不再需要回溯
                j = next[j]
            }
        }
        return if (j == targetLen) i - targetLen else -1
    }

    private fun getNext(target: IntArray): IntArray {
        val len = target.size
        if (len == 0) {
            return intArrayOf(-1)
        }
        val next = IntArray(target.size)

        // j表示当前位置，k表示子串需比较的第一位。
        var j = 0
        var k = -1
        next[0] = -1
        while (j < len - 1) {
            if (k == -1 || target[j] == target[k]) {
                j++
                k++
                next[j] = k
            } else {
                k = next[k]
            }
        }
        return next
    }
}