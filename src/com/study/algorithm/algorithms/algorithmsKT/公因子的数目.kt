package com.study.algorithm.algorithms.algorithmsKT

//给你两个正整数 a 和 b ，返回 a 和 b 的 公 因子的数目。
//
//如果 x 可以同时整除 a 和 b ，则认为 x 是 a 和 b 的一个 公因子 。
//
// 
//
//示例 1：
//
//输入：a = 12, b = 6
//输出：4
//解释：12 和 6 的公因子是 1、2、3、6 。
//示例 2：
//
//输入：a = 25, b = 30
//输出：2
//解释：25 和 30 的公因子是 1、5 。
// 
//
//提示：
//
//1 <= a, b <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/number-of-common-factors
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 公因子的数目 {
    fun commonFactors(a: Int, b: Int): Int {
        //枚举
        //另a<b
        //从2到a/2,进行枚举
        //对于数字i,判断是否为公因数,即分别取余都为0,那么结果+1
        //最后在判断b对a进行取余是否为0
        if (a > b) {
            return commonFactors(b, a)
        }
        var res = 1
        for (i in 2..a / 2) {
            if (a % i == 0 && b % i == 0) {
                res++
            }
        }
        if (b % a == 0 && a != 1) {
            res++
        }
        return res
    }
}