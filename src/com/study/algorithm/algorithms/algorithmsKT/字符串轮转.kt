package com.study.algorithm.algorithms.algorithmsKT

//字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
//
//示例1:
//
// 输入：s1 = "waterbottle", s2 = "erbottlewat"
// 输出：True
//示例2:
//
// 输入：s1 = "aa", s2 = "aba"
// 输出：False
//提示：
//
//字符串长度在[0, 100000]范围内。
//说明:
//
//你能只调用一次检查子串的方法吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/string-rotation-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 字符串轮转 {
    fun isFlipedString(s1: String, s2: String): Boolean {
        //如果s1翻转后可以得到s2
        //那么s1+s1的字符串必然包含s2
        return s1.length == s2.length && (s1 + s1).contains(s2)
    }

    fun isFlipedString2(s1: String, s2: String): Boolean {
        //模拟
        //一趟遍历,模拟选择每个下标为翻转处,判断s1根据下标i翻转后的字符串是否和s2相同
        val len = s1.length
        if (len != s2.length) {
            return false
        }
        if (len == 0) {
            return true
        }

        for (i in 0 until len) {
            if ((s1.substring(i until len) + s1.substring(0 until i)) == s2) {
                return true
            }
        }
        return false
    }
}