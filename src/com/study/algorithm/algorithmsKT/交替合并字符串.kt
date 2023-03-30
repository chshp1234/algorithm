package com.study.algorithm.algorithmsKT

//给你两个字符串 word1 和 word2 。请你从 word1 开始，通过交替添加字母来合并字符串。如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
//
//返回 合并后的字符串 。
//
// 
//
//示例 1：
//
//输入：word1 = "abc", word2 = "pqr"
//输出："apbqcr"
//解释：字符串合并情况如下所示：
//word1：  a   b   c
//word2：    p   q   r
//合并后：  a p b q c r
//示例 2：
//
//输入：word1 = "ab", word2 = "pqrs"
//输出："apbqrs"
//解释：注意，word2 比 word1 长，"rs" 需要追加到合并后字符串的末尾。
//word1：  a   b
//word2：    p   q   r   s
//合并后：  a p b q   r   s
//示例 3：
//
//输入：word1 = "abcd", word2 = "pq"
//输出："apbqcd"
//解释：注意，word1 比 word2 长，"cd" 需要追加到合并后字符串的末尾。
//word1：  a   b   c   d
//word2：    p   q
//合并后：  a p b q c   d
// 
//
//提示：
//
//1 <= word1.length, word2.length <= 100
//word1 和 word2 由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/merge-strings-alternately
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 交替合并字符串 {
    fun mergeAlternately(word1: String, word2: String): String {
        //双指针
        //用两个指针指向word1下标和word2下标
        //交替将两个字符串中的字符加入结果中
        //最后再将没遍历完的一个字符串剩下的字符加入到结果中
        var index1 = 0
        var index2 = 0
        val result = StringBuilder()

        while (index1 < word1.length && index2 < word2.length) {
            result.append(word1[index1++])
            result.append(word2[index2++])
        }
        while (index1 < word1.length) {
            result.append(word1[index1++])
        }
        while (index2 < word1.length) {
            result.append(word2[index2++])
        }

        return result.toString()
    }
}