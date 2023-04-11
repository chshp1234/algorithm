package com.study.algorithm.algorithms.algorithmsKT

//给你一个数组 towers 和一个整数 radius 。
//
//数组  towers  中包含一些网络信号塔，其中 towers[i] = [xi, yi, qi] 表示第 i 个网络信号塔的坐标是 (xi, yi) 且信号强度参数为 qi 。所有坐标都是在  X-Y 坐标系内的 整数 坐标。两个坐标之间的距离用 欧几里得距离 计算。
//
//整数 radius 表示一个塔 能到达 的 最远距离 。如果一个坐标跟塔的距离在 radius 以内，那么该塔的信号可以到达该坐标。在这个范围以外信号会很微弱，所以 radius 以外的距离该塔是 不能到达的 。
//
//如果第 i 个塔能到达 (x, y) ，那么该塔在此处的信号为 ⌊qi / (1 + d)⌋ ，其中 d 是塔跟此坐标的距离。一个坐标的 信号强度 是所有 能到达 该坐标的塔的信号强度之和。
//
//请你返回数组 [cx, cy] ，表示 信号强度 最大的 整数 坐标点 (cx, cy) 。如果有多个坐标网络信号一样大，请你返回字典序最小的 非负 坐标。
//
//注意：
//
//坐标 (x1, y1) 字典序比另一个坐标 (x2, y2) 小，需满足以下条件之一：
//要么 x1 < x2 ，
//要么 x1 == x2 且 y1 < y2 。
//⌊val⌋ 表示小于等于 val 的最大整数（向下取整函数）。
// 
//
//示例 1：
//
//
//输入：towers = [[1,2,5],[2,1,7],[3,1,9]], radius = 2
//输出：[2,1]
//解释：
//坐标 (2, 1) 信号强度之和为 13
//- 塔 (2, 1) 强度参数为 7 ，在该点强度为 ⌊7 / (1 + sqrt(0)⌋ = ⌊7⌋ = 7
//- 塔 (1, 2) 强度参数为 5 ，在该点强度为 ⌊5 / (1 + sqrt(2)⌋ = ⌊2.07⌋ = 2
//- 塔 (3, 1) 强度参数为 9 ，在该点强度为 ⌊9 / (1 + sqrt(1)⌋ = ⌊4.5⌋ = 4
//没有别的坐标有更大的信号强度。
//示例 2：
//
//输入：towers = [[23,11,21]], radius = 9
//输出：[23,11]
//解释：由于仅存在一座信号塔，所以塔的位置信号强度最大。
//示例 3：
//
//输入：towers = [[1,2,13],[2,1,7],[0,1,9]], radius = 2
//输出：[1,2]
//解释：坐标 (1, 2) 的信号强度最大。
// 
//
//提示：
//
//1 <= towers.length <= 50
//towers[i].length == 3
//0 <= xi, yi, qi <= 50
//1 <= radius <= 50
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/coordinate-with-maximum-network-quality
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 网络信号最好的坐标 {
    fun bestCoordinate(towers: Array<IntArray>, radius: Int): IntArray {
        //模拟
        //先找出塔的边界(塔的最小坐标和最大坐标)
        //以此接下来的点的遍历时就从做小坐标到最大坐标之间.
        //再根据每个点的信号强度之和
        //根据条件判断该点是否是符合条件的点
        val border = getBorder(towers)
        var signal = 0
        var x = 0
        var y = 0

        val towersRadius = radius * radius

        for (pointX in border[0][0]..border[1][0]) {
            for (pointY in border[0][1]..border[1][1]) {
                val pointSignal = calculateSignal(pointX, pointY, towers, towersRadius)
                //这里x和y都是从小到大遍历的，所以不需要后续“根据字典序”的判断
                if (pointSignal > signal /*|| (pointSignal == signal && (pointX < x || (pointX == x && pointY < y)))*/) {
                    x = pointX
                    y = pointY
                    signal = pointSignal
                }
            }
        }

        return intArrayOf(x, y)
    }

    fun calculateSignal(x: Int, y: Int, towers: Array<IntArray>, radius: Int): Int {
        //求该点到所有塔的信号强度之和
        var signal = 0
        for (t in towers) {
            val distance = Math.pow((x - t[0]).toDouble(), 2.0) + Math.pow((y - t[1]).toDouble(), 2.0)
            if (distance <= radius) {
                signal += ((t[2] / (1 + Math.sqrt(distance))).toInt())
            }
        }
        return signal
    }

    fun getBorder(towers: Array<IntArray>): Array<IntArray> {
        //获取所有塔的最大xy坐标和最小xy坐标
        var xMin = towers[0][0]
        var yMin = towers[0][1]
        var xMax = towers[0][0]
        var yMax = towers[0][1]

        for (t in towers) {
            if (t[0] < xMin) {
                xMin = t[0]
            } else if (t[0] > xMax) {
                xMax = t[0]
            }

            if (t[1] < yMin) {
                yMin = t[1]
            } else if (t[1] > yMax) {
                yMax = t[1]
            }
        }

        return arrayOf(intArrayOf(xMin, yMin), intArrayOf(xMax, yMax))
    }
}