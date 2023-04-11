package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//给你两个二维整数数组 items1 和 items2 ，表示两个物品集合。每个数组 items 有以下特质：
//
//items[i] = [valuei, weighti] 其中 valuei 表示第 i 件物品的 价值 ，weighti 表示第 i 件物品的 重量 。
//items 中每件物品的价值都是 唯一的 。
//请你返回一个二维数组 ret，其中 ret[i] = [valuei, weighti]， weighti 是所有价值为 valuei 物品的 重量之和 。
//
//注意：ret 应该按价值 升序 排序后返回。
//
// 
//
//示例 1：
//
//输入：items1 = [[1,1],[4,5],[3,8]], items2 = [[3,1],[1,5]]
//输出：[[1,6],[3,9],[4,5]]
//解释：
//value = 1 的物品在 items1 中 weight = 1 ，在 items2 中 weight = 5 ，总重量为 1 + 5 = 6 。
//value = 3 的物品再 items1 中 weight = 8 ，在 items2 中 weight = 1 ，总重量为 8 + 1 = 9 。
//value = 4 的物品在 items1 中 weight = 5 ，总重量为 5 。
//所以，我们返回 [[1,6],[3,9],[4,5]] 。
//示例 2：
//
//输入：items1 = [[1,1],[3,2],[2,3]], items2 = [[2,1],[3,2],[1,3]]
//输出：[[1,4],[2,4],[3,4]]
//解释：
//value = 1 的物品在 items1 中 weight = 1 ，在 items2 中 weight = 3 ，总重量为 1 + 3 = 4 。
//value = 2 的物品在 items1 中 weight = 3 ，在 items2 中 weight = 1 ，总重量为 3 + 1 = 4 。
//value = 3 的物品在 items1 中 weight = 2 ，在 items2 中 weight = 2 ，总重量为 2 + 2 = 4 。
//所以，我们返回 [[1,4],[2,4],[3,4]] 。
//示例 3：
//
//输入：items1 = [[1,3],[2,2]], items2 = [[7,1],[2,2],[1,4]]
//输出：[[1,7],[2,4],[7,1]]
//解释：
//value = 1 的物品在 items1 中 weight = 3 ，在 items2 中 weight = 4 ，总重量为 3 + 4 = 7 。
//value = 2 的物品在 items1 中 weight = 2 ，在 items2 中 weight = 2 ，总重量为 2 + 2 = 4 。
//value = 7 的物品在 items2 中 weight = 1 ，总重量为 1 。
//所以，我们返回 [[1,7],[2,4],[7,1]] 。
// 
//
//提示：
//
//1 <= items1.length, items2.length <= 1000
//items1[i].length == items2[i].length == 2
//1 <= valuei, weighti <= 1000
//items1 中每个 valuei 都是 唯一的 。
//items2 中每个 valuei 都是 唯一的 。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/merge-similar-items
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 合并相似的物品 {
    @Test
    fun 合并相似的物品() {
        println(
            "合并相似的物品:${
                mergeSimilarItems(
                    arrayOf(
                        intArrayOf(15, 5),
                        intArrayOf(2, 6),
                        intArrayOf(5, 3),
                        intArrayOf(14, 8),
                        intArrayOf(12, 4),
                        intArrayOf(19, 6),
                        intArrayOf(25, 4),
                        intArrayOf(13, 4),
                        intArrayOf(9, 1)
                    ), arrayOf(
                        intArrayOf(15, 9),
                        intArrayOf(2, 4),
                        intArrayOf(5, 2),
                        intArrayOf(14, 4),
                        intArrayOf(12, 3),
                        intArrayOf(19, 10),
                        intArrayOf(25, 7),
                        intArrayOf(13, 6),
                        intArrayOf(9, 9)
                    )
                )
            }"
        )
    }

    fun mergeSimilarItems(items1: Array<IntArray>, items2: Array<IntArray>): List<List<Int>> {
        //哈希表
        //用哈希表存储items1的value和weight
        //再遍历items2,将相同的value对weight进行累加,不存在的value进行存储
        //将哈希表中的数据导出List,并对value进行排序
        val map = HashMap<Int, Int>()
        for (i in items1) {
            map[i[0]] = i[1]
        }

        for (i in items2) {
            map[i[0]] = i[1] + (map[i[0]] ?: 0)
        }
        val res = map.flatMap {
            listOf(listOf(it.key, it.value))
        }

        return res.sortedBy {
            it[0]
        }
    }
}