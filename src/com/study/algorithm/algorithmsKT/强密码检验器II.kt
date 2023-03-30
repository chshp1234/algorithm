package com.study.algorithm.algorithmsKT

//如果一个密码满足以下所有条件，我们称它是一个 强 密码：
//
//它有至少 8 个字符。
//至少包含 一个小写英文 字母。
//至少包含 一个大写英文 字母。
//至少包含 一个数字 。
//至少包含 一个特殊字符 。特殊字符为："!@#$%^&*()-+" 中的一个。
//它 不 包含 2 个连续相同的字符（比方说 "aab" 不符合该条件，但是 "aba" 符合该条件）。
//给你一个字符串 password ，如果它是一个 强 密码，返回 true，否则返回 false 。
//
// 
//
//示例 1：
//
//输入：password = "IloveLe3tcode!"
//输出：true
//解释：密码满足所有的要求，所以我们返回 true 。
//示例 2：
//
//输入：password = "Me+You--IsMyDream"
//输出：false
//解释：密码不包含数字，且包含 2 个连续相同的字符。所以我们返回 false 。
//示例 3：
//
//输入：password = "1aB!"
//输出：false
//解释：密码不符合长度要求。所以我们返回 false 。
// 
//
//提示：
//
//1 <= password.length <= 100
//password 包含字母，数字和 "!@#$%^&*()-+" 这些特殊字符。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/strong-password-checker-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 强密码检验器II {
    fun strongPasswordCheckerII(password: String): Boolean {
        //模拟
        //按题意遍历字符串，用变量记录是否遇到的数组，大小写以及特殊字符
        //再用一个变量记录上一个字符，如果当前字符和上一个字符相同，则直接返回false
        //注意一开始应首先判断密码长度是否大于等于8
        if (password.length < 8) {
            return false
        }
        val signs = setOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+')
        var lowercase = false
        var capital = false
        var sign = false
        var num = false
        var last = ' '
        for (c in password) {
            when (c) {
                last -> return false
                in 'a'..'z' -> lowercase = true
                in 'A'..'Z' -> capital = true
                in signs -> sign = true
                in '0'..'9' -> num = true
            }
            last = c
        }
        return lowercase && capital && sign && num
    }
}