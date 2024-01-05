package com.study.algorithm.algorithms.algorithmsKT

import java.util.*

//有 n 个人排成一个队列，从左到右 编号为 0 到 n - 1 。给你以一个整数数组 heights ，每个整数 互不相同，heights[i] 表示第 i 个人的高度。
//
//一个人能 看到 他右边另一个人的条件是这两人之间的所有人都比他们两人 矮 。更正式的，第 i 个人能看到第 j 个人的条件是 i < j 且 min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]) 。
//
//请你返回一个长度为 n 的数组 answer ，其中 answer[i] 是第 i 个人在他右侧队列中能 看到 的 人数 。
//
//
//
//示例 1：
//
//
//
//输入：heights = [10,6,8,5,11,9]
//输出：[3,1,2,1,1,0]
//解释：
//第 0 个人能看到编号为 1 ，2 和 4 的人。
//第 1 个人能看到编号为 2 的人。
//第 2 个人能看到编号为 3 和 4 的人。
//第 3 个人能看到编号为 4 的人。
//第 4 个人能看到编号为 5 的人。
//第 5 个人谁也看不到因为他右边没人。
//示例 2：
//
//输入：heights = [5,1,2,3,10]
//输出：[4,1,1,1,0]
//
//
//提示：
//
//n == heights.length
//1 <= n <= 105
//1 <= heights[i] <= 105
//heights 中所有数 互不相同 。
class 队列中可以看到的人数 {
    //单调栈
    //由于i要看到j（j默认在i右侧），必须满足heights[i..j]之间的所有元素，均小于heights[i]和heights[j]
    //那么可以得出：
    //1.如果heights[i]<heights[j]，j之后的所有元素，i都将看不到（先不管i能不能看见j）
    //2.如果heights[i]>heights[j]，i有机会看到j右侧+1的元素（如果i可以看见j）
    //而对于上述第2点：
    //1.当i和j之间有其他元素k，使得heights[k]大于heights[j]时，那么将看不到j，并且其右侧所有小于heights[k]的元素也都将看不到，所以对于第2点，默认在可以看见的情况下
    //2.“机会”说的就是heights[j+1]>heights[j]，那么i肯定看得见j+1
    //由此，从数组右侧往左进行遍历，并维护一个单调递减栈。
    //因为栈是单调递减的，所以，对于第2点中的“如果i可以看见j”将始终成立，就不用考虑其余情况了（因为i和j之间的元素肯定小于i和j）。
    //而对于第1点，我们在维护单调栈的时候，就已经满足了，即当heights[i]<heights[j]退出栈的遍历。
    //最后再将当前元素heights[j]加入栈中，开始下一轮判断，直到所有元素判断完毕即可。
    fun canSeePersonsCount(heights: IntArray): IntArray {
        val deque: Deque<Int> = LinkedList()
        val res = IntArray(heights.size)
        //反向遍历，并维护单调栈
        for (i in heights.size - 1 downTo 0) {
            while (!deque.isEmpty()) {
                //因为单调栈的性质，如果栈不为空，那么i看到能看到j
                res[i]++
                if (heights[i] > deque.peek()) {
                    //将j出栈
                    deque.pop()
                } else {
                    break
                }
            }
            //此时栈为空，或者heights[i]<heights[j]。不管如何，将i加入栈中
            deque.push(heights[i])
        }
        return res
    }
}