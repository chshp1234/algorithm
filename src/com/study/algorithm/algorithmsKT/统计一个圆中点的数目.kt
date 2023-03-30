package com.study.algorithm.algorithmsKT

//给你一个数组 points ，其中 points[i] = [xi, yi] ，表示第 i 个点在二维平面上的坐标。多个点可能会有 相同 的坐标。
//
//同时给你一个数组 queries ，其中 queries[j] = [xj, yj, rj] ，表示一个圆心在 (xj, yj) 且半径为 rj 的圆。
//
//对于每一个查询 queries[j] ，计算在第 j 个圆 内 点的数目。如果一个点在圆的 边界上 ，我们同样认为它在圆 内 。
//
//请你返回一个数组 answer ，其中 answer[j]是第 j 个查询的答案。
//
// 
//
//示例 1：
//
//
//输入：points = [[1,3],[3,3],[5,3],[2,2]], queries = [[2,3,1],[4,3,1],[1,1,2]]
//输出：[3,2,2]
//解释：所有的点和圆如上图所示。
//queries[0] 是绿色的圆，queries[1] 是红色的圆，queries[2] 是蓝色的圆。
//示例 2：
//
//
//输入：points = [[1,1],[2,2],[3,3],[4,4],[5,5]], queries = [[1,2,2],[2,2,2],[4,3,2],[4,3,3]]
//输出：[2,3,2,4]
//解释：所有的点和圆如上图所示。
//queries[0] 是绿色的圆，queries[1] 是红色的圆，queries[2] 是蓝色的圆，queries[3] 是紫色的圆。
// 
//
//提示：
//
//1 <= points.length <= 500
//points[i].length == 2
//0 <= x​​​​​​i, y​​​​​​i <= 500
//1 <= queries.length <= 500
//queries[j].length == 3
//0 <= xj, yj <= 500
//1 <= rj <= 500
//所有的坐标都是整数。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/queries-on-number-of-points-inside-a-circle
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 统计一个圆中点的数目 {
    fun countPoints(points: Array<IntArray>, queries: Array<IntArray>): IntArray {
        //模拟
        //判断一个点是否在圆内,只需要判断这个点到圆心的距离是否小于等于圆的半径即可
        //方便计算,判断时先计算圆的半径的平方,对于每个点,计算其道圆心的x坐标的平方加上y坐标的平方(可省去开方的步骤)的值,再判断其和半径的平方的大小
        //如果结果是小于等于,那么说明其在圆内,结果加一
        val res = IntArray(queries.size)

        for (i in queries.indices) {
            res[i] = checkPoint(points, queries[i])
        }

        return res
    }

    fun checkPoint(points: Array<IntArray>, circle: IntArray): Int {
        val r = circle[2] * circle[2]
        var count = 0
        for (p in points) {
            val distance =
                Math.pow((circle[0] - p[0]).toDouble(), 2.0) + Math.pow((circle[1] - p[1]).toDouble(), 2.0)
            if (distance <= r) {
                count++
            }
        }
        return count
    }
}