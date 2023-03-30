package com.study.algorithm.algorithmsKT

//请你将一些箱子装在 一辆卡车 上。给你一个二维数组 boxTypes ，其中 boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi] ：
//
//numberOfBoxesi 是类型 i 的箱子的数量。
//numberOfUnitsPerBoxi 是类型 i 每个箱子可以装载的单元数量。
//整数 truckSize 表示卡车上可以装载 箱子 的 最大数量 。只要箱子数量不超过 truckSize ，你就可以选择任意箱子装到卡车上。
//
//返回卡车可以装载 单元 的 最大 总数。
//
// 
//
//示例 1：
//
//输入：boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
//输出：8
//解释：箱子的情况如下：
//- 1 个第一类的箱子，里面含 3 个单元。
//- 2 个第二类的箱子，每个里面含 2 个单元。
//- 3 个第三类的箱子，每个里面含 1 个单元。
//可以选择第一类和第二类的所有箱子，以及第三类的一个箱子。
//单元总数 = (1 * 3) + (2 * 2) + (1 * 1) = 8
//示例 2：
//
//输入：boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
//输出：91
// 
//
//提示：
//
//1 <= boxTypes.length <= 1000
//1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
//1 <= truckSize <= 106
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-units-on-a-truck
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 卡车上的最大单元数 {
    fun maximumUnits(boxTypes: Array<IntArray>, truckSize: Int): Int {
        //排序,贪心
        //每次都选取最大的箱子(即数组中numberOfUnitsPerBoxi最大的箱子)
        //直到该类型箱子选完或者打到卡车的最大箱子数量未知
        //于此可以先对boxTypes数组进行排序,根据箱子可装的数量(numberOfUnitsPerBoxi)进行排序
        boxTypes.sortWith(object : Comparator<IntArray> {
            override fun compare(o1: IntArray, o2: IntArray): Int {
                return o2[1] - o1[1]
            }
        })

        var count = 0
        var size = truckSize

        for (array in boxTypes) {
            val left = size - array[0]
            if (left >= 0) {
                count += array[0] * array[1]
                size = left
            } else {
                count += size * array[1]
                break
            }
        }

        return count
    }
}