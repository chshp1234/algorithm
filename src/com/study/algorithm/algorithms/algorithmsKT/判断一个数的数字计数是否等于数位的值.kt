package com.study.algorithm.algorithms.algorithmsKT

//给你一个下标从 0 开始长度为 n 的字符串 num ，它只包含数字。
//
//如果对于 每个 0 <= i < n 的下标 i ，都满足数位 i 在 num 中出现了 num[i]次，那么请你返回 true ，否则返回 false 。
//
// 
//
//示例 1：
//
//输入：num = "1210"
//输出：true
//解释：
//num[0] = '1' 。数字 0 在 num 中出现了一次。
//num[1] = '2' 。数字 1 在 num 中出现了两次。
//num[2] = '1' 。数字 2 在 num 中出现了一次。
//num[3] = '0' 。数字 3 在 num 中出现了零次。
//"1210" 满足题目要求条件，所以返回 true 。
//示例 2：
//
//输入：num = "030"
//输出：false
//解释：
//num[0] = '0' 。数字 0 应该出现 0 次，但是在 num 中出现了两次。
//num[1] = '3' 。数字 1 应该出现 3 次，但是在 num 中出现了零次。
//num[2] = '0' 。数字 2 在 num 中出现了 0 次。
//下标 0 和 1 都违反了题目要求，所以返回 false 。
// 
//
//提示：
//
//n == num.length
//1 <= n <= 10
//num 只包含数字。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/check-if-number-has-equal-digit-count-and-digit-value
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 判断一个数的数字计数是否等于数位的值 {
    fun digitCount(num: String): Boolean {
        //哈希表
        //先用一个10位数组,记录字符串中每个元素代表的数字出现的次数
        //再遍历字符串,判断下标i出现的次数是否等于下标i所对应的元素的数字
        val counter = IntArray(10)
        for (n in num) {
            counter[n - '0']++
        }
        for (i in num.indices) {
            if ((num[i] - '0') != counter[i]) {
                return false
            }
        }
        return true
    }
}