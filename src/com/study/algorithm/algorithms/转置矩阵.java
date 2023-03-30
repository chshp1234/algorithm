package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

//给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
//
//矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
//
//
//
// 
//
//示例 1：
//
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[[1,4,7],[2,5,8],[3,6,9]]
//示例 2：
//
//输入：matrix = [[1,2,3],[4,5,6]]
//输出：[[1,4],[2,5],[3,6]]
// 
//
//提示：
//
//m == matrix.length
//n == matrix[i].length
//1 <= m, n <= 1000
//1 <= m * n <= 105
//-109 <= matrix[i][j] <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/transpose-matrix
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 转置矩阵 {
    @Test
    public void 转置矩阵() {
        int[][] ints = new int[][]{
                {1, 2}
        };
        System.out.println("转置矩阵:" + Arrays.deepToString(transpose(ints)));
    }

    public int[][] transpose(int[][] matrix) {
        //转置矩阵，行号变列号，列号变行号
        int     row    = matrix.length;
        int     col    = matrix[0].length;
        int[][] result = new int[col][row];
        for (int r = 0; r < col; r++) {
            for (int c = 0; c < row; c++) {
                result[r][c] = matrix[c][r];
            }
        }
        return result;
    }
}
