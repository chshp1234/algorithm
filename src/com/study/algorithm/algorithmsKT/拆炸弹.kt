package com.study.algorithm.algorithmsKT

import org.junit.Test

//你有一个炸弹需要拆除，时间紧迫！你的情报员会给你一个长度为 n 的 循环 数组 code 以及一个密钥 k 。
//
//为了获得正确的密码，你需要替换掉每一个数字。所有数字会 同时 被替换。
//
//如果 k > 0 ，将第 i 个数字用 接下来 k 个数字之和替换。
//如果 k < 0 ，将第 i 个数字用 之前 k 个数字之和替换。
//如果 k == 0 ，将第 i 个数字用 0 替换。
//由于 code 是循环的， code[n-1] 下一个元素是 code[0] ，且 code[0] 前一个元素是 code[n-1] 。
//
//给你 循环 数组 code 和整数密钥 k ，请你返回解密后的结果来拆除炸弹！
//
// 
//
//示例 1：
//
//输入：code = [5,7,1,4], k = 3
//输出：[12,10,16,13]
//解释：每个数字都被接下来 3 个数字之和替换。解密后的密码为 [7+1+4, 1+4+5, 4+5+7, 5+7+1]。注意到数组是循环连接的。
//示例 2：
//
//输入：code = [1,2,3,4], k = 0
//输出：[0,0,0,0]
//解释：当 k 为 0 时，所有数字都被 0 替换。
//示例 3：
//
//输入：code = [2,4,9,3], k = -2
//输出：[12,5,6,13]
//解释：解密后的密码为 [3+9, 2+3, 4+2, 9+4] 。注意到数组是循环连接的。如果 k 是负数，那么和为 之前 的数字。
// 
//
//提示：
//
//n == code.length
//1 <= n <= 100
//1 <= code[i] <= 100
//-(n - 1) <= k <= n - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/defuse-the-bomb
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 拆炸弹 {
    @Test
    fun 拆炸弹() {
        System.out.printf("拆炸弹:${decrypt(intArrayOf(5, 7, 1, 4), 3)}")
    }

    fun decrypt(code: IntArray, k: Int): IntArray {
        //滑动窗口
        //根据k的正负，判断初始left和right的区间，并计算code[left..right]的区间和windowSum,令result[0]=windowSum
        //遍历code时，使得窗口移动，窗口和减去左区间原始值windowSum -= code[(left++) % code.size]，窗口和加上右区间新值windowSum += code[(++right) % code.size]，再更新结果数组

        var left: Int
        var right: Int
        if (k == 0) {
            return IntArray(code.size) {
                0
            }
        } else if (k > 0) {
            left = 1
            right = k
        } else {
            left = code.size + k
            right = code.size - 1
        }

        val result = IntArray(code.size)

        var windowSum = 0
        for (i in left..right) {
            windowSum += code[i]
        }
        result[0] = windowSum

        for (i in 1 until code.size) {
            windowSum -= code[(left++) % code.size]
            windowSum += code[(++right) % code.size]
            result[i] = windowSum
        }

        return result
    }
}