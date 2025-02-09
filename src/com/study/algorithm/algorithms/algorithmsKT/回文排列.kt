package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
//
//回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
//
//回文串不一定是字典当中的单词。
//
//
//
//示例1：
//
//输入："tactcoa"
//输出：true（排列有"tacocat"、"atcocta"，等等）
class 回文排列 {
    @Test
    fun 回文排列() {
        println("回文排列:${canPermutePalindrome("tactcoa")}")
    }

    fun canPermutePalindrome(s: String): Boolean {

        //哈希表，分类讨论
        //1.先用哈希表将每一个字符出现的次数记录下来
        //2.分类讨论，根据字符串长度奇偶性进行判断：
        //  2.1.偶数：只有当所有字符出现的次数都为偶数，可以重新排列成回文串
        //  2.2.奇数：只有一个字符出现的次数维奇数（回文串中间的字符），并且其他字符出现的次数都为偶数时，才可以重新排列成回文串
        if (s.isEmpty()) {
            return true
        }
        val map = HashMap<Char, Int>()
        for (c in s) {
            map[c] = map[c]?.let { it + 1 } ?: 1
        }

        val entries = map.entries
        if (s.length % 2 == 0) {
            for (entry in entries) {
                if (entry.value % 2 != 0) {
                    return false
                }
            }
        } else {
            var odd = 0
            for (entry in entries) {
                if (entry.value % 2 != 0) {
                    if (odd != 0) {
                        return false
                    }
                    odd = 1
                }
            }
        }

        return true
    }
}