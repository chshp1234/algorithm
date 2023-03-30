package com.study.algorithm.algorithmsKT

import java.util.*

class 最小栈 {

    //要使得操作的事件复杂度都是O(1)，那么可以使用一个辅助容器实时维护当前数组中最小的元素
    //每次加入新数据时，将此数据元素和辅助容器中当前数组下的最小元素进行比较并获取最小值后，再加入辅助容器中即可

    //哈希表存，当前数组大小下的最小元素(使用另一个栈亦可实时维护当前数组下的最小元素)
    val min = HashMap<Int, Int>()
    val stack = LinkedList<Int>()
    var size = 0

    fun push(`val`: Int) {
        stack.push(`val`)
        min[size + 1] = Math.min(min[size++] ?: `val`, `val`)
    }

    fun pop() {
        min.remove(size--)
    }

    fun top(): Int {
        return stack.peekFirst()
    }

    fun getMin(): Int {
        return min[size] ?: 0
    }

}