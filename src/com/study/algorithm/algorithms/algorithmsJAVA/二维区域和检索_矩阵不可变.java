package com.study.algorithm.algorithms.algorithmsJAVA;

//给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
//
//
//上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
//
// 
//
//示例：
//
//给定 matrix = [
//  [3, 0, 1, 4, 2],
//  [5, 6, 3, 2, 1],
//  [1, 2, 0, 1, 5],
//  [4, 1, 0, 1, 7],
//  [1, 0, 3, 0, 5]
//]
//
//sumRegion(2, 1, 4, 3) -> 8
//sumRegion(1, 1, 2, 2) -> 11
//sumRegion(1, 2, 2, 4) -> 12
// 
//
//提示：
//
//你可以假设矩阵不可变。
//会多次调用 sumRegion 方法。
//你可以假设 row1 ≤ row2 且 col1 ≤ col2 。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/range-sum-query-2d-immutable
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 二维区域和检索_矩阵不可变 {

    //一维前缀和；
    //先计算每一行的前缀和
    //取值时，累加每一行的区间和

    //二维前缀和:
    //设preSum[i][j]为从二维数组左上角（0，0）开始，到右下角（i+1,j+1）矩形的和
    //那么在区域矩形中，从左上角(row1, col1)到右下角(row2, col2)处的区间和就为：
    //preSum[row2 + 1][col2 + 1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1] + preSum[row1][col1]
    private final int[][] preSum;

    public 二维区域和检索_矩阵不可变(int[][] matrix) {
        int row = matrix.length;
        int col = (row == 0 || matrix[0] == null) ? 0 : matrix[0].length;
        preSum = new int[row + 1][col + 1];
        for (int r = 0; r < row; r++) {
            int[] preSumCol = new int[col + 1];
            for (int c = 0; c < col; c++) {
                int tempCol = c + 1;
                //维护当前行的前缀和
                preSumCol[tempCol] = preSumCol[c] + matrix[r][c];
                //当前矩形的前缀和就为，在上一行处的矩形的前缀和+当前行的前缀和
                preSum[r + 1][tempCol] = preSum[r][tempCol] + preSumCol[tempCol];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2 + 1][col2 + 1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1] + preSum[row1][col1];
    }
}
