package com.study.algorithm.algorithmsKT

//给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
//
//示例 1：
//
//输入: s1 = "abc", s2 = "bca"
//输出: true
//示例 2：
//
//输入: s1 = "abc", s2 = "bad"
//输出: false
//说明：
//
//0 <= len(s1) <= 100
//0 <= len(s2) <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/check-permutation-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 判定是否互为字符重排 {
    fun CheckPermutation(s1: String, s2: String): Boolean {
        //哈希表
        //1. 首先判断俩字符串长度是否相同
        //2. 用一个临时数组(**注意题目可没说是小写字母,但...**)记录s1每个字符出现的次数
        //3. 遍历s2字符串,从临时数组中减去s2出现的字符的次数,当次数小于0时返回fasle(因为已经确认俩字符串长度肯定相同了,所以当有一个字符频次小于0时,俩字符串肯定不会相同)
        if (s1.length != s2.length) {
            return false
        }
        val charArray = IntArray(26)
        for (c in s1) {
            //记录s1中每个字符出现的频次
            charArray[c - 'a']++
        }
        for (c in s2) {
            if (--charArray[c - 'a'] < 0) {
                //因为已经确认俩字符串长度肯定相同了,所以当有一个字符频次小于0时,俩字符串肯定不会相同
                return false
            }
        }
        return true
    }
}