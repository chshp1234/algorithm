package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test
import java.util.*

//给你两个字符串 s 和 t ，请你找出 s 中的非空子串的数目，这些子串满足替换 一个不同字符 以后，是 t 串的子串。换言之，请你找到 s 和 t 串中 恰好 只有一个字符不同的子字符串对的数目。
//
//比方说， "computer" and "computation" 只有一个字符不同： 'e'/'a' ，所以这一对子字符串会给答案加 1 。
//
//请你返回满足上述条件的不同子字符串对数目。
//
//一个 子字符串 是一个字符串中连续的字符。
//
// 
//
//示例 1：
//
//输入：s = "aba", t = "baba"
//输出：6
//解释：以下为只相差 1 个字符的 s 和 t 串的子字符串对：
//("aba", "baba")
//("aba", "baba")
//("aba", "baba")
//("aba", "baba")
//("aba", "baba")
//("aba", "baba")
//加粗部分分别表示 s 和 t 串选出来的子字符串。
//示例 2：
//输入：s = "ab", t = "bb"
//输出：3
//解释：以下为只相差 1 个字符的 s 和 t 串的子字符串对：
//("ab", "bb")
//("ab", "bb")
//("ab", "bb")
//加粗部分分别表示 s 和 t 串选出来的子字符串。
//示例 3：
//输入：s = "a", t = "a"
//输出：0
//示例 4：
//
//输入：s = "abe", t = "bbc"
//输出：10
// 
//
//提示：
//
//1 <= s.length, t.length <= 100
//s 和 t 都只包含小写英文字母。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/count-substrings-that-differ-by-one-character
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 统计只差一个字符的子串数目 {
    @Test
    fun 统计只差一个字符的子串数目() {
        println(
            "统计只差一个字符的子串数目:${
                countSubstrings(
                    "aba", "baba"
                )
            }"
        )
    }

    fun countSubstrings(s: String, t: String): Int {
        //队列,枚举
        //设dp[r][c][l]:以s[r],t[c]为子串起始下标,且长度为l的子串,不同字符的数目
        //那么只有当dp[r][c][l]=1时,说明两个子串只相差一个字符不同,那么可以计算进结果中
        //如果dp[r][c][l]=0,那么只有当s[r+l]!=t[c+l]时,说明dp[r][c][l+1]=1,否则如果dp[r][c][l+1]=0,继续计算dp[r][c][l+2]
        //如果dp[r][c][l]=1,那么只有当s[r+l]==t[c+l]时,说明dp[r][c][l+1]=1,否则,说明dp[r][c][l+1]=2,那么后续子串也将不用计算了
        //所以为了优化枚举,可以使用一个队列,每一项存储r,c,l以及count,count就代表当前两个子串不相同字符的数目
        //只有当count=1或count=0时,才继续计算下一个长度l+1的子串,这样就可以略去不相同字符数量大于1的子串
        if (s.length > t.length) {
            return countSubstrings(t, s)
        }
        var res = 0
        val queue: Queue<IntArray> = LinkedList()
        for (r in s.indices) {
            for (c in t.indices) {
                if (s[r] == t[c]) {
                    queue.offer(intArrayOf(r, c, 1, 0))
                } else {
                    queue.offer(intArrayOf(r, c, 1, 1))
                    res++
                }
            }
        }
        var item: IntArray
        while (!queue.isEmpty()) {
            item = queue.poll()
            if (item[0] + item[2] == s.length || item[1] + item[2] == t.length) {
                continue
            }
            if (s[item[0] + item[2]] == t[item[1] + item[2]]) {
                if (item[3] == 0) {
                    item[2]++
                    queue.offer(item)
                } else {
                    item[2]++
                    queue.offer(item)
                    res++
                }
            } else {
                if (item[3] == 0) {
                    item[2]++
                    item[3] = 1
                    res++
                    queue.offer(item)
                }
            }
        }
        return res
    }
}