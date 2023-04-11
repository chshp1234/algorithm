package com.study.algorithm.algorithms.algorithmsKT

//给你一个由不同字符组成的字符串 allowed 和一个字符串数组 words 。如果一个字符串的每一个字符都在 allowed 中，就称这个字符串是 一致字符串 。
//
//请你返回 words 数组中 一致字符串 的数目。
//
// 
//
//示例 1：
//
//输入：allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
//输出：2
//解释：字符串 "aaab" 和 "baa" 都是一致字符串，因为它们只包含字符 'a' 和 'b' 。
//示例 2：
//
//输入：allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
//输出：7
//解释：所有字符串都是一致的。
//示例 3：
//
//输入：allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
//输出：4
//解释：字符串 "cc"，"acd"，"ac" 和 "d" 是一致字符串。
// 
//
//提示：
//
//1 <= words.length <= 104
//1 <= allowed.length <= 26
//1 <= words[i].length <= 10
//allowed 中的字符 互不相同 。
//words[i] 和 allowed 只包含小写英文字母。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/count-the-number-of-consistent-strings
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 统计一致字符串的数目 {
    fun countConsistentStrings(allowed: String, words: Array<String>): Int {
        //哈希,位运算
        //因为字符串只包含26个小写字母,因此可以用一个int类型的值表示其包含的所有字母
        //令allowed做位运算后的的标志为mask,words中每个字符串的标志为maskWord
        //只需要求出maskWord中每个'1'是否存在mask中即可,也即maskWord是否是mask的子集
        //那么只需要计算(mask | maskWord)是否等于mask,即可得出
        val mask = stringHash(allowed)

        var count = 0

        for (word in words) {
            if (mask or stringHash(word) == mask) {
                count++
            }
        }

        return count
    }

    fun stringHash(s: String): Int {
        //计算字符串的标志
        var hash = 0
        for (c in s) {
            hash = hash or (1 shl (c - 'a'))
        }
        return hash
    }
}