package com.study.algorithm.algorithms.algorithmsKT

//给你一个整数数组 nums ，和两个整数 limit 与 goal 。数组 nums 有一条重要属性：abs(nums[i]) <= limit 。
//
//返回使数组元素总和等于 goal 所需要向数组中添加的 最少元素数量 ，添加元素 不应改变 数组中 abs(nums[i]) <= limit 这一属性。
//
//注意，如果 x >= 0 ，那么 abs(x) 等于 x ；否则，等于 -x 。
//
// 
//
//示例 1：
//
//输入：nums = [1,-1,1], limit = 3, goal = -4
//输出：2
//解释：可以将 -2 和 -3 添加到数组中，数组的元素总和变为 1 - 1 + 1 - 2 - 3 = -4 。
//示例 2：
//
//输入：nums = [1,-10,9,1], limit = 100, goal = 0
//输出：1
// 
//
//提示：
//
//1 <= nums.length <= 105
//1 <= limit <= 106
//-limit <= nums[i] <= limit
//-109 <= goal <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-elements-to-add-to-form-a-given-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 构成特定和需要添加的最少元素 {
    fun minElements(nums: IntArray, limit: Int, goal: Int): Int {
        //模拟,上取整
        //先算出原数组元素总和(防止溢出,用long),再算出总和与limit的差值diff的绝对值
        //最后,需要的数量就是diff/limit
        //这里注意:
        //若 limit 整除 diff,答案就是diff/limit
        //否则答案是(diff/limit)+1
        //而这两种情况可以使用上取整的方法合成一个公式:(diff+limit-1)/limit
        var sum: Long = 0
        for (n in nums) {
            sum += n
        }
        val diff = Math.abs(sum - goal)
        return ((diff + limit - 1) / limit).toInt()
    }
}