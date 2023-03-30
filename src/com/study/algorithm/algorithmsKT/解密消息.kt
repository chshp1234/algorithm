package com.study.algorithm.algorithmsKT

import org.junit.Test

//给你字符串 key 和 message ，分别表示一个加密密钥和一段加密消息。解密 message 的步骤如下：
//
//使用 key 中 26 个英文小写字母第一次出现的顺序作为替换表中的字母 顺序 。
//将替换表与普通英文字母表对齐，形成对照表。
//按照对照表 替换 message 中的每个字母。
//空格 ' ' 保持不变。
//例如，key = "happy boy"（实际的加密密钥会包含字母表中每个字母 至少一次），据此，可以得到部分对照表（'h' -> 'a'、'a' -> 'b'、'p' -> 'c'、'y' -> 'd'、'b' -> 'e'、'o' -> 'f'）。
//返回解密后的消息。
//
// 
//
//示例 1：
//
//
//
//输入：key = "the quick brown fox jumps over the lazy dog", message = "vkbs bs t suepuv"
//输出："this is a secret"
//解释：对照表如上图所示。
//提取 "the quick brown fox jumps over the lazy dog" 中每个字母的首次出现可以得到替换表。
//示例 2：
//
//
//
//输入：key = "eljuxhpwnyrdgtqkviszcfmabo", message = "zwx hnfx lqantp mnoeius ycgk vcnjrdb"
//输出："the five boxing wizards jump quickly"
//解释：对照表如上图所示。
//提取 "eljuxhpwnyrdgtqkviszcfmabo" 中每个字母的首次出现可以得到替换表。
// 
//
//提示：
//
//26 <= key.length <= 2000
//key 由小写英文字母及 ' ' 组成
//key 包含英文字母表中每个字符（'a' 到 'z'）至少一次
//1 <= message.length <= 2000
//message 由小写英文字母和 ' ' 组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/decode-the-message
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 解密消息 {
    @Test
    fun 解密消息() {
        println(
            "解密消息:${decodeMessage("the quick brown fox jumps over the lazy dog", "vkbs bs t suepuv")}"
        )
    }

    fun decodeMessage(key: String, message: String): String {
        //哈希表
        //用哈希表记录每个字符第一次出现的位置（在26个字母的顺序中）
        //如果一个字母已经出现过则跳过，如果字符是' '也跳过
        val map = IntArray(26) {
            -1
        }
        var index = 0
        for (i in 0 until 26) {
            while (key[index] == ' ' || map[key[index] - 'a'] != -1) {
                index++
            }
            map[key[index] - 'a'] = i
        }
        val res = message.toCharArray()

        for (i in message.indices) {
            if (res[i] != ' ') {
                res[i] = 'a' + map[res[i] - 'a']
            }
        }

        return String(res)
    }
}