package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

//给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
//
//水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
//
//反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
//
//示例 1:
//
//输入: [[1,1,0],[1,0,1],[0,0,0]]
//输出: [[1,0,0],[0,1,0],[1,1,1]]
//解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
//     然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
//示例 2:
//
//输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
//输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
//解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
//     然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
//说明:
//
//1 <= A.length = A[0].length <= 20
//0 <= A[i][j] <= 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/flipping-an-image
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 翻转图像 {

    @Test
    public void 翻转图像() {
        int[][] ints = new int[][]{{1, 0, 0, 1}, {1, 1, 1, 0}, {0, 0, 1, 1}};
        System.out.println("翻转图像:" + Arrays.deepToString(flipAndInvertImage(ints)));
    }

    public int[][] flipAndInvertImage(int[][] A) {
        //位运算，异或
        //1.双指针对每一行进行逆序排序，异或交换两个指针处的元素。
        //2.异或（^1）使元素0转换成1，元素1转换成0。
        //注意列数为奇数时，中间的元素不进行交换，但也要进行转换。
        //优化：观察可得，若两个指针处元素不想同，则不必进行操作，因为交换+反正后，又会得到原元素本身。
        //所以，我们只对相同的两个元素进行操作，而相同的元素，交换后还是相同，所以只需进行反正即可。
        int row = A.length;
        int col = A[0].length - 1;
        for (int r = 0; r < row; r++) {
            int start = 0;
            int end   = col;
            while (start < end) {

                //只需对相同的两个元素进行反转即可
                if (A[r][start]==A[r][end]){
                    A[r][start] ^= 1;
                    A[r][end] ^= 1;
                }

                //1.异或3次交换俩元素
                /*A[r][start] ^= A[r][end];
                A[r][end] ^= A[r][start];
                A[r][start] ^= A[r][end];

                //2.异或1，反正元素
                A[r][start] ^= 1;
                A[r][end] ^= 1;*/

                start++;
                end--;
            }
            if (start == end) {
                A[r][start] ^= 1;
            }
        }
        return A;
    }

}
