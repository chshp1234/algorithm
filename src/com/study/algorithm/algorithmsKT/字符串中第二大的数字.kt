package com.study.algorithm.algorithmsKT

//给你一个混合字符串 s ，请你返回 s 中 第二大 的数字，如果不存在第二大的数字，请你返回 -1 。
//
//混合字符串 由小写英文字母和数字组成。
//
// 
//
//示例 1：
//
//输入：s = "dfa12321afd"
//输出：2
//解释：出现在 s 中的数字包括 [1, 2, 3] 。第二大的数字是 2 。
//示例 2：
//
//输入：s = "abc1111"
//输出：-1
//解释：出现在 s 中的数字只包含 [1] 。没有第二大的数字。
// 
//
//提示：
//
//1 <= s.length <= 500
//s 只包含小写英文字母和（或）数字。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/second-largest-digit-in-a-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 字符串中第二大的数字 {
    fun secondHighest(s: String): Int {
        //直接遍历
        //用两个数表示最大和次大的数
        //遍历字符串时，只判断是‘数字’类型的字符
        //如果数字类型的字符比最大的数大，则更新次大数为当最大数，最大数为当前字符
        //如果字符比次大的数大并且小于最大数，则更新次大数
        var first = 0.toChar()
        var second = 0.toChar()

        for (c in s) {
            if (c <= '9') {
                if (c > first) {
                    second = first
                    first = c
                } else if (c > second && c < first) {
                    second = c
                }
            }
        }

        return if (second.toInt() == 0) -1 else (second - '0')
    }
}