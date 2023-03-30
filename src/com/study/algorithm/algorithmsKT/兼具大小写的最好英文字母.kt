package com.study.algorithm.algorithmsKT

//给你一个由英文字母组成的字符串 s ，请你找出并返回 s 中的 最好 英文字母。返回的字母必须为大写形式。如果不存在满足条件的字母，则返回一个空字符串。
//
//最好 英文字母的大写和小写形式必须 都 在 s 中出现。
//
//英文字母 b 比另一个英文字母 a 更好 的前提是：英文字母表中，b 在 a 之 后 出现。
//
// 
//
//示例 1：
//
//输入：s = "lEeTcOdE"
//输出："E"
//解释：
//字母 'E' 是唯一一个大写和小写形式都出现的字母。
//示例 2：
//
//输入：s = "arRAzFif"
//输出："R"
//解释：
//字母 'R' 是大写和小写形式都出现的最好英文字母。
//注意 'A' 和 'F' 的大写和小写形式也都出现了，但是 'R' 比 'F' 和 'A' 更好。
//示例 3：
//
//输入：s = "AbCdEfGhIjK"
//输出：""
//解释：
//不存在大写和小写形式都出现的字母。
// 
//
//提示：
//
//1 <= s.length <= 1000
//s 由小写和大写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/greatest-english-letter-in-upper-and-lower-case
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 兼具大小写的最好英文字母 {
    fun greatestLetter(s: String): String {
        //哈希表
        //两个哈希表记录字符串中存在的小写和大写字母
        //最后在判断两个哈希表中，是否同时存在某个字母，若存在则赋予结果
        val upper = CharArray(26)
        val lower = CharArray(26)
        for (c in s) {
            if (c >= 'a' && c <= 'z') {
                lower[c - 'a'] = c
            } else if (c >= 'A' && c <= 'Z') {
                upper[c - 'A'] = c
            }
        }

        var res = ""
        for (i in 0 until 26) {
            if (lower[i] != 0.toChar() && upper[i] != 0.toChar()) {
                res = upper[i].toString()
            }
        }
        return res
    }
}