package com.study.algorithm.algorithmsKT

//给你一个字符串 s ，返回 s 中 同构子字符串 的数目。由于答案可能很大，只需返回对 109 + 7 取余 后的结果。
//
//同构字符串 的定义为：如果一个字符串中的所有字符都相同，那么该字符串就是同构字符串。
//
//子字符串 是字符串中的一个连续字符序列。
//
// 
//
//示例 1：
//
//输入：s = "abbcccaa"
//输出：13
//解释：同构子字符串如下所列：
//"a"   出现 3 次。
//"aa"  出现 1 次。
//"b"   出现 2 次。
//"bb"  出现 1 次。
//"c"   出现 3 次。
//"cc"  出现 2 次。
//"ccc" 出现 1 次。
//3 + 1 + 2 + 1 + 3 + 2 + 1 = 13
//示例 2：
//
//输入：s = "xy"
//输出：2
//解释：同构子字符串是 "x" 和 "y" 。
//示例 3：
//
//输入：s = "zzzzz"
//输出：15
// 
//
//提示：
//
//1 <= s.length <= 105
//s 由小写字符串组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/count-number-of-homogenous-substrings
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 统计同构子字符串的数目 {
    //数学，组合
    //统计连续相同字符的数量n，那么这些字符可以组成的同构子字符串数量为1+2+3+...+n
    //设一个变量为上一个字符，用来判断当前字符是否和上一个字符相同
    //设一个变量为当前连续相同的字符的数量
    fun countHomogenous(s: String): Int {
        var res = 1
        var last = s[0]
        var count = 1
        for (i in 1 until s.length) {
            if (s[i] == last) {
                count++
            } else {
                last = s[i]
                count = 1
            }
            res = (res + count) % 1000000007
        }
        return res
    }
}