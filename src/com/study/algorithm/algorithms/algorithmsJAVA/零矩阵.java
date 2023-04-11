package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

@Deprecated
public class 零矩阵 {

    @Test
    public void 零矩阵() {
        int[][] ints = new int[][] {{1, 2, 3}, {4, 0, 6}, {7, 8, 9}};
        setZeroes(ints);
        System.out.println("零矩阵:" + Arrays.deepToString(ints));
    }

    // 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
    //
    //
    //
    // 示例 1：
    //
    // 输入：
    // [
    //  [1,1,1],
    //  [1,0,1],
    //  [1,1,1]
    // ]
    // 输出：
    // [
    //  [1,0,1],
    //  [0,0,0],
    //  [1,0,1]
    // ]
    // 示例 2：
    //
    // 输入：
    // [
    //  [0,1,2,0],
    //  [3,4,5,2],
    //  [1,3,1,5]
    // ]
    // 输出：
    // [
    //  [0,0,0,0],
    //  [0,4,5,0],
    //  [0,3,1,0]
    // ]
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/zero-matrix-lcci
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    public void setZeroes(int[][] matrix) {}
}
