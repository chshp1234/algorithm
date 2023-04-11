package com.study.algorithm.algorithms.algorithmsKT

//给你一个仅由字符 '0' 和 '1' 组成的字符串 s 。一步操作中，你可以将任一 '0' 变成 '1' ，或者将 '1' 变成 '0' 。
//
//交替字符串 定义为：如果字符串中不存在相邻两个字符相等的情况，那么该字符串就是交替字符串。例如，字符串 "010" 是交替字符串，而字符串 "0100" 不是。
//
//返回使 s 变成 交替字符串 所需的 最少 操作数。
//
// 
//
//示例 1：
//
//输入：s = "0100"
//输出：1
//解释：如果将最后一个字符变为 '1' ，s 就变成 "0101" ，即符合交替字符串定义。
//示例 2：
//
//输入：s = "10"
//输出：0
//解释：s 已经是交替字符串。
//示例 3：
//
//输入：s = "1111"
//输出：2
//解释：需要 2 步操作得到 "0101" 或 "1010" 。
// 
//
//提示：
//
//1 <= s.length <= 104
//s[i] 是 '0' 或 '1'
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-changes-to-make-alternating-binary-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 生成交替二进制字符串的最少操作数 {
    fun minOperations(s: String): Int {
        //模拟
        //模拟起始字符是'0'或'1'的情况
        //根据题意,交替变换字符,如果变换后的字符和字符串中的字符不同,则操作步数+1
        //只需要计算出一种变换结果,另一种变换就是字符串长度减去当前这种变换步数
        //最后返回两种变换种步数最小的值
        val change = changeWord(s, '1')
        return Math.min(change, s.length - change)
    }

    fun changeWord(word: String, start: Char): Int {
        var step = 0
        var change = start.toInt()
        for (c in word) {
            if (c.toInt() != change) {
                step++
            }
            change = change xor 1
        }
        return step
    }
}