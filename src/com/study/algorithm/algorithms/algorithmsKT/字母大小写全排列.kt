package com.study.algorithm.algorithms.algorithmsKT

//给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
//
//返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
//
// 
//
//示例 1：
//
//输入：s = "a1b2"
//输出：["a1b2", "a1B2", "A1b2", "A1B2"]
//示例 2:
//
//输入: s = "3z4"
//输出: ["3z4","3Z4"]
// 
//
//提示:
//
//1 <= s.length <= 12
//s 由小写英文字母、大写英文字母和数字组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/letter-case-permutation
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 字母大小写全排列 {
    fun letterCasePermutation(s: String): List<String> {
        //回溯
        //回溯遍历每种可能
        //首先将当前下标字符加入可能的结果中
        //如果当前字符是小写字母，则转换并加入其对应的大写字母
        //如果当前字符是大写字母，则转换并加入其对应的小写字母
        val result = ArrayList<String>()

        backTrack(s, 0, CharArray(s.length), result)

        return result
    }

    fun backTrack(s: String, index: Int, changeS: CharArray, result: MutableList<String>) {
        if (index == s.length) {
            result.add(String(changeS))
            return
        }

        changeS[index] = s[index]
        backTrack(s, index + 1, changeS, result)

        if (s[index] >= 'a') {
            changeS[index] = s[index] - 32
            backTrack(s, index + 1, changeS, result)
        } else if (s[index] >= 'A') {
            changeS[index] = s[index] + 32
            backTrack(s, index + 1, changeS, result)
        }

    }
}