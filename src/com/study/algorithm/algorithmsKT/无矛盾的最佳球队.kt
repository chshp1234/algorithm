package com.study.algorithm.algorithmsKT

import org.junit.Test

//假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。
//
//然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。
//
//给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。请你返回 所有可能的无矛盾球队中得分最高那支的分数 。
//
// 
//
//示例 1：
//
//输入：scores = [1,3,5,10,15], ages = [1,2,3,4,5]
//输出：34
//解释：你可以选中所有球员。
//示例 2：
//
//输入：scores = [4,5,6,5], ages = [2,1,2,1]
//输出：16
//解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。
//示例 3：
//
//输入：scores = [1,2,3,5], ages = [8,9,10,1]
//输出：6
//解释：最佳的选择是前 3 名球员。
// 
//
//提示：
//
//1 <= scores.length, ages.length <= 1000
//scores.length == ages.length
//1 <= scores[i] <= 106
//1 <= ages[i] <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/best-team-with-no-conflicts
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 无矛盾的最佳球队 {
    @Test
    fun 无矛盾的最佳球队() {
        println(
            "无矛盾的最佳球队:${
                bestTeamScore(
                    intArrayOf(319776, 611683, 835240, 602298, 430007, 574, 142444, 858606, 734364, 896074),
                    intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
                )
            }"
        )
    }

    fun bestTeamScore(scores: IntArray, ages: IntArray): Int {
        //动态规划
        //先对得分-年龄进行排序,如果年龄相同,按得分排序,如果年龄不相同,按年龄排序
        //这样,对数组进行遍历时,年龄总是递增的,就可以只考虑得分情况
        //设dp[i]为:以第i个队员为该球队最大得分的队员时,该球队的总得分情况
        //那么dp[i]=max(dp[j])+score[i],0<=j<i&&score[j]<=score[i]
        //最后返回dp数组中的最大值即可
        val map = Array(scores.size) {
            intArrayOf(scores[it], ages[it])
        }
        map.sortWith(object : Comparator<IntArray> {
            override fun compare(o1: IntArray, o2: IntArray): Int {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0]
                }
                return o1[1] - o2[1]
            }
        })
        val dp = IntArray(scores.size)
        var res = 0
        for (i in map.indices) {
            var maxScore = 0
            for (j in 0 until i) {
                if (map[j][0] <= map[i][0]) {
                    maxScore = Math.max(maxScore, dp[j])
                }
            }
            dp[i] = maxScore + map[i][0]
            res = Math.max(dp[i], res)
        }
        return res
    }
}