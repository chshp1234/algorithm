package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

public class 三角形的最大周长 {
    @Test
    public void 三角形的最大周长() {
        System.out.println("三角形的最大周长:" + largestPerimeter(new int[]{3, 6, 2, 3}));
    }

    public int largestPerimeter(int[] A) {
        // 贪心 + 排序
        // 首先对数组进行排序
        // 然后倒序遍历数组，依次枚举最大边：
        // 若其前两个数之和大于这条最大边，则即可返回这三个数组成的最大周长。就算在往前周长也符合条件，但肯定不是最大的。
        // 若其前两个数之和小于等于这条最大边，那么在往前的数只会更小，所以，不必计算，直接遍历下一条最大边。
        // 遍历结束没得到答案，返回0；
        int len = A.length;
        Arrays.sort(A);
        for (int i = len - 1; i >= 2; i--) {
            if ((A[i - 2] + A[i - 1]) > A[i]) {
                return A[i - 2] + A[i - 1] + A[i];
            }
        }
        return 0;
    }
}
