package com.study.algorithm.algorithms.algorithmsKT

//给定一个  无重复元素 的 有序 整数数组 nums 。
//
//返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
//
//列表中的每个区间范围 [a,b] 应该按如下格式输出：
//
//"a->b" ，如果 a != b
//"a" ，如果 a == b
//
//
//示例 1：
//
//输入：nums = [0,1,2,4,5,7]
//输出：["0->2","4->5","7"]
//解释：区间范围是：
//[0,2] --> "0->2"
//[4,5] --> "4->5"
//[7,7] --> "7"
//示例 2：
//
//输入：nums = [0,2,3,4,6,8,9]
//输出：["0","2->4","6","8->9"]
//解释：区间范围是：
//[0,0] --> "0"
//[2,4] --> "2->4"
//[6,6] --> "6"
//[8,9] --> "8->9"
//
//
//提示：
//
//0 <= nums.length <= 20
//-231 <= nums[i] <= 231 - 1
//nums 中的所有值都 互不相同
//nums 按升序排列
class 汇总区间 {
    fun summaryRanges(nums: IntArray): List<String> {
        // 模拟
        //记录连续数组元素的第一个元素first
        //当遇到nums[i] != nums[i - 1] + 1时，说明此时元素不连续，将结果加入数组
        //判断first == nums[i - 1]，如果相等，说明连续的元素只有一个；否则，连续元素有多个。按规则加入结果即可
        //最后遍历结束别忘记在判断一次
        if (nums.isEmpty()) {
            return listOf()
        }
        var first = nums[0]
        val res = mutableListOf<String>()
        for (i in 1 until nums.size) {
            if (nums[i] != nums[i - 1] + 1) {
                if (first == nums[i - 1]) {
                    res.add(first.toString())
                } else {
                    res.add(first.toString() + "->" + nums[i - 1].toString())
                }
                first = nums[i]
            }
        }
        if (nums[nums.size - 1] == first) {
            res.add(first.toString())
        } else {
            res.add(first.toString() + "->" + nums[nums.size - 1].toString())
        }
        return res
    }
}