package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

//给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
//
//示例 1:
//
//输入:
//[
//  [1,1,1],
//  [1,0,1],
//  [1,1,1]
//]
//输出:
//[
//  [1,0,1],
//  [0,0,0],
//  [1,0,1]
//]
//示例 2:
//
//输入:
//[
//  [0,1,2,0],
//  [3,4,5,2],
//  [1,3,1,5]
//]
//输出:
//[
//  [0,0,0,0],
//  [0,4,5,0],
//  [0,3,1,0]
//]
//进阶:
//
//一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
//一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
//你能想出一个常数空间的解决方案吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/set-matrix-zeroes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 矩阵置零 {
    @Test
    public void 矩阵置零() {
        int[][] ints = new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 8, 9}};
        System.out.println("原始矩阵:" + Arrays.deepToString(ints));
        setZeroes(ints);
        System.out.println("矩阵置零:" + Arrays.deepToString(ints));
    }

    public void setZeroes(int[][] matrix) {
        //标记数组
        //使用两个标记数组，判断某一行和某一列是否需要变更
        //具体步骤如注释
        int       row     = matrix.length;
        int       col     = matrix[0].length;
        boolean[] rChange = new boolean[row];
        boolean[] cChange = new boolean[col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                //如果当前值为0
                if (matrix[r][c] == 0) {
                    //如果当前行和列都没被标记，则对此行和列进行标记
                    //并从第一行到当前行设置元素为0（列号不变）；从第一列到当前列设置元素为0（行号不变）
                    if (!rChange[r] && !cChange[c]) {
                        rChange[r] = true;
                        cChange[c] = true;
                        for (int i = 0; i < r; i++) {
                            matrix[i][c] = 0;
                        }
                        for (int i = 0; i < c; i++) {
                            matrix[r][i] = 0;
                        }
                    }
                    //如果只标记了当前列，则标记上当前的行
                    //并从第一列到当前列设置元素为0（行号不变）
                    else if (cChange[c]) {
                        rChange[r] = true;
                        for (int i = 0; i < c; i++) {
                            matrix[r][i] = 0;
                        }
                    }
                    //如果只标记了当前行，则标记上当前列
                    //并从第一行到当前行设置元素为0（列号不变）
                    else if (rChange[r]) {
                        cChange[c] = true;
                        for (int i = 0; i < r; i++) {
                            matrix[i][c] = 0;
                        }
                    }
                }
                //如果当前元素不为0，但当前行或当前列已被标记，则设置当前元素为0
                else {
                    if (rChange[r] || cChange[c]) {
                        matrix[r][c] = 0;
                    }
                }
            }
        }
    }

}
