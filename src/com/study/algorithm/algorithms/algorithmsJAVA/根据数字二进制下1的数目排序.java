package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

public class 根据数字二进制下1的数目排序 {

    @Test
    public void 根据数字二进制下1的数目排序() {
        int[] pillars = {2, 3, 5, 7, 11, 13, 17, 19};

        System.out.println("根据数字二进制下1的数目排序:" + Arrays.toString(sortByBits(pillars)));
        //        System.out.println("根据数字二进制下1的数目排序:" + countBits(5));
        System.out.println("根据数字二进制下1的数目排序:" + Integer.bitCount(579));
    }

    public int[] sortByBits(int[] arr) {

        // 方法一：
        // 映射出数组中每个数字对应的二进制中‘1’的个数
        // （1）.用哈希表进行映射
        // （2）.创建一个二维数组，里层第一位为原值，第二位为二进制中‘1’的个数
        // 根据题意排序，并把排序后的值填充原数组即可

        // 方法二：
        // 对每个十进制的数转二进制的时候统计一下 1 的个数即可。
        // 我们定义 bit[i] 为数字 i 二进制表示下数字 1 的个数，则可以列出递推式：
        //
        // bit[i]=bit[i>>1]+(i&1)
        //
        // 所以我们线性预处理 bit 数组然后去排序即可。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits/solution/gen-ju-shu-zi-er-jin-zhi-xia-1-de-shu-mu-pai-xu-by/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        int len = arr.length;

        int[][] temps = new int[len][2];

        for (int i = 0; i < len; i++) {
            temps[i][0] = arr[i];
            temps[i][1] = countBits(arr[i]);
        }

        Arrays.sort(
                temps,
                (o1, o2) -> {
                    if (o1[1] == o2[1]) {
                        return o1[0] - o2[0];
                    }
                    return o1[1] - o2[1];
                });

        for (int i = 0; i < len; i++) {
            arr[i] = temps[i][0];
        }
        return arr;
    }

    public int countBits(int num) {

        // 计算二进制下‘1’的个数
        int count = 0;

        while (num > 0) {
            count++;
            num = (num & (num - 1));
        }

        /*int res = 0;
        while (x != 0) {
            res += x % 2;
            x /= 2;
        }*/

        /*int count = 0; // 计数器
        while (n > 0) {
            if ((n & 1) == 1) count++; // 当前位是1，count++
            n >>= 1; // n向右移位
        }*/

        //        Integer.bitCount(num);

        return count;
    }
}
