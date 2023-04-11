package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//给你一个整数 n ，以二进制字符串的形式返回该整数的 负二进制（base -2）表示。
//
//注意，除非字符串就是 "0"，否则返回的字符串中不能含有前导零。
//
// 
//
//示例 1：
//
//输入：n = 2
//输出："110"
//解释：(-2)2 + (-2)1 = 2
//示例 2：
//
//输入：n = 3
//输出："111"
//解释：(-2)2 + (-2)1 + (-2)0 = 3
//示例 3：
//
//输入：n = 4
//输出："100"
//解释：(-2)2 = 4
// 
//
//提示：
//
//0 <= n <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/convert-to-base-2
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 二进制转换 {

    @Test
    fun 二进制转换() {
        println(
            "二进制转换:${
                baseNeg2(
                    10
                )
            }"
        )
    }

    fun baseNeg2(n: Int): String {
        //转换成负二进制后时,也可以像转换成二进制那样每次/2,求余数
        //因为对于二进制,余数只能是0和1,所以当对num进行num % -2时,如果:
        //余数是0:结果拼接0
        //余数是1:结果拼接1
        //余数是-1:令num--,结果拼接1
        //最后num /= -2
        //上述操作结束之后，将字符串翻转之后得到「负二进制」数。
        if (n == 0) {
            return "0"
        }
        val list = StringBuilder()
        var num = n
        while (num != 0) {
            when (num % -2) {
                0 -> list.append('0')
                1 -> list.append('1')
                -1 -> {
                    list.append('1')
                    num -= 1
                }
            }
            num /= -2
        }
        return list.reverse().toString()
    }
}