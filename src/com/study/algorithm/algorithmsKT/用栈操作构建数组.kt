package com.study.algorithm.algorithmsKT

//给你一个数组 target 和一个整数 n。每次迭代，需要从  list = { 1 , 2 , 3 ..., n } 中依次读取一个数字。
//
//请使用下述操作来构建目标数组 target ：
//
//"Push"：从 list 中读取一个新元素， 并将其推入数组中。
//"Pop"：删除数组中的最后一个元素。
//如果目标数组构建完成，就停止读取更多元素。
//题目数据保证目标数组严格递增，并且只包含 1 到 n 之间的数字。
//
//请返回构建目标数组所用的操作序列。如果存在多个可行方案，返回任一即可。
//
// 
//
//示例 1：
//
//输入：target = [1,3], n = 3
//输出：["Push","Push","Pop","Push"]
//解释：
//读取 1 并自动推入数组 -> [1]
//读取 2 并自动推入数组，然后删除它 -> [1]
//读取 3 并自动推入数组 -> [1,3]
//示例 2：
//
//输入：target = [1,2,3], n = 3
//输出：["Push","Push","Push"]
//示例 3：
//
//输入：target = [1,2], n = 4
//输出：["Push","Push"]
//解释：只需要读取前 2 个数字就可以停止。
// 
//
//提示：
//
//1 <= target.length <= 100
//1 <= n <= 100
//1 <= target[i] <= n
//target 严格递增
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/build-an-array-with-stack-operations
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 用栈操作构建数组 {
    fun buildArray(target: IntArray, n: Int): List<String> {
        //模拟栈
        //数字严格按照1...n进栈，那么遍历每个数字时，我们首先都有个“Push”操作
        //然后判断当前target下标的数字，是否与进栈的数字相同，若相同，则下标加一，继续判断下一个数字，若不相同，则这个数字将要出栈，有个“Pop”操作
        val result = mutableListOf<String>()
        var index = 0
        var num = 1
        while (index < target.size) {
            result.add("push")
            if (num != target[index]) {
                result.add("Pop")
            } else {
                index++
            }
            num++
        }

        return result
    }
}