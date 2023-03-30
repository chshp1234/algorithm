package com.study.algorithm.algorithmsKT

import java.util.*

//给你一个字符串 s ，它仅包含字符 'a' 和 'b'​​​​ 。
//
//你可以删除 s 中任意数目的字符，使得 s 平衡 。当不存在下标对 (i,j) 满足 i < j ，且 s[i] = 'b' 的同时 s[j]= 'a' ，此时认为 s 是 平衡 的。
//
//请你返回使 s 平衡 的 最少 删除次数。
//
// 
//
//示例 1：
//
//输入：s = "aababbab"
//输出：2
//解释：你可以选择以下任意一种方案：
//下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
//下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。
//示例 2：
//
//输入：s = "bbaaaaabb"
//输出：2
//解释：唯一的最优解是删除最前面两个字符。
// 
//
//提示：
//
//1 <= s.length <= 105
//s[i] 要么是 'a' 要么是 'b'​ 。​
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 使字符串平衡的最少删除次数 {
    fun minimumDeletions(s: String): Int {
        //栈,动态规划
        //反向遍历s,记录每个字符出现的次数,加入栈中,这样正向遍历s时,就可以快速知道每个字符连续的个数
        //设endA:以A为结尾,最大需要删除的元素个数;endB:以B为结尾,需要删除的最大元素个数
        //同时分别:记录元素A连续个数;元素B连续个数,以及元素B之前A的个数(因为删除元素B后,就剩下元素A或者没有元素)
        //当char=a时,可以选择删除在endB中删除元素b,或者拼接到endA中
        //当char=b时,可以选择拼接到endA或endB中
        //最后返回endA和endB两种情况下,最少需要删除的个数
        val stack: Deque<Int> = LinkedList()
        var last = s[s.length - 1]
        var count = 1
        for (i in s.length - 2 downTo 0) {
            if (s[i] != last) {
                stack.push(count)
                last = s[i]
                count = 1
            } else {
                count++
            }
        }
        stack.push(count)

        val endA = intArrayOf(0, 0)
        val endB = intArrayOf(0, 0, 0)

        var index = 0
        while (index < s.length) {
            val c = s[index]
            val top = stack.pop()
            if (c == 'a') {
                if (endB[0] + endB[2] < endA[1]) {
                    //如果删除endB中的b元素,删除的个数小于endA的删除的个数
                    endA[0] = endB[1] + top
                    endA[1] = endB[0] + endB[2]
                } else {
                    //否则endA直接更新
                    endA[0] += top
                }
                //endB需要删除当前a元素
                endB[2] += top
            } else {
                if (endA[1] < endB[2]) {
                    //如果endA删除的个数小于endB删除的个数,则b拼接在endA中
                    endB[0] = top
                    endB[1] = endA[0]
                    endB[2] = endA[1]
                } else {
                    //否则b拼接在endB中
                    endB[0] += top
                }
                if (endB[0] + endB[2] < endA[1] + top) {
                    //如果删除endB中的b元素,删除的个数小于endA的删除的个数+当前b的个数
                    endA[0] = endB[1]
                    endA[1] = endB[0] + endB[2]
                } else {
                    //否则endA直接更新
                    endA[1] += top
                }
            }
            index += top
        }

        return Math.min(endA[1], endB[2])
    }
}