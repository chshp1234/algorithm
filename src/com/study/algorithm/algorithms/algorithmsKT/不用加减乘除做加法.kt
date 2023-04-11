package com.study.algorithm.algorithms.algorithmsKT

import kotlin.test.Test

//写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
//
// 
//
//示例:
//
//输入: a = 1, b = 1
//输出: 2
// 
//
//提示：
//
//a, b 均可能是负数或 0
//结果不会溢出 32 位整数
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 不用加减乘除做加法 {

    @Test
    fun 不用加减乘除做加法() {
        print(0x5555_5555)
        System.out.printf("不用加减乘除做加法:%d+%d=%d", 2, 3, add(2, 3))
    }

    fun add(a: Int, b: Int): Int {
        //不能用+-*/，那么就用&|~^
        //首先，两数异或，相当于不带进位的相加，那么我们可以用异或进行相加，同时注意处理进位问题

        //两个数
        var left = a
        var right = b

        //进位
        var step = 0
        //结果
        var result = 0

        //当前位
        var bit = 1

        //当两个数都不为0时
        while (left != 0 && right != 0) {
            //分别取出两个数在当前bit位上的数
            val bitL = left and bit
            val bitR = right and bit

            //异或计算不带进位的相加，或把值赋值到结果中
            result = result or (bitL xor bitR xor step)

            //计算进位，当3个数中有两个数是1时，就需要进行进位操作
            step = (bitL and bitR) or (bitL and step) or (bitR and step)

            //将两数当前bit位置0
            left = left and bit.inv()
            right = right and bit.inv()

            //step进位与bit左移一位
            step = step shl 1
            bit = bit shl 1
        }

        //同理，再对两个数中剩下的数进行计算（step为0时其实就不用计算进位了，可以直接跳出）
        while (left != 0) {
            val bitL = left and bit
            result = result or (bitL xor step)
            step = bitL and step
            left = left and bit.inv()
            step = step shl 1
            bit = bit shl 1
        }
        while (right != 0) {
            val bitR = right and bit
            result = result or (bitR xor step)
            step = bitR and step
            right = right and bit.inv()
            step = step shl 1
            bit = bit shl 1
        }
        //最后再计算进位
        result = result or step
        return result
    }
}