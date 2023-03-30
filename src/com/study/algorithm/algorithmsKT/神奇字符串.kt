package com.study.algorithm.algorithmsKT

import java.util.*

//神奇字符串 s 仅由 '1' 和 '2' 组成，并需要遵守下面的规则：
//
//神奇字符串 s 的神奇之处在于，串联字符串中 '1' 和 '2' 的连续出现次数可以生成该字符串。
//s 的前几个元素是 s = "1221121221221121122……" 。如果将 s 中连续的若干 1 和 2 进行分组，可以得到 "1 22 11 2 1 22 1 22 11 2 11 22 ......" 。每组中 1 或者 2 的出现次数分别是 "1 2 2 1 1 2 1 2 2 1 2 2 ......" 。上面的出现次数正是 s 自身。
//
//给你一个整数 n ，返回在神奇字符串 s 的前 n 个数字中 1 的数目。
//
// 
//
//示例 1：
//
//输入：n = 6
//输出：3
//解释：神奇字符串 s 的前 6 个元素是 “122112”，它包含三个 1，因此返回 3 。
//示例 2：
//
//输入：n = 1
//输出：1
// 
//
//提示：
//
//1 <= n <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/magical-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 神奇字符串 {
    fun magicalString(n: Int): Int {
        //模拟,队列
        //根据题意,模拟出整个字符串S,再统计前n项共有几个'1'即可
        //这边使用队列进行模拟s,并用一个last记录上一个加入队列的数字,那么下一个加入的数字必然是另一个
        //因为加入的只能是1或2,那么将last = last ^ 3,即可令1转成2,或者2转成1

        val queue: Deque<Int> = ArrayDeque()

        //初始化122,我们令队列头部数字是2(第三个数),那么下标就是3
        queue.offer(2)
        //1的个数
        var count = 1
        //下标(1..n)
        var index = 3
        var last = 2

        while (index <= n) {
            //队列头部数字,该数字决定了下一个往队列里加的数字的次数
            val head = queue.poll()!!
            if (head == 1) {
                count++
            }
            //上一个添加的数字,如果是1,则转换成2,如果是2,则转换成1
            last = last xor 3
            for (i in 0 until head) {
                queue.offer(last)
            }
            index++
        }

        return count
    }
}