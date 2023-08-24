package com.study.algorithm.algorithms.algorithmsKT

//给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
//
//美式键盘 中：
//
//第一行由字符 "qwertyuiop" 组成。
//第二行由字符 "asdfghjkl" 组成。
//第三行由字符 "zxcvbnm" 组成。
//American keyboard
//
//
//
//示例 1：
//
//输入：words = ["Hello","Alaska","Dad","Peace"]
//输出：["Alaska","Dad"]
//示例 2：
//
//输入：words = ["omk"]
//输出：[]
//示例 3：
//
//输入：words = ["adsdf","sfd"]
//输出：["adsdf","sfd"]
//
//
//提示：
//
//1 <= words.length <= 20
//1 <= words[i].length <= 100
//words[i] 由英文字母（小写和大写字母）组成
class 键盘行 {

    //哈希表
    //将每一行的大写和小写字母加入哈希表中，并标出其行数
    //再遍历字符串数组，判断每个字符串中的每个字符是否都在键盘的同一行即可

    val map = HashMap<Char, Int>()

    init {
        for (c in "qwertyuiopQWERTYUIOP") {
            map[c] = 0
        }
        for (c in "asdfghjklASDFGHJKL") {
            map[c] = 1
        }
        for (c in "zxcvbnmZXCVBNM") {
            map[c] = 2
        }
    }

    fun findWords(words: Array<String>): Array<String> {
        val res = ArrayList<String>()

        OUT@
        for (word in words) {
            val i = map[word[0]]
            for (c in 1 until word.length) {
                if (map[word[c]] != i) {
                    continue@OUT
                }
            }
            res.add(word)
        }

        return res.toTypedArray()
    }
}