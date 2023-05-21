package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//给出基数为 -2 的两个数 arr1 和 arr2，返回两数相加的结果。
//
//数字以 数组形式 给出：数组由若干 0 和 1 组成，按最高有效位到最低有效位的顺序排列。例如，arr = [1,1,0,1] 表示数字 (-2)^3 + (-2)^2 + (-2)^0 = -3。数组形式 中的数字 arr 也同样不含前导零：即 arr == [0] 或 arr[0] == 1。
//
//返回相同表示形式的 arr1 和 arr2 相加的结果。两数的表示形式为：不含前导零、由若干 0 和 1 组成的数组。
//
// 
//
//示例 1：
//
//输入：arr1 = [1,1,1,1,1], arr2 = [1,0,1]
//输出：[1,0,0,0,0]
//解释：arr1 表示 11，arr2 表示 5，输出表示 16 。
//示例 2：
//
//输入：arr1 = [0], arr2 = [0]
//输出：[0]
//示例 3：
//
//输入：arr1 = [0], arr2 = [1]
//输出：[1]
// 
//
//提示：
//
//1 <= arr1.length, arr2.length <= 1000
//arr1[i] 和 arr2[i] 都是 0 或 1
//arr1 和 arr2 都没有前导0
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/adding-two-negabinary-numbers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 负二进制数相加 {

    @Test
    fun 负二进制数相加() {
        println(
            "负二进制数相加:${
                addNegabinary(
                    intArrayOf(1, 0, 1), intArrayOf(1)
                )
            }"
        )
    }

    fun addNegabinary(arr1: IntArray, arr2: IntArray): IntArray {
        //数学,模拟
        //因为是-2进制,所以当偶数位得到的数是正数,奇数位得到的数是负数
        //那么当相同位上的数都是1时,相加得到的数将和下一位上的数互补
        //比如(-2)^0+(-2)^0=-(-2)^1
        //所以,此时我们可另进位step=-1
        //那么,当下一位上,只有一个数组中的数是1,就会和上一个进位的-1相加后得到0了
        //但如果下一位上两个数都是0,和上一个进位的-1相加后得到的数是-1,又因为二进制表示的数为1或0,不可逆为-1,所以此时另这一位为1,那么下一个进位上就需要进行补充,此时进位为1
        //比如第二位数,进位是-1,但两个数组中对应的数都是0,那么理应+(-(-2)^1),但进制数只能是1,
        //所以我们另进位为1,再另下一位的进位为1,那么(-2)^2+(-2)^1=(-(-2)^1),就可以使得进制是1的时候两边想等
        //
        //从最低位开始两两相加,并记录相加后的进位step
        //当arr1[i1] + arr2[i2] + step:
        // 3:step=-1,res[i]=1
        // 2:step=-1,res[i]=0
        // 1:step= 1,res[i]=0
        // 0:step= 0,res[i]=0
        //-1:step= 1,res[i]=1
        //当一个数组遍历结束时,在遍历另一个数组,直至结束
        //如果两个数组都遍历结束,且进位为-1,那么直接在头部加上两个1
        //最后去除结果数组头部的0,并输出即可
        if (arr1.size < arr2.size) {
            return addNegabinary(arr2, arr1)
        }
        var i1 = arr1.size - 1
        var i2 = arr2.size - 1
        var step = 0
        val res = mutableListOf<Int>()
        while (i2 >= 0) {
            when ((arr1[i1] + arr2[i2] + step)) {
                3 -> {
                    res.add(0, 1)
                    step = -1
                }

                2 -> {
                    res.add(0, 0)
                    step = -1
                }

                1 -> {
                    res.add(0, 1)
                    step = 0
                }

                0 -> {
                    res.add(0, 0)
                    step = 0
                }

                -1 -> {
                    res.add(0, 1)
                    step = 1
                }
            }
            i1--
            i2--
        }

        while (i1 >= 0) {
            when ((arr1[i1] + step)) {
                3 -> {
                    res.add(0, 1)
                    step = -1
                }

                2 -> {
                    res.add(0, 0)
                    step = -1
                }

                1 -> {
                    res.add(0, 1)
                    step = 0
                }

                0 -> {
                    res.add(0, 0)
                    step = 0
                }

                -1 -> {
                    res.add(0, 1)
                    step = 1
                }
            }
            i1--
        }
        if (step == -1) {
            res.add(0, 1)
            res.add(0, 1)
        }
        while (res.size > 1 && res[0] == 0) {
            res.removeAt(0)
        }

        return res.toIntArray()
    }
}