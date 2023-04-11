package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

public class 矩阵对角线遍历 {
    @Test
    public void 矩阵对角线遍历() {
        int[][] ints = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("矩阵对角线遍历:" + Arrays.toString(findDiagonalOrder(ints)));
    }
    /**
     * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
     *
     * <p>示例:
     *
     * <p>输入: [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ]
     *
     * <p>输出: [1,2,4,7,5,3,6,8,9]
     *
     * <p>说明:给定矩阵中的元素总数不会超过 100000 。
     */
    /*todo 优化*/
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int loopCount = matrix.length + matrix[0].length - 1;

        int maxLoop = Math.min(matrix.length, matrix[0].length);

        int firstLoop = loopCount / 2;

        int[] result = new int[matrix.length * matrix[0].length];

        int index = 0;
        boolean direction = true /*true向上*/;
        int start = 0;

        //        boolean horizontalMax = (matrix.length < matrix[0].length);

        for (int i = 0; i < loopCount; i++) {
            if ((i + 1) <= firstLoop) {
                for (int j = 0, l = Math.min(i + 1, maxLoop); j < l; j++) {
                    if (direction) {
                        if (i + 1 <= matrix.length) {
                            start = i;
                            result[index] = matrix[start - j][j];
                            //                            start = Math.min(l - 1, matrix.length -
                            // 1);
                        } else {
                            start = matrix.length - 1;
                            result[index] = matrix[start - j][i - matrix.length + j + 1];
                        }

                    } else {
                        if (i + 1 <= matrix[0].length) {
                            start = i;
                            result[index] = matrix[j][start - j];
                        } else {
                            start = matrix[0].length - 1;
                            result[index] = matrix[i - matrix[0].length + j + 1][start - j];
                        }
                    }
                    index++;
                }
                direction = !direction;
            } else {
                for (int j = 0, l = Math.min(loopCount - i, maxLoop); j < l; j++) {
                    if (direction) {
                        if (i + 1 <= matrix.length) {
                            start = i;
                            result[index] = matrix[start - j][j];
                            //                            start = Math.min(l - 1, matrix.length -
                            // 1);
                        } else {
                            start = matrix.length - 1;
                            result[index] = matrix[start - j][i - matrix.length + j + 1];
                        }

                    } else {
                        if (i + 1 <= matrix[0].length) {
                            start = i;
                            result[index] = matrix[j][start - j];
                        } else {
                            start = matrix[0].length - 1;
                            result[index] = matrix[i - matrix[0].length + j + 1][start - j];
                        }
                    }
                    index++;
                }
                direction = !direction;
            }
        }

        return result;
    }
}
