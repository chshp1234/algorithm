package com.study.algorithm.algorithms.algorithmsKT

import java.util.*

//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
//
// 
//
//示例 1：
//
//输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
//示例 2：
//
//输入：arr = [0,1,2,1], k = 1
//输出：[0]
// 
//
//限制：
//
//0 <= k <= arr.length <= 10000
//0 <= arr[i] <= 10000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 最小的k个数 {
    fun getLeastNumbers(arr: IntArray, k: Int): IntArray {
        //排序
        //利用小顶堆的特性，建完堆后，直接取出堆中前k个数即可，相比排序将更有效率
        val len = arr.size
        val result = IntArray(k)
        val queue = PriorityQueue<Int>(len)
        for (i in 0 until len) {
            queue.offer(arr[i])
        }
        for (i in 0 until k) {
            result[i] = queue.poll()
        }
        return result
    }
}