package com.study.algorithm.algorithms.algorithmsKT

//给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
//
// 
//
//示例 1：
//
//输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//输出： True
//说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
//示例 2:
//
//输入: nums = [1,2,3,4], k = 3
//输出: false
// 
//
//提示：
//
//1 <= k <= len(nums) <= 16
//0 < nums[i] < 10000
//每个元素的频率在 [1,4] 范围内
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/partition-to-k-equal-sum-subsets
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 划分为k个相等的子集 {
    //回溯
    //按桶进行回溯
    //将数组分成k份,每份中的数字总和必须为target=sum / k,判断每个数字时,可将其加入某一个桶中,若该桶总和大于target,则判断下一个桶,否则判断下一个数字元素
    //遍历到数组结尾,则说明平均分配成功
    fun canPartitionKSubsets(nums: IntArray, k: Int): Boolean {
        val sum = nums.sum()
        if (sum % k != 0) {
            return false
        }
        return backTrack(nums, 0, sum / k, IntArray(k))
    }

    fun backTrack(nums: IntArray, index: Int, target: Int, visit: IntArray): Boolean {
        if (index == nums.size) {
            return true
        }

        val set: MutableSet<Int> = HashSet()

        for (i in visit.indices) {
            //剪枝一:如果当前桶元素值已经遍历过(说明此方案失败),则跳过
            if (set.contains(visit[i])) {
                continue
            }
            //剪枝一:如果当前桶内元素和上一个
//            if (i > 0 && visit[i] == visit[i - 1]) {
//                continue
//            }
            //如果合大于target,跳过
            if (visit[i] + nums[index] > target) {
                continue
            }
            set.add(visit[i])
            visit[i] += nums[index]
            if (backTrack(nums, index + 1, target, visit)) {
                return true
            }
            visit[i] -= nums[index]
        }

        return false
    }

    //回溯
    //依次选取或者不选取每个元素
    //当总和等于target时,一个桶存放完毕,继续存放入下一个桶
    fun canPartitionKSubsets2(nums: IntArray, k: Int): Boolean {
        val sum = nums.sum()
        if (sum % k != 0) {
            return false
        }

        //方便剪枝
        nums.sort()
        return backTrack2(nums, 0, 0, sum / k, HashSet())
    }

    fun backTrack2(nums: IntArray, index: Int, current: Int, target: Int, visit: MutableSet<Int>): Boolean {

        if (current == target) {
            if (visit.size == nums.size) {
                return true
            }
            //一个桶装满了,继续装下一个桶,所以下一个桶的index从0开始
            return backTrack2(nums, 0, 0, target, visit)
        }

        var sum = current

        //剪枝一:从index位置开始遍历数字,前面的不用遍历,因为肯定是从前往后遍历过了
        for (i in index until nums.size) {
            if (visit.contains(i)) {
                continue
            }

            //剪枝二:如果上一个数没选过(说明跳过了),并且当前数和上一个数相同,那么当前数也可以跳过
            if (i > 0 && !visit.contains(i - 1) && nums[i] == nums[i - 1]) {
                continue
            }
            sum += nums[i]

            //剪枝三:进行排序了,sum>target,那么后续的数相加肯定也将>target
            if (sum > target) {
                return false
            }
            visit.add(i)
            if (backTrack2(nums, i + 1, sum, target, visit)) {
                return true
            }
            visit.remove(i)
            sum -= nums[i]
        }
        return false
    }
}