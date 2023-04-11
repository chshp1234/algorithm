package com.study.algorithm.algorithms.algorithmsKT

//给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。
//
//字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
//
//例如， “ace” 是 “abcde” 的子序列。
// 
//
//示例 1:
//
//输入: s = "abcde", words = ["a","bb","acd","ace"]
//输出: 3
//解释: 有三个是 s 的子序列的单词: "a", "acd", "ace"。
//Example 2:
//
//输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
//输出: 2
// 
//
//提示:
//
//1 <= s.length <= 5 * 104
//1 <= words.length <= 5000
//1 <= words[i].length <= 50
//words[i]和 s 都只由小写字母组成。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/number-of-matching-subsequences
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 匹配子序列的单词数 {
    fun numMatchingSubseq(s: String, words: Array<String>): Int {
        //双指针
        //两个指针分别指向给定字符串s和目标字符串word
        //如果字符相同,则继续匹配目标字符串的下一个字符,其中给定字符串的下标将一直移动
        //如果目标字符串匹配完毕(指针移到了末尾),则说明匹配成功,否则匹配失败
        //将字符串数组中每个字符串都按上述方式匹配,匹配成功,则数量+1
        var count = 0
        for (word in words) {
            if (matchSubSeq(s, word)) {
                count++
            }
        }
        return count
    }

    fun matchSubSeq(s: String, word: String): Boolean {
        if (word.length > s.length) {
            return false
        }

        var i1 = 0
        var i2 = 0

        while (i1 < s.length && i2 < word.length) {
            if (s[i1] == word[i2]) {
                i2++
            }
            i1++
        }

        return i2 == word.length
    }

    fun numMatchingSubseq2(s: String, words: Array<String>): Int {
        //哈希表,二分
        //先用哈希表存储给定字符串中的所有字符以及字符在字符串中的位置
        //再用哈希表和目标字符串进行匹配
        //1.匹配目标字符串中的字符时,记录上一个匹配到的字符的下标,并查找当前要匹配的字符的下标
        //2.如果当前字符不在哈希表中,说明原给定字符串没有这个字符,匹配失败
        //3.再根据上一次找到的下标在下标列表中查找下一个因在的下标位置,由于列表中的下标元素是递增的,所以查找下一个下标时可以二分查找列表,
        //如果列表中所有元素都小于上一次查找的下标,则匹配失败,否则更新当前下标,继续查找匹配下一个字符
        //4.将字符串数组中每个字符串都按上述方式匹配,匹配成功,则数量+1
        var count = 0
        val map = arrayOfNulls<MutableList<Int>>(26)
        for ((i, c) in s.withIndex()) {
            (map[c - 'a'] ?: kotlin.run {
                val list = mutableListOf<Int>()
                map[c - 'a'] = list
                list
            }).add(i)
        }
        for (word in words) {
            if (word.length > s.length) {
                continue
            }
            if (matchSubSeq(map, word)) {
                count++
            }
        }
        return count
    }

    fun matchSubSeq(map: Array<out List<Int>?>, word: String): Boolean {
        var index = -1
        for (c in word) {
            val list = map[c - 'a']
            index = list?.binarySearch(index)?.let { nextIndex ->
                (if (nextIndex < 0) -nextIndex - 1 else nextIndex + 1).let {
                    if (it < list.size) list[it] else null
                }
            } ?: return false
        }
        return true
    }
}