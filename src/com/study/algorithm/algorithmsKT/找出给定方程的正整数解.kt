package com.study.algorithm.algorithmsKT

import org.junit.Test
import kotlin.math.pow

//给你一个函数  f(x, y) 和一个目标结果 z，函数公式未知，请你计算方程 f(x,y) == z 所有可能的正整数 数对 x 和 y。满足条件的结果数对可以按任意顺序返回。
//
//尽管函数的具体式子未知，但它是单调递增函数，也就是说：
//
//f(x, y) < f(x + 1, y)
//f(x, y) < f(x, y + 1)
//函数接口定义如下：
//
//interface CustomFunction {
//public:
//  // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
//  int f(int x, int y);
//};
//你的解决方案将按如下规则进行评判：
//
//判题程序有一个由 CustomFunction 的 9 种实现组成的列表，以及一种为特定的 z 生成所有有效数对的答案的方法。
//判题程序接受两个输入：function_id（决定使用哪种实现测试你的代码）以及目标结果 z 。
//判题程序将会调用你实现的 findSolution 并将你的结果与答案进行比较。
//如果你的结果与答案相符，那么解决方案将被视作正确答案，即 Accepted 。
// 
//
//示例 1：
//
//输入：function_id = 1, z = 5
//输出：[[1,4],[2,3],[3,2],[4,1]]
//解释：function_id = 1 暗含的函数式子为 f(x, y) = x + y
//以下 x 和 y 满足 f(x, y) 等于 5：
//x=1, y=4 -> f(1, 4) = 1 + 4 = 5
//x=2, y=3 -> f(2, 3) = 2 + 3 = 5
//x=3, y=2 -> f(3, 2) = 3 + 2 = 5
//x=4, y=1 -> f(4, 1) = 4 + 1 = 5
//示例 2：
//
//输入：function_id = 2, z = 5
//输出：[[1,5],[5,1]]
//解释：function_id = 2 暗含的函数式子为 f(x, y) = x * y
//以下 x 和 y 满足 f(x, y) 等于 5：
//x=1, y=5 -> f(1, 5) = 1 * 5 = 5
//x=5, y=1 -> f(5, 1) = 5 * 1 = 5
// 
//
//提示：
//
//1 <= function_id <= 9
//1 <= z <= 100
//题目保证 f(x, y) == z 的解处于 1 <= x, y <= 1000 的范围内。
//在 1 <= x, y <= 1000 的前提下，题目保证 f(x, y) 是一个 32 位有符号整数。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/find-positive-integer-solution-for-a-given-equation
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 找出给定方程的正整数解 {

    @Test
    fun 找出给定方程的正整数解() {
        println(
            "找出给定方程的正整数解:${
                findSolution(
                    { x: Int, y: Int ->
                        x.toDouble().pow(y.toDouble()).pow(2).toInt()
                    }, 16
                )
            }"
        )
    }

    fun findSolution(customFunction: CustomFunction, z: Int): List<List<Int>> {
        //二分查找
        //先用二分固定x的最小值和最大值
        //再在这个区间中,二分查找y值
        val res = ArrayList<List<Int>>()

        var l = 1
        var r = 1000

        //二分查找x的最小值
        //需要满足计算的最大值比z还大
        //如果计算的最大值比z还要小,说明x至少在mid右边
        while (l < r) {
            val mid = (l + r) shr 1
            val max = customFunction.f(mid, 1000)
            if (max < z) {
                l = mid + 1
            } else {
                r = mid
            }
        }
        var xL: Int = r
        r = 1000

        //二分查找x的最大值
        //需要满足计算的最小值比z还小
        //如果计算的最小值比z还要大,说明x至少在mid左边
        while (l < r) {
            val mid = (l + r) shr 1
            val min = customFunction.f(mid, 0)
            if (min > z) {
                r = mid - 1
            } else {
                l = mid + 1
            }
        }

        val xR: Int = l
        while (xL <= xR) {
            l = 1
            r = 1000

            //二分查找y值
            Y@
            while (l <= r) {
                val y = (l + r) shr 1

                val it = customFunction.f(xL, y)
                when {
                    it > z -> r = y - 1
                    it < z -> l = y + 1
                    else -> {
                        res.add(listOf(xL, y))
                        break@Y
                    }
                }
            }

            xL++
        }

        return res
    }
}

fun interface CustomFunction {
    // Returns f(x, y) for any given positive integers x and y.
    // Note that f(x, y) is increasing with respect to both x and y.
    // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
    fun f(x: Int, y: Int): Int
}