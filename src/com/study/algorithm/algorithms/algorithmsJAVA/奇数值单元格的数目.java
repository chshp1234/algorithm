package com.study.algorithm.algorithms.algorithmsJAVA;

//给你一个 m x n 的矩阵，最开始的时候，每个单元格中的值都是 0。
//
//另有一个二维索引数组 indices，indices[i] = [ri, ci] 指向矩阵中的某个位置，其中 ri 和 ci 分别表示指定的行和列（从 0 开始编号）。
//
//对 indices[i] 所指向的每个位置，应同时执行下述增量操作：
//
//ri 行上的所有单元格，加 1 。
//ci 列上的所有单元格，加 1 。
//给你 m、n 和 indices 。请你在执行完所有 indices 指定的增量操作后，返回矩阵中 奇数值单元格 的数目。
//
// 
//
//示例 1：
//
//
//
//输入：m = 2, n = 3, indices = [[0,1],[1,1]]
//输出：6
//解释：最开始的矩阵是 [[0,0,0],[0,0,0]]。
//第一次增量操作后得到 [[1,2,1],[0,1,0]]。
//最后的矩阵是 [[1,3,1],[1,3,1]]，里面有 6 个奇数。
//示例 2：
//
//
//
//输入：m = 2, n = 2, indices = [[1,1],[0,0]]
//输出：0
//解释：最后的矩阵是 [[2,2],[2,2]]，里面没有奇数。
// 
//
//提示：
//
//1 <= m, n <= 50
//1 <= indices.length <= 100
//0 <= ri < m
//0 <= ci < n
// 
//
//进阶：你可以设计一个时间复杂度为 O(n + m + indices.length) 且仅用 O(n + m) 额外空间的算法来解决此问题吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/cells-with-odd-values-in-a-matrix
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 奇数值单元格的数目 {
    public int oddCells(int m, int n, int[][] indices) {
        //奇数+偶数=奇数
        //偶数+奇数=奇数
        //1.用两个数组，分别统计每一行的累加值和每一列的累加值
        //2.遍历每一行的值，计算出m行中，奇数的行数odd和偶数的行数event
        //3.遍历每一列，如果当前列的累加值是奇数，那么需要和累加值是偶数的那几行相加，其中的结果才会为奇数，反之需要和累加值是奇数的那几行相加

        int[] row = new int[m];
        int[] col = new int[n];
        for (int[] ints : indices) {
            row[ints[0]]++;
            col[ints[1]]++;
        }
        int odd = 0;
        int even = 0;
        for (int i = 0; i < m; i++) {
            if (row[i] % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            if (col[i] % 2 == 0) {
                result += odd;
            } else {
                result += even;
            }
        }

        return result;
    }
}
