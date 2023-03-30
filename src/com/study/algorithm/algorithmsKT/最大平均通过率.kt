package com.study.algorithm.algorithmsKT

import org.junit.Test
import java.util.*

//一所学校里有一些班级，每个班级里有一些学生，现在每个班都会进行一场期末考试。给你一个二维数组 classes ，其中 classes[i] = [passi, totali] ，表示你提前知道了第 i 个班级总共有 totali 个学生，其中只有 passi 个学生可以通过考试。
//
//给你一个整数 extraStudents ，表示额外有 extraStudents 个聪明的学生，他们 一定 能通过任何班级的期末考。你需要给这 extraStudents 个学生每人都安排一个班级，使得 所有 班级的 平均 通过率 最大 。
//
//一个班级的 通过率 等于这个班级通过考试的学生人数除以这个班级的总人数。平均通过率 是所有班级的通过率之和除以班级数目。
//
//请你返回在安排这 extraStudents 个学生去对应班级后的 最大 平均通过率。与标准答案误差范围在 10-5 以内的结果都会视为正确结果。
//
// 
//
//示例 1：
//
//输入：classes = [[1,2],[3,5],[2,2]], extraStudents = 2
//输出：0.78333
//解释：你可以将额外的两个学生都安排到第一个班级，平均通过率为 (3/4 + 3/5 + 2/2) / 3 = 0.78333 。
//示例 2：
//
//输入：classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
//输出：0.53485
// 
//
//提示：
//
//1 <= classes.length <= 105
//classes[i].length == 2
//1 <= passi <= totali <= 105
//1 <= extraStudents <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-average-pass-ratio
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 最大平均通过率 {
    @Test
    fun 最大平均通过率() {
        println(
            "最大平均通过率:${
                maxAverageRatio(
                    arrayOf(intArrayOf(1, 2), intArrayOf(3, 5), intArrayOf(2, 2)), 2
                )
            }"
        )
    }

    fun maxAverageRatio(classes: Array<IntArray>, extraStudents: Int): Double {
        //大顶堆
        //对于每个"班级",我们可以计算其加上聪明学生后,其通过率变化了多少
        //那么我们可以维护一个大顶堆,将变化通过率最大的班级放在顶端
        //之后每次将一个聪明学生放进这个班级中,总的通过率将会最大化
        //直到聪明的学生全部放入班级
        //最后在计算总体的平均通过率即可
        val queue = PriorityQueue(object : Comparator<IntArray> {
            override fun compare(o1: IntArray, o2: IntArray): Int {
                val diff1 = ((o1[0].toDouble() + 1) / (o1[1] + 1)) - (o1[0].toDouble() / o1[1])
                val diff2 = ((o2[0].toDouble() + 1) / (o2[1] + 1))- (o2[0].toDouble() / o2[1])
                return if (diff1 >= diff2) -1 else 1
            }
        })
        for (cla in classes) {
            queue.offer(cla)
        }

        var extra = extraStudents
        while (extra > 0) {
            val top = queue.poll()
            top[0]++
            top[1]++
            queue.offer(top)
            extra--
        }
        var res = 0.0

        while (!queue.isEmpty()) {
            val top = queue.poll()
            res += (top[0].toDouble() / top[1])
        }

        return res / classes.size
    }
}