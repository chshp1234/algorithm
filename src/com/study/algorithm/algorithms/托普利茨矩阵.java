package com.study.algorithm.algorithms;

import org.junit.Test;

//给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
//
//如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
//
// 
//
//示例 1：
//
//
//输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
//输出：true
//解释：
//在上述矩阵中, 其对角线为:
//"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
//各条对角线上的所有元素均相同, 因此答案是 True 。
//示例 2：
//
//
//输入：matrix = [[1,2],[2,2]]
//输出：false
//解释：
//对角线 "[1, 2]" 上的元素不同。
// 
//
//提示：
//
//m == matrix.length
//n == matrix[i].length
//1 <= m, n <= 20
//0 <= matrix[i][j] <= 99
// 
//
//进阶：
//
//如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？
//如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/toeplitz-matrix
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 托普利茨矩阵 {

    @Test
    public void 托普利茨矩阵() {
        int[][] ints = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println("托普利茨矩阵:" + isToeplitzMatrix(ints));
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        //每次和左上角坐标元素对比即可
        //这里可以从下标1开始遍历，下标0处为起始点，不需要判断。
        int row = matrix.length;
        int col = matrix[0].length;

        for (int r = 1; r < row; r++) {
            for (int c = 1; c < col; c++) {
                if (matrix[r][c] != matrix[r - 1][c - 1]) {
                    return false;
                }
            }
        }

        return true;
    }

}
