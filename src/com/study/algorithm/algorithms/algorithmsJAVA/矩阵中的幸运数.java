package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.ArrayList;
import java.util.List;

//给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
//
//幸运数是指矩阵中满足同时下列两个条件的元素：
//
//在同一行的所有元素中最小
//在同一列的所有元素中最大
// 
//
//示例 1：
//
//输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
//输出：[15]
//解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
//示例 2：
//
//输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
//输出：[12]
//解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
//示例 3：
//
//输入：matrix = [[7,8],[1,2]]
//输出：[7]
// 
//
//提示：
//
//m == mat.length
//n == mat[i].length
//1 <= n, m <= 50
//1 <= matrix[i][j] <= 10^5
//矩阵中的所有元素都是不同的
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/lucky-numbers-in-a-matrix
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 矩阵中的幸运数 {
    public List<Integer> luckyNumbers(int[][] matrix) {
        //预处理：
        //
        //找出每一行最小值的列数下标；找出每一列最大值的行数下标。
        //幸运数字最多为Min(行数，列数)
        //根据预处理的两个数组数据，判断最大值和最小值的是否为同一个元素即可（重合）


        int row = matrix.length;
        int col = matrix[0].length;

        //每一行最小值（列数下标）
        int[] min = new int[row];
        //每一列最大值（行数下标）
        int[] max = new int[col];

        //预处理
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] < matrix[r][min[r]]) {
                    min[r] = c;
                }
                if (matrix[r][c] > matrix[max[c]][c]) {
                    max[c] = r;
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        //幸运数字最多为Min(行数，列数)
        if (row > col) {
            //max[c]代表第c列最大值的行数下标
            //min[max[c]]代表第max[c]行中最小值的列数下标
            //那么min[max[c]] == c，说明第c列的最大值也是那一行中的最小值
            for (int c = 0; c < col; c++) {
                if (min[max[c]] == c) {
                    result.add(matrix[max[c]][c]);
                }
            }
        } else {
            //同上
            for (int r = 0; r < row; r++) {
                if (max[min[r]] == r) {
                    result.add(matrix[r][min[r]]);
                }
            }
        }

        return result;

    }
}
