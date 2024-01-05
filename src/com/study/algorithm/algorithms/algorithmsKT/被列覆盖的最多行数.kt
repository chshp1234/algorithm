package com.study.algorithm.algorithms.algorithmsKT

//给你一个下标从 0 开始、大小为 m x n 的二进制矩阵 matrix ；另给你一个整数 numSelect，表示你必须从 matrix 中选择的 不同 列的数量。
//
//如果一行中所有的 1 都被你选中的列所覆盖，则认为这一行被 覆盖 了。
//
//形式上，假设 s = {c1, c2, ...., cnumSelect} 是你选择的列的集合。对于矩阵中的某一行 row ，如果满足下述条件，则认为这一行被集合 s 覆盖：
//
//对于满足 matrix[row][col] == 1 的每个单元格 matrix[row][col]（0 <= col <= n - 1），col 均存在于 s 中，或者
//row 中 不存在 值为 1 的单元格。
//你需要从矩阵中选出 numSelect 个列，使集合覆盖的行数最大化。
//
//返回一个整数，表示可以由 numSelect 列构成的集合 覆盖 的 最大行数 。
//
// 
//
//示例 1：
//
//
//
//输入：matrix = [[0,0,0],[1,0,1],[0,1,1],[0,0,1]], numSelect = 2
//输出：3
//解释：
//图示中显示了一种覆盖 3 行的可行办法。
//选择 s = {0, 2} 。
//- 第 0 行被覆盖，因为其中没有出现 1 。
//- 第 1 行被覆盖，因为值为 1 的两列（即 0 和 2）均存在于 s 中。
//- 第 2 行未被覆盖，因为 matrix[2][1] == 1 但是 1 未存在于 s 中。
//- 第 3 行被覆盖，因为 matrix[2][2] == 1 且 2 存在于 s 中。
//因此，可以覆盖 3 行。
//另外 s = {1, 2} 也可以覆盖 3 行，但可以证明无法覆盖更多行。
//示例 2：
//
//
//
//输入：matrix = [[1],[0]], numSelect = 1
//输出：2
//解释：
//选择唯一的一列，两行都被覆盖了，因为整个矩阵都被覆盖了。
//所以我们返回 2 。
// 
//
//提示：
//
//m == matrix.length
//n == matrix[i].length
//1 <= m, n <= 12
//matrix[i][j] 要么是 0 要么是 1
//1 <= numSelect <= n
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-rows-covered-by-columns
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {

    //思路
    //回溯
    //因为矩阵中只包含数字0和1，并且只会将1设置为0，那么我们可以把每一行的数字整体看做是一个整形数字二进制表示
    //遍历矩阵，将每一行的数字当做二进制转换成一个整形，并用一个map记录每一列中有哪几行的数字是1
    //之后进行回溯计算，依次将每一列数字设为0，或者不处理，最后再统计并处理完之后数组中有几个数字0，并维护最大数量。
    //二进制枚举
    //这个思路可以参照官方题解：
    //
    //我们可以用一个整数 S 来表示当前我们选中列的序号集合：从低位到高位，第 i 位为 1 则表示第 i 列被选择，否则表示第 i 列没被选择。每一行的元素依旧当做一个整形数字，最后将数字中对应S里代表的‘1’的位置为0，再判断该数字是否为0即可。
    //还可以通过Gosper's Hack算法进行求解S优化。
    //
    //作者：chshp
    //链接：https://leetcode.cn/problems/maximum-rows-covered-by-columns/solution/hui-su-wei-yun-suan-by-chshp-pyo8/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    var res = 0
    fun maximumRows(matrix: Array<IntArray>, numSelect: Int): Int {

        //每一行元素代表的整形数字
        val nums = IntArray(matrix.size)
        //每一列中1所在的行数，key代表列，value代表这一列中哪几行元素是1
        val oneMap = HashMap<Int, MutableList<Int>>()

        var flag = 1

        //构造nums和oneMap
        for (c in matrix[0].size - 1 downTo 0) {
            for (r in matrix.indices) {
                if (matrix[r][c] == 1) {
                    nums[r] = nums[r] or flag
                    (oneMap[c] ?: run {
                        ArrayList<Int>().also {
                            oneMap[c] = it
                        }
                    }).add(r)
                }
            }
            flag = flag shl 1
        }

        backTrack(nums, oneMap, matrix[0].size, flag shr 1, 0, numSelect)
        return res
    }

    //回溯
    fun backTrack(nums: IntArray, oneMap: Map<Int, List<Int>>, col: Int, flag: Int, index: Int, numSelect: Int) {
        //当可选择的列数为0时，直接进行统计数组中0的数量
        if (numSelect == 0) {
            res = Math.max(res, nums.count { it == 0 })
            return
        }
        //当flag为0，说明按列遍历到头了，不进行统计（没有必要）
        if (flag == 0) {
            return
        }
        //如果当前列数+可选择的列数==总列数，说明剩下后面几列都可以设置为0，那么都设置为0后并统计数字0的数量
        if (index + numSelect == col) {
            //-(flag shl 1)表示以flag（包括）右边所有元素都为0，左边（不包括）所有元素都为1，那么就可以将剩余的几列的元素一次性设置为0
            res = Math.max(res, nums.count { (it and -(flag shl 1)) == 0 })
            return
        }
        val ones = oneMap[index]
        //取出当前列中，元素1所在的行
        if (!ones.isNullOrEmpty()) {
            for (i in ones) {
                nums[i] = nums[i] and flag.inv()
            }
            //选中当前列
            backTrack(nums, oneMap, col,flag shr 1, index + 1, numSelect - 1)
            for (i in ones) {
                nums[i] = nums[i] or flag
            }
        }
        //不选当前列
        backTrack(nums, oneMap, col,flag shr 1, index + 1, numSelect)
    }
}