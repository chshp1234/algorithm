package com.study.algorithm.algorithmsKT

import java.util.*

//这个问题和“最多能完成排序的块”相似，但给定数组中的元素可以重复，输入数组最大长度为2000，其中的元素最大为10**8。
//
//arr是一个可能包含重复元素的整数数组，我们将这个数组分割成几个“块”，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
//
//我们最多能将数组分成多少块？
//
//示例 1:
//
//输入: arr = [5,4,3,2,1]
//输出: 1
//解释:
//将数组分成2块或者更多块，都无法得到所需的结果。
//例如，分成 [5, 4], [3, 2, 1] 的结果是 [4, 5, 1, 2, 3]，这不是有序的数组。
//示例 2:
//
//输入: arr = [2,1,3,4,4]
//输出: 4
//解释:
//我们可以把它分成两块，例如 [2, 1], [3, 4, 4]。
//然而，分成 [2, 1], [3], [4], [4] 可以得到最多的块数。
//注意:
//
//arr的长度在[1, 2000]之间。
//arr[i]的大小在[0, 10**8]之间。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/max-chunks-to-make-sorted-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 最多能完成排序的块II {
    fun maxChunksToSorted2(arr: IntArray): Int {
        //单调栈
        //分割的各个区块中,前一个区块的最大值小于等于后一个区块的最小值
        //我们用两个单调递增栈maxStack,minStack,维护一个区块中的最大值和最小值
        //当遍历一个元素num时:
        //1.num<minStack.top,说明此值比当前区间最小值还要小,那么需要进一步判断,弹出栈顶元素
        //2.num>=maxStack.top,说明这个元素比当前区间最大值还要大,那么可以加入一个新区间(新区间的最大最小值实时维护)
        //3.这个元素的值在区间内,不进行操作(融入当前区间)
        //最后返回栈的大小即可

        //单调递增栈,维护区间最大值和最小值
        val maxStack: Deque<Int> = LinkedList()
        val minStack: Deque<Int> = LinkedList()
        maxStack.push(arr[0])
        minStack.push(arr[0])
        for (i in 1 until arr.size) {

            //实时维护的区间最大最小值
            var max = maxStack.peekFirst()
            var min = max

            do {

                val maxTop = maxStack.pop()
                val minTop = minStack.pop()

                if (arr[i] < minTop) {
                    //更新最小值
                    min = arr[i]
                } else if (arr[i] >= maxTop) {
                    //因为用的是pop(),所以应将当前区间重新加入
                    maxStack.push(maxTop)
                    minStack.push(minTop)

                    //更新最大值最小值
                    max = Math.max(max, arr[i])
                    min = Math.min(min, arr[i])
                    break
                } else {
                    break
                }

            } while (!maxStack.isEmpty())

            //加入当前计算的区间最大值和最小值
            maxStack.push(max)
            minStack.push(min)

        }
        return maxStack.size
    }
}