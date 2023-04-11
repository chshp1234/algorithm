package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//一个句子是由一些单词与它们之间的单个空格组成，且句子的开头和结尾没有多余空格。比方说，"Hello World" ，"HELLO" ，"hello world hello world" 都是句子。每个单词都 只 包含大写和小写英文字母。
//
//如果两个句子 sentence1 和 sentence2 ，可以通过往其中一个句子插入一个任意的句子（可以是空句子）而得到另一个句子，那么我们称这两个句子是 相似的 。比方说，sentence1 = "Hello my name is Jane" 且 sentence2 = "Hello Jane" ，我们可以往 sentence2 中 "Hello" 和 "Jane" 之间插入 "my name is" 得到 sentence1 。
//
//给你两个句子 sentence1 和 sentence2 ，如果 sentence1 和 sentence2 是相似的，请你返回 true ，否则返回 false 。
//
// 
//
//示例 1：
//
//输入：sentence1 = "My name is Haley", sentence2 = "My Haley"
//输出：true
//解释：可以往 sentence2 中 "My" 和 "Haley" 之间插入 "name is" ，得到 sentence1 。
//示例 2：
//
//输入：sentence1 = "of", sentence2 = "A lot of words"
//输出：false
//解释：没法往这两个句子中的一个句子只插入一个句子就得到另一个句子。
//示例 3：
//
//输入：sentence1 = "Eating right now", sentence2 = "Eating"
//输出：true
//解释：可以往 sentence2 的结尾插入 "right now" 得到 sentence1 。
//示例 4：
//
//输入：sentence1 = "Luky", sentence2 = "Lucccky"
//输出：false
// 
//
//提示：
//
//1 <= sentence1.length, sentence2.length <= 100
//sentence1 和 sentence2 都只包含大小写英文字母和空格。
//sentence1 和 sentence2 中的单词都只由单个空格隔开。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/sentence-similarity-iii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 句子相似性III {
    @Test
    fun 句子相似性III() {
        println(
            "句子相似性III:${
                areSentencesSimilar(
                    "of", "A lot of words"
                )
            }"
        )
    }
    fun areSentencesSimilar(sentence1: String, sentence2: String): Boolean {
        //双指针
        //如果两个句子长度相等，则直接判断内容是否相同
        //令第一个句子长度大于第二个句子，那么此时就必须在第二个句子前面，或者后面，或者中间插入其他单词使得两个句子内容完全相同
        //两个句子按“ ”分割出单词数组，并分别使用头尾两个指针指向两个单词数组的头尾
        //1.首先判断两个数组头部单词是否相同，不同时退出循环，否则，各头部指针加1
        //2.其次判断两个数组尾部单词是否相同，不同则退出循环，否则，各尾部指针减1
        //最后返回第二个单词数组的尾部指针是否小于头部指针即可
        //验证：因为只能在短句子的头部，尾部，或者中间插入其他子句子，并且短句子中的每个单词，都能匹配到长句子中的单词，才能使得两个句子相同
        //所以，当短句子的单词数组遍历完时，尾部指针必须小于头部指针，其中的单词，才能顺序匹配到长句子中的单词
        //并且，按照步骤遍历单词时，可以保证插入的子句子在头部，尾部或者中间，子句子内容即为长单词数组的头尾指针之间的内容
        if (sentence1.length == sentence2.length) {
            return sentence1 == sentence2
        }
        if (sentence1.length < sentence2.length) {
            return areSentencesSimilar(sentence2, sentence1)
        }

        val words1 = sentence1.split(" ")
        val words2 = sentence2.split(" ")
        var left1 = 0
        var left2 = 0
        while (left1 < words1.size && left2 < words2.size) {
            if (words1[left1] != words2[left2]) {
                break
            }
            left1++
            left2++
        }
        var right1 = words1.size - 1
        var right2 = words2.size - 1
        while (right1 >= left1 && right2 >= left2) {
            if (words1[right1] != words2[right2]) {
                break
            }
            right1--
            right2--
        }
        return right2 < left2
    }
}