package com.study.algorithm.algorithms.algorithmsJAVA;

//给你一个大小为 rows x cols 的矩阵 mat，其中 mat[i][j] 是 0 或 1，请返回 矩阵 mat 中特殊位置的数目 。
//
//特殊位置 定义：如果 mat[i][j] == 1 并且第 i 行和第 j 列中的所有其他元素均为 0（行和列的下标均 从 0 开始 ），则位置 (i, j) 被称为特殊位置。
//
// 
//
//示例 1：
//
//输入：mat = [[1,0,0],
//            [0,0,1],
//            [1,0,0]]
//输出：1
//解释：(1,2) 是一个特殊位置，因为 mat[1][2] == 1 且所处的行和列上所有其他元素都是 0
//示例 2：
//
//输入：mat = [[1,0,0],
//            [0,1,0],
//            [0,0,1]]
//输出：3
//解释：(0,0), (1,1) 和 (2,2) 都是特殊位置
//示例 3：
//
//输入：mat = [[0,0,0,1],
//            [1,0,0,0],
//            [0,1,1,0],
//            [0,0,0,0]]
//输出：2
//示例 4：
//
//输入：mat = [[0,0,0,0,0],
//            [1,0,0,0,0],
//            [0,1,0,0,0],
//            [0,0,1,0,0],
//            [0,0,0,1,1]]
//输出：3
// 
//
//提示：
//
//rows == mat.length
//cols == mat[i].length
//1 <= rows, cols <= 100
//mat[i][j] 是 0 或 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/special-positions-in-a-binary-matrix
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 二进制矩阵中的特殊位置 {
    public int numSpecial(int[][] mat) {
        //模拟
        //用两个数组统计每一行1的数量以及每一列1的数量
        //在判断两个数组中1的数量都为1，且当前元素为1，那么说明这个位置是特殊位置
        int row = mat.length;
        int col = mat[0].length;

        int[] countR = new int[row];
        int[] countC = new int[col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (mat[r][c] == 1) {
                    countR[r]++;
                    countC[c]++;
                }
            }
        }

        int result = 0;

        for (int r = 0; r < row; r++) {
            if (countR[r] != 1) {
                continue;
            }
            for (int c = 0; c < col; c++) {
                if (countC[c] != 1 || mat[r][c] != 1) {
                    continue;
                }
                result++;
            }
        }

        return result;
    }

}
