package com.study.algorithm.algorithmsKT

//给你一个字符串数组 words 和一个字符串 pref 。
//
//返回 words 中以 pref 作为 前缀 的字符串的数目。
//
//字符串 s 的 前缀 就是  s 的任一前导连续字符串。
//
// 
//
//示例 1：
//
//输入：words = ["pay","attention","practice","attend"], pref = "at"
//输出：2
//解释：以 "at" 作为前缀的字符串有两个，分别是："attention" 和 "attend" 。
//示例 2：
//
//输入：words = ["leetcode","win","loops","success"], pref = "code"
//输出：0
//解释：不存在以 "code" 作为前缀的字符串。
// 
//
//提示：
//
//1 <= words.length <= 100
//1 <= words[i].length, pref.length <= 100
//words[i] 和 pref 由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/counting-words-with-a-given-prefix
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 统计包含给定前缀的字符串 {
    fun prefixCount(words: Array<String>, pref: String): Int {
        //模拟，双指针
        //遍历字符串数组words，判断其中每个字符串是否包含前缀字符串pref
        //判断时，使用双指针，分别从字符串word和字符串pref开头开始判断，如果字符不相同则返回false，遍历结束则返回true
        //判断时，注意，若字符串word的长度小于字符串pref，则pref肯定不是word的前缀字符串，直接返回false
        var res = 0

        for (word in words) {
            if (check(word, pref)) {
                res++
            }
        }

        return res
    }

    fun check(word: String, pref: String): Boolean {
        if (word.length < pref.length) {
            return false
        }
        var l = 0
        var r = 0
        while (l < word.length && r < pref.length) {
            if (word[l] != pref[r]) {
                return false
            }
            l++
            r++
        }

        return true
    }
}