package com.study.algorithm.algorithms.algorithmsKT

//给你一个以行程长度编码压缩的整数列表 nums 。
//
//考虑每对相邻的两个元素 [freq, val] = [nums[2*i], nums[2*i+1]] （其中 i >= 0 ），每一对都表示解压后子列表中有 freq 个值为 val 的元素，你需要从左到右连接所有子列表以生成解压后的列表。
//
//请你返回解压后的列表。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,3,4]
//输出：[2,4,4,4]
//解释：第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]。
//第二对 [3,4] 代表着 4 的出现频次为 3，所以生成数组 [4,4,4]。
//最后将它们串联到一起 [2] + [4,4,4] = [2,4,4,4]。
//示例 2：
//
//输入：nums = [1,1,2,3]
//输出：[1,3,3]
// 
//
//提示：
//
//2 <= nums.length <= 100
//nums.length % 2 == 0
//1 <= nums[i] <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/decompress-run-length-encoded-list
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 解压缩编码列表 {
    fun decompressRLElist(nums: IntArray): IntArray {
        //模拟
        //直接遍历每个偶数下班,偶数下标i处的数代表频次,i+1处的数字代表数字
        //将数字按频次次数加入列表中
        //最后将列表转换成数组并输出
        val list = ArrayList<Int>()
        for (i in nums.indices step 2) {
            repeat(nums[i]) {
                list.add(nums[i + 1])
            }
        }
        return list.toIntArray()
    }
}