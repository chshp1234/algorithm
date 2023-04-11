package com.study.algorithm.algorithms.algorithmsKT

//给你一个四位 正 整数 num 。请你使用 num 中的 数位 ，将 num 拆成两个新的整数 new1 和 new2 。new1 和 new2 中可以有 前导 0 ，且 num 中 所有 数位都必须使用。
//
//比方说，给你 num = 2932 ，你拥有的数位包括：两个 2 ，一个 9 和一个 3 。一些可能的 [new1, new2] 数对为 [22, 93]，[23, 92]，[223, 9] 和 [2, 329] 。
//请你返回可以得到的 new1 和 new2 的 最小 和。
//
// 
//
//示例 1：
//
//输入：num = 2932
//输出：52
//解释：可行的 [new1, new2] 数对为 [29, 23] ，[223, 9] 等等。
//最小和为数对 [29, 23] 的和：29 + 23 = 52 。
//示例 2：
//
//输入：num = 4009
//输出：13
//解释：可行的 [new1, new2] 数对为 [0, 49] ，[490, 0] 等等。
//最小和为数对 [4, 9] 的和：4 + 9 = 13 。
// 
//
//提示：
//
//1000 <= num <= 9999
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-sum-of-four-digit-number-after-splitting-digits
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 拆分数位后四位数字的最小和 {
    fun minimumSum(num: Int): Int {
        //排序,贪心
        //将最小的两个数当分别当做分割后的两个数的"十位数",再将剩下的两个数分别设为两个个位数
        //返回这两个新分割的数的和
        return IntArray(4) { (num / (Math.pow(10.0, it.toDouble())) % 10).toInt() }.let {
            it.sort()
            it[0] * 10 + it[1] * 10 + it[2] + it[3]
        }
    }
}