package com.study.algorithm.algorithms.algorithmsKT

//三枚石子放置在数轴上，位置分别为 a，b，c。
//
//每一回合，你可以从两端之一拿起一枚石子（位置最大或最小），并将其放入两端之间的任一空闲位置。形式上，假设这三枚石子当前分别位于位置 x, y, z 且 x < y < z。那么就可以从位置 x 或者是位置 z 拿起一枚石子，并将该石子移动到某一整数位置 k 处，其中 x < k < z 且 k != y。
//
//当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。
//
//要使游戏结束，你可以执行的最小和最大移动次数分别是多少？ 以长度为 2 的数组形式返回答案：answer = [minimum_moves, maximum_moves]
//
// 
//
//示例 1：
//
//输入：a = 1, b = 2, c = 5
//输出：[1, 2]
//解释：将石子从 5 移动到 4 再移动到 3，或者我们可以直接将石子移动到 3。
//示例 2：
//
//输入：a = 4, b = 3, c = 2
//输出：[0, 0]
//解释：我们无法进行任何移动。
// 
//
//提示：
//
//1 <= a <= 100
//1 <= b <= 100
//1 <= c <= 100
//a != b, b != c, c != a
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/moving-stones-until-consecutive
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 移动石子直到连续 {
    fun numMovesStones(a: Int, b: Int, c: Int): IntArray {
        //先对abc进行排序,使得a<=b<=c
        //再求出diff1=b-a,diff2=c-b
        //最小移动次数为getMin(diff1, diff2)
        //最大移动次数为getMax(diff1, diff2)
        if (a > b) {
            return numMovesStones(b, a, c)
        }
        if (b > c) {
            return numMovesStones(a, c, b)
        }
        val diff1 = b - a
        val diff2 = c - b
        return intArrayOf(getMin(diff1, diff2), getMax(diff1, diff2))
    }

    fun getMin(diff1: Int, diff2: Int): Int {
        return if (diff1 == 2 || diff2 == 2) {
            //如果有一个间距为2,则另一边的石头可直接移动过来,所以是一步
            1
        } else if (diff1 > 2 && diff2 > 2) {
            //如果两边的间距都大于2,则分别把两边石头各移一步,使其连续
            2
        } else if (diff1 > 2 || diff2 > 2) {
            //如果只有一边大于2,那么只需要一步
            1
        } else {
            0
        }
    }

    fun getMax(diff1: Int, diff2: Int): Int {
        return if (diff1 > 1 && diff2 > 1) {
            //如果两边间距都大于1,则最大步数为diff1 + diff2 - 2
            diff1 + diff2 - 2
        }
        //否则,只有一边间距大于1,那么步数为那个的边距-1
        else if (diff1 > 1) {

            diff1 - 1
        } else {
            diff2 - 1
        }
    }
}