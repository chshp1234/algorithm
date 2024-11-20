package com.study.algorithm.algorithms.algorithmsKT

//给你两个字符串数组 words1 和 words2 ，请你返回在两个字符串数组中 都恰好出现一次 的字符串的数目。
//
//
//
//示例 1：
//
//输入：words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
//输出：2
//解释：
//- "leetcode" 在两个数组中都恰好出现一次，计入答案。
//- "amazing" 在两个数组中都恰好出现一次，计入答案。
//- "is" 在两个数组中都出现过，但在 words1 中出现了 2 次，不计入答案。
//- "as" 在 words1 中出现了一次，但是在 words2 中没有出现过，不计入答案。
//所以，有 2 个字符串在两个数组中都恰好出现了一次。
//示例 2：
//
//输入：words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
//输出：0
//解释：没有字符串在两个数组中都恰好出现一次。
//示例 3：
//
//输入：words1 = ["a","ab"], words2 = ["a","a","a","ab"]
//输出：1
//解释：唯一在两个数组中都出现一次的字符串是 "ab" 。
//
//
//提示：
//
//1 <= words1.length, words2.length <= 1000
//1 <= words1[i].length, words2[j].length <= 30
//words1[i] 和 words2[j] 都只包含小写英文字母。
class 统计出现过一次的公共字符串 {
    //哈希表
    //首先用两个哈希表，分别记录两个字符串数组中字符串出现的次数
    //然后遍历其中一个哈希表，判断其value是否为1，其key是否存在于map2中以及在map2中对应的次数是否为1。如果条件成立，则结果+1
    fun countWords(words1: Array<String>, words2: Array<String>): Int {
        val map1 = HashMap<String, Int>()
        val map2 = HashMap<String, Int>()
        for (word in words1) {
            map1[word] = map1.getOrDefault(word, 0) + 1
        }
        for (word in words2) {
            map2[word] = map2.getOrDefault(word, 0) + 1
        }
        return map1.filter { it.value == 1 && map2.getOrDefault(it.key, 0) == 1 }.size
    }
}