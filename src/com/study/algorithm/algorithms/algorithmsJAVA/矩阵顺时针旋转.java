package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

public class 矩阵顺时针旋转 {
    @Test
    public void 旋转图像() {
        int[][] ints = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("原始矩阵：" + Arrays.deepToString(ints));
        rotate(ints);
        System.out.println("旋转矩阵：" + Arrays.deepToString(ints));
        int r = ints.length, c = ints[0].length;
        /*for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.println(result[i][j] + "");
            }
        }*/
    }

    /**
     * 给定一个 n × n 的二维矩阵表示一个图像。
     *
     * <p>将图像顺时针旋转 90 度。
     *
     * <p>说明：
     *
     * <p>你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     *
     * <p>示例 1:
     *
     * <p>给定 matrix = [ [1,2,3], [4,5,6], [7,8,9] ],
     *
     * <p>原地旋转输入矩阵，使其变为: [ [7,4,1], [8,5,2], [9,6,3] ] 示例 2:
     *
     * <p>给定 matrix = [ [ 5, 1, 9,11], [ 2, 4, 8,10], [13, 3, 6, 7], [15,14,12,16] ],
     *
     * <p>原地旋转输入矩阵，使其变为: [ [15,13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7,10,11] ]
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/rotate-image
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param matrix the matrix
     * @return the int [ ] [ ]
     */
    public int[][] rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[0][];
        }
        /*int[][] result = new int[matrix.length][matrix[0].length];
        int r = matrix.length - 1;
        for (int i = 0; i < r + 1; i++) {
            for (int j = 0; j < r + 1; j++) {

                result[i][j] = matrix[r - j][i];
            }
        }
        return result;*/

        //原地旋转
        //我们把整个矩阵图像看成从外到内的回形图像，旋转既是依次交换每一条“边”
        //最多旋转次数为n.length/2，当n为偶数时，每一条边都要旋转，当n为奇数时，最中间的一个点不用旋转。

        int maxIndex = matrix.length - 1;
        int temp;

        //第一层，要旋转的“回形”图像，最大为matrix.length / 2
        for (int i = 0, l = matrix.length / 2; i < l; i++) {
            int k = maxIndex - 2 * i;
            //第二层，对每一层“回形”图像的边进行旋转交换
            for (int j = 0; j < k; j++) {
                temp = matrix[i][i + j];
                matrix[i][i + j] = matrix[maxIndex - (i + j)][i];
                matrix[maxIndex - (i + j)][i] = matrix[maxIndex - i][maxIndex - (i + j)];
                matrix[maxIndex - i][maxIndex - (i + j)] = matrix[i + j][maxIndex - i];
                matrix[i + j][maxIndex - i] = temp;
            }
        }
        return matrix;
    }
}
