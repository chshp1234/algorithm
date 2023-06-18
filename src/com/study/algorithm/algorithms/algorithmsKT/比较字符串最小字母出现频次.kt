package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//定义一个函数 f(s)，统计 s  中（按字典序比较）最小字母的出现频次 ，其中 s 是一个非空字符串。
//
//例如，若 s = "dcce"，那么 f(s) = 2，因为字典序最小字母是 "c"，它出现了 2 次。
//
//现在，给你两个字符串数组待查表 queries 和词汇表 words 。对于每次查询 queries[i] ，需统计 words 中满足 f(queries[i]) < f(W) 的 词的数目 ，W 表示词汇表 words 中的每个词。
//
//请你返回一个整数数组 answer 作为答案，其中每个 answer[i] 是第 i 次查询的结果。
//
// 
//
//示例 1：
//
//输入：queries = ["cbd"], words = ["zaaaz"]
//输出：[1]
//解释：查询 f("cbd") = 1，而 f("zaaaz") = 3 所以 f("cbd") < f("zaaaz")。
//示例 2：
//
//输入：queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
//输出：[1,2]
//解释：第一个查询 f("bbb") < f("aaaa")，第二个查询 f("aaa") 和 f("aaaa") 都 > f("cc")。
// 
//
//提示：
//
//1 <= queries.length <= 2000
//1 <= words.length <= 2000
//1 <= queries[i].length, words[i].length <= 10
//queries[i][j]、words[i][j] 都由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/compare-strings-by-frequency-of-the-smallest-character
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 比较字符串最小字母出现频次 {

    @Test
    fun 比较字符串最小字母出现频次() {
        println(
            "比较字符串最小字母出现频次：" + numSmallerByFrequency(
                arrayOf("bba"),
                arrayOf("aaabbb", "aab", "babbab", "babbbb", "b", "bbbbbbbbab", "a", "bbbbbbbbbb", "baaabbaab", "aa")
            )
        )
    }

    fun numSmallerByFrequency(queries: Array<String>, words: Array<String>): IntArray {
        //计数,排序,二分查找
        //用一个数组统计每个字符串中每个字符出现的频次,并从低到高找到第一个大于0的频次,即为当前字符串的最小字母频次
        //统计完words中每个字符串的频次,并进行排序
        //遍历queries数组,计算出其中字符串的频次后,在之前统计的words频次数组中进行二分搜索,查找出大于该频次的数量,更新进结果数组中
        val wordsCounter = IntArray(words.size) { f(words[it]) }.apply { sort() }
        return IntArray(queries.size) { binarySearch(f(queries[it]), wordsCounter) }
    }

    fun binarySearch(num: Int, array: IntArray): Int {
        var l = 0
        var r = array.size - 1
        while (l <= r) {
            val mid = (l + r) shr 1
            if (num >= array[mid]) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }
        return array.size - l
    }

    fun f(s: String): Int {
        val counter = IntArray(26)
        for (c in s) {
            counter[c - 'a']++
        }
        for (i in 0 until 26) {
            if (counter[i] > 0) {
                return counter[i]
            }
        }
        return -1
    }
}