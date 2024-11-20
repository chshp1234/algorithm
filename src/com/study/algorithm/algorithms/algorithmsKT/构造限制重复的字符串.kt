package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//给你一个字符串 s 和一个整数 repeatLimit ，用 s 中的字符构造一个新字符串 repeatLimitedString ，使任何字母 连续 出现的次数都不超过 repeatLimit 次。你不必使用 s 中的全部字符。
//
//返回 字典序最大的 repeatLimitedString 。
//
//如果在字符串 a 和 b 不同的第一个位置，字符串 a 中的字母在字母表中出现时间比字符串 b 对应的字母晚，则认为字符串 a 比字符串 b 字典序更大 。如果字符串中前 min(a.length, b.length) 个字符都相同，那么较长的字符串字典序更大。
//
//
//
//示例 1：
//
//输入：s = "cczazcc", repeatLimit = 3
//输出："zzcccac"
//解释：使用 s 中的所有字符来构造 repeatLimitedString "zzcccac"。
//字母 'a' 连续出现至多 1 次。
//字母 'c' 连续出现至多 3 次。
//字母 'z' 连续出现至多 2 次。
//因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
//该字符串是字典序最大的 repeatLimitedString ，所以返回 "zzcccac" 。
//注意，尽管 "zzcccca" 字典序更大，但字母 'c' 连续出现超过 3 次，所以它不是一个有效的 repeatLimitedString 。
//示例 2：
//
//输入：s = "aababab", repeatLimit = 2
//输出："bbabaa"
//解释：
//使用 s 中的一些字符来构造 repeatLimitedString "bbabaa"。
//字母 'a' 连续出现至多 2 次。
//字母 'b' 连续出现至多 2 次。
//因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
//该字符串是字典序最大的 repeatLimitedString ，所以返回 "bbabaa" 。
//注意，尽管 "bbabaaa" 字典序更大，但字母 'a' 连续出现超过 2 次，所以它不是一个有效的 repeatLimitedString 。
//
//
//提示：
//
//1 <= repeatLimit <= s.length <= 105
//s 由小写英文字母组成
class 构造限制重复的字符串 {

    @Test
    fun 构造限制重复的字符串() {
        println(
            "查找用户活跃分钟数:${
                repeatLimitedString("aababab", 2)
            }"
        )
    }

    fun repeatLimitedString(s: String, repeatLimit: Int): String {
        //计数，贪心，双指针
        //因为字符只包含小写字母，那么可以用一个26大小的数组统计每个字符出现的次数
        //使用左右连个指针l，r
        //l代表的字符小于r，那么当r指向的字符重复添加repeatLimit次后，下一个就将指向l
        //如果counter[r] <= repeatLimit，将counter[r]数量的字符全加入，并将r指向下一个counter[r]>0的字符
        //如果counter[r] > repeatLimit，那么只能添加repeatLimit个r指向的字符，寻找r左边第一个counter[l]>0的字符，添加一个
        //直到l<0时，说明无法继续添加。返回结果。
        var r = 25
        var l = 25
        val counter = IntArray(26)
        for (c in s) {
            counter[c - 'a']++
        }
        val res = StringBuilder()

        while (l >= 0) {
            if (counter[r] <= repeatLimit) {
                repeat(counter[r]) { res.append(r.toChar() + 'a'.code) }
                r--
                while (r >= 0 && counter[r] == 0) {
                    r--
                }
                l = r
            } else {
                repeat(repeatLimit) { res.append(r.toChar() + 'a'.code) }
                counter[r] -= repeatLimit
                while ((l >= 0 && counter[l] == 0) || l == r) {
                    l--
                }
                if (l >= 0) {
                    res.append(l.toChar() + 'a'.code)
                    counter[l]--
                }
            }
        }

        return res.toString()
    }
}