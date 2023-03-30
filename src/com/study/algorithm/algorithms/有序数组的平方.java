package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

public class 有序数组的平方 {
    @Test
    public void 有序数组的平方() {
        int[] matrix = {-4, -1, 0, 3, 10};

        System.out.println("有序数组的平方：" + Arrays.toString(sortedSquares(matrix)));
    }

    // 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
    //
    //
    //
    // 示例 1：
    //
    // 输入：[-4,-1,0,3,10]
    // 输出：[0,1,9,16,100]
    // 示例 2：
    //
    // 输入：[-7,-3,2,3,11]
    // 输出：[4,9,9,49,121]
    //
    //
    // 提示：
    //
    // 1 <= A.length <= 10000
    // -10000 <= A[i] <= 10000
    // A 已按非递减顺序排序。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int[] sortedSquares(int[] A) {
        // 高低位双指针
        // 判断高低位元素绝对值的大小，大的值优先填入目标数组，填入顺序从后往前（从大到小）

        if (A == null) {
            return null;
        }

        int low = 0, high = A.length - 1, index = A.length - 1;

        int[] result = new int[A.length];

        while (low <= high) {

            if (Math.abs(A[low]) < Math.abs(A[high])) {
                result[index] = (int) Math.pow(A[high], 2);
                high--;
            } else {
                result[index] = (int) Math.pow(A[low], 2);
                low++;
            }
            index--;
        }
        return result;
    }
}
