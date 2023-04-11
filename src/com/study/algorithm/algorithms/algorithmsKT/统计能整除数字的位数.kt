package com.study.algorithm.algorithms.algorithmsKT

//给你一个整数 num ，返回 num 中能整除 num 的数位的数目。
//
//如果满足 nums % val == 0 ，则认为整数 val 可以整除 nums 。
//
// 
//
//示例 1：
//
//输入：num = 7
//输出：1
//解释：7 被自己整除，因此答案是 1 。
//示例 2：
//
//输入：num = 121
//输出：2
//解释：121 可以被 1 整除，但无法被 2 整除。由于 1 出现两次，所以返回 2 。
//示例 3：
//
//输入：num = 1248
//输出：4
//解释：1248 可以被它每一位上的数字整除，因此答案是 4 。
// 
//
//提示：
//
//1 <= num <= 109
//num 的数位中不含 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/count-the-digits-that-divide-a-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 统计能整除数字的位数 {
    fun countDigits(num: Int): Int {
        //枚举
        //枚举num的每一位数val,并判断num%val是否等于0即可
        var res = 0
        var n = num
        while (n > 0) {
            if (num % (n % 10) == 0) {
                res++
            }
            n /= 10
        }
        return res
    }
}