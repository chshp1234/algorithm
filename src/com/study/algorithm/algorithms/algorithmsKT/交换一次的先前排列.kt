package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//给你一个正整数数组 arr（可能存在重复的元素），请你返回可在 一次交换（交换两数字 arr[i] 和 arr[j] 的位置）后得到的、按字典序排列小于 arr 的最大排列。
//
//如果无法这么操作，就请返回原数组。
//
// 
//
//示例 1：
//
//输入：arr = [3,2,1]
//输出：[3,1,2]
//解释：交换 2 和 1
//示例 2：
//
//输入：arr = [1,1,5]
//输出：[1,1,5]
//解释：已经是最小排列
//示例 3：
//
//输入：arr = [1,9,4,6,7]
//输出：[1,7,4,6,9]
//解释：交换 9 和 7
// 
//
//提示：
//
//1 <= arr.length <= 104
//1 <= arr[i] <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/previous-permutation-with-one-swap
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 交换一次的先前排列 {
    @Test
    fun 交换一次的先前排列() {
        println(
            "交换一次的先前排列:${
                prevPermOpt1(
                    intArrayOf(1, 2, 3, 4)
                )
            }"
        )
    }

    fun prevPermOpt1(arr: IntArray): IntArray {
        //贪心
        //要使得按字典序比原数组小,那么要找出一个下标j和i使得arr[i]>arr[j],并且i<j
        //因为要使得字典序尽量大,所以,我们找到的i和j需要尽可能靠数组后面
        //用first和second代表最大和次大的元素下标
        //如果当前元素大于等于记录的最大元素,则更新最大和次大元素,并保留原最大和次大元素,以免后续无更新
        //如果当前元素小于记录的最大元素,则再将当前元素和次大元素比较
        //如果比次大元素大,则更新次大元素
        //如果比次大元素小,则找到一个更靠后的方案,将最大元素下标取次大元素的下标,并将次大元素下标更新为当前元素下标
        //最后如果first!=second,说明找到了不同的,可以更新的两个下标,交换两个下标的值,否则再判断之前记录保留的两个下标是否相同,同样如果不相同,则交换下标
        var resF = 0
        var resS = 0

        var index = 0
        while (index < arr.size - 1) {
            if (arr[index] <= arr[index + 1]) {
                index++
            } else {
                break
            }
        }
        var first = index
        var second = index
        index++
        while (index < arr.size) {
            if (arr[index] >= arr[first]) {
                if (first != second) {
                    resF = first
                    resS = second
                }
                first = index
                second = index
            } else {
                if (arr[index] > arr[second]) {
                    second = index
                } else if (arr[index] < arr[second]) {
                    first = second
                    second = index
                }
            }
            index++
        }
        if (first != second) {
            resF = first
            resS = second
        }
        return intArrayOf(*arr).also {
            if (resF != resS) {
                it[resF] = it[resF] xor it[resS]
                it[resS] = it[resF] xor it[resS]
                it[resF] = it[resF] xor it[resS]
            }
        }
    }
}