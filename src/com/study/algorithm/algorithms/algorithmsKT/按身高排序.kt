package com.study.algorithm.algorithms.algorithmsKT

//给你一个字符串数组 names ，和一个由 互不相同 的正整数组成的数组 heights 。两个数组的长度均为 n 。
//
//对于每个下标 i，names[i] 和 heights[i] 表示第 i 个人的名字和身高。
//
//请按身高 降序 顺序返回对应的名字数组 names 。
//
// 
//
//示例 1：
//
//输入：names = ["Mary","John","Emma"], heights = [180,165,170]
//输出：["Mary","Emma","John"]
//解释：Mary 最高，接着是 Emma 和 John 。
//示例 2：
//
//输入：names = ["Alice","Bob","Bob"], heights = [155,185,150]
//输出：["Bob","Alice","Bob"]
//解释：第一个 Bob 最高，然后是 Alice 和第二个 Bob 。
// 
//
//提示：
//
//n == names.length == heights.length
//1 <= n <= 103
//1 <= names[i].length <= 20
//1 <= heights[i] <= 105
//names[i] 由大小写英文字母组成
//heights 中的所有值互不相同
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/sort-the-people
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 按身高排序 {
    fun sortPeople(names: Array<String>, heights: IntArray): Array<String> {
        //排序
        //将index下标和heights身高做关联
        //然后将数组按身高从高到低排序
        //最后将排序后,对应的下标处对应的人员依次输出出来
        val map = Array(names.size) {
            arrayOf(it, heights[it])
        }.apply {
            sortByDescending {
                it[1]
            }
        }
        return Array(names.size) {
            names[map[it][0]]
        }
    }
}