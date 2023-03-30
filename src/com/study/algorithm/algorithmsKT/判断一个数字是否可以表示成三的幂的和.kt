package com.study.algorithm.algorithmsKT

//给你一个整数 n ，如果你可以将 n 表示成若干个不同的三的幂之和，请你返回 true ，否则请返回 false 。
//
//对于一个整数 y ，如果存在整数 x 满足 y == 3x ，我们称这个整数 y 是三的幂。
//
// 
//
//示例 1：
//
//输入：n = 12
//输出：true
//解释：12 = 31 + 32
//示例 2：
//
//输入：n = 91
//输出：true
//解释：91 = 30 + 32 + 34
//示例 3：
//
//输入：n = 21
//输出：false
// 
//
//提示：
//
//1 <= n <= 107
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/check-if-number-is-a-sum-of-powers-of-three
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 判断一个数字是否可以表示成三的幂的和 {
    fun checkPowersOfThree(n: Int): Boolean {
        //根据2进制中快速幂的思路,在本题中,我们可以模拟3进制进行求解
        //如果取余的过程中,余数为2,说明不符合条件,返回false,遍历结束即可返回true
        var num = n
        while (num > 0) {
            if (num % 3 == 2) {
                return false
            }
            num /= 3
        }
        return true
    }
}