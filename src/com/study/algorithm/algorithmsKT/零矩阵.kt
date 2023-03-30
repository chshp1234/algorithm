package com.study.algorithm.algorithmsKT

//编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
//
// 
//
//示例 1：
//
//输入：
//[
//  [1,1,1],
//  [1,0,1],
//  [1,1,1]
//]
//输出：
//[
//  [1,0,1],
//  [0,0,0],
//  [1,0,1]
//]
//示例 2：
//
//输入：
//[
//  [0,1,2,0],
//  [3,4,5,2],
//  [1,3,1,5]
//]
//输出：
//[
//  [0,0,0,0],
//  [0,4,5,0],
//  [0,3,1,0]
//]
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/zero-matrix-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 零矩阵 {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        //模拟
        //遍历,并用两个数组,分别标记某一行和某一列是否需要置零
        //再次遍历,根据上一步,如果这一行或者这一列需要置零,则此元素置零
        val rowToZero = BooleanArray(matrix.size)
        val colToZero = BooleanArray(matrix[0].size)

        for (r in matrix.indices) {
            for (c in matrix[r].indices) {
                if (matrix[r][c] == 0) {
                    rowToZero[r] = true
                    colToZero[c] = true
                }
            }
        }
        for (r in matrix.indices) {
            for (c in matrix[r].indices) {
                if (rowToZero[r] || colToZero[c]) {
                    matrix[r][c] = 0
                }
            }
        }
    }

    fun setZeroes2(matrix: Array<IntArray>): Unit {
        //原地标记
        //先使用两个变量zeroCol和zeroCol判断第0行和第0列是否需要置零
        //从第1行第1列开始遍历数组,如果元素为零,则令这一行的开头元素(列下标为0)和这一列的开头元素(行下标为0)置为零
        //再从第1行第1列开始遍历数组,如果这一行的第一个元素为0,或者这一列的第一个元素为0,则令当前元素为0
        //最后再根据第一步的zeroCol和zeroRow,决定第一行和第一列是否需要置零
        var zeroCol = false
        var zeroRow = false
        for (i in matrix[0].indices) {
            if (matrix[0][i] == 0) {
                zeroRow = true
            }
        }
        for (i in matrix.indices) {
            if (matrix[i][0] == 0) {
                zeroCol = true
            }
        }

        for (r in 1 until matrix.size) {
            for (c in 1 until matrix[r].size) {
                if (matrix[r][c] == 0) {
                    matrix[0][c] = 0
                    matrix[r][0] = 0
                }
            }
        }

        for (r in 1 until matrix.size) {
            for (c in 1 until matrix[r].size) {
                if (matrix[r][0] == -1 || matrix[0][c] == -1) {
                    matrix[r][c] = 0
                }
            }
        }

        if (zeroRow) {
            matrix[0].fill(0)
        }

        if (zeroCol) {
            for (i in matrix.indices) {
                matrix[i][0] = 0
            }
        }
    }
}