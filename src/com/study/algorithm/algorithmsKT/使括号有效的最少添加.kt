package com.study.algorithm.algorithmsKT

//只有满足下面几点之一，括号字符串才是有效的：
//
//它是一个空字符串，或者
//它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
//它可以被写作 (A)，其中 A 是有效字符串。
//给定一个括号字符串 s ，移动N次，你就可以在字符串的任何位置插入一个括号。
//
//例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。
//返回 为使结果字符串 s 有效而必须添加的最少括号数。
//
// 
//
//示例 1：
//
//输入：s = "())"
//输出：1
//示例 2：
//
//输入：s = "((("
//输出：3
// 
//
//提示：
//
//1 <= s.length <= 1000
//s 只包含 '(' 和 ')' 字符。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 使括号有效的最少添加 {
    fun minAddToMakeValid(s: String): Int {
        //一趟遍历，计算需要增加多少左括号和右括号
        var left = 0
        var right = 0
        for (c in s) {
            //如果字符是"("，说明在右侧应该需要一个")"
            if (c == '(') {
                right++
            }
            //如果")"已和其左侧的"("凑成对数，那么说明其左侧需要一个"("
            else if (right == 0) {
                left++
            }
            //当前所需的")"数量-1
            else {
                right--
            }
        }
        //所需插入的所有"("和")"数量
        return left + right
    }
}