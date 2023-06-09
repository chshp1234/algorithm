package com.study.algorithm.algorithms.algorithmsKT

//给你一个字符串数组 words ，每一个字符串长度都相同，令所有字符串的长度都为 n 。
//
//每个字符串 words[i] 可以被转化为一个长度为 n - 1 的 差值整数数组 difference[i] ，其中对于 0 <= j <= n - 2 有 difference[i][j] = words[i][j+1] - words[i][j] 。注意两个字母的差值定义为它们在字母表中 位置 之差，也就是说 'a' 的位置是 0 ，'b' 的位置是 1 ，'z' 的位置是 25 。
//
//比方说，字符串 "acb" 的差值整数数组是 [2 - 0, 1 - 2] = [2, -1] 。
//words 中所有字符串 除了一个字符串以外 ，其他字符串的差值整数数组都相同。你需要找到那个不同的字符串。
//
//请你返回 words中 差值整数数组 不同的字符串。
//
// 
//
//示例 1：
//
//输入：words = ["adc","wzy","abc"]
//输出："abc"
//解释：
//- "adc" 的差值整数数组是 [3 - 0, 2 - 3] = [3, -1] 。
//- "wzy" 的差值整数数组是 [25 - 22, 24 - 25]= [3, -1] 。
//- "abc" 的差值整数数组是 [1 - 0, 2 - 1] = [1, 1] 。
//不同的数组是 [1, 1]，所以返回对应的字符串，"abc"。
//示例 2：
//
//输入：words = ["aaa","bob","ccc","ddd"]
//输出："bob"
//解释：除了 "bob" 的差值整数数组是 [13, -13] 以外，其他字符串的差值整数数组都是 [0, 0] 。
// 
//
//提示：
//
//3 <= words.length <= 100
//n == words[i].length
//2 <= n <= 20
//words[i] 只含有小写英文字母。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/odd-string-difference
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 差值数组不同的字符串 {
    fun oddString(words: Array<String>): String {
        //遍历,比较
        //首先遍历比较words[0]和words[1]中每个字符的差值
        //1.如果每个差值大小都相同,说明是两个相同字符串,否则,其中肯定有一个字符串是差值不同的字符串
        //2.如果words[0]和words[1]有一个是不同的字符串,则继续遍历words[2]和words[1],如果words[2]!=words[1],则words[1]是不同的字符串,否则words[0]是不同的字符串
        //3.如果words[0]=words[1],那么继续遍历后续字符串,直到找到那个不同的字符串为止
        val n = words[0].length
        for (i in 1 until n) {
            if (words[1][i] - words[0][i] != words[1][i - 1] - words[0][i - 1]) {
                for (j in 1 until n) {
                    if (words[2][j] - words[1][j] != words[2][j - 1] - words[1][j - 1]) {
                        return words[1]
                    }
                }
                return words[0]
            }
        }
        for (i in 2 until words.size) {
            for (j in 1 until n) {
                if (words[i][j] - words[0][j] != words[i][j - 1] - words[0][j - 1]) {
                    return words[i]
                }
            }
        }
        return ""
    }
}
