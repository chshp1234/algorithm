package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 数字范围按位与 {
    @Test
    public void 数字范围按位与() {
        System.out.println("数字范围按位与:" + rangeBitwiseAnd(8, 12));
    }

    // 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
    //
    // 示例 1:
    //
    // 输入: [5,7]
    // 输出: 4
    // 示例 2:
    //
    // 输入: [0,1]
    // 输出: 0
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/bitwise-and-of-numbers-range
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int rangeBitwiseAnd(int m, int n) {

        // 思路一：暴力迭代，此解肯定没问题，但作为一个题目肯定会超出时间限制
        // 思路二：最长公共前缀（二进制下），在公共前缀的后一位开始（后面每一位），从较小的数向大数迭代的过程中，肯定会遇到0，在与运算中，遇0则0。
        // 我们可以消除大数的最右侧‘1’位，当小于等于小数时，此数则为最长公共前缀，也就是从此数开始迭代到大数时，所有数的逻辑与操作都为此数（因为此数在这个最长公共前缀后面的数都为0）。
        // 思路三：从小数往大数迭代，根据规律可以发现，最右侧‘1’位后面无论怎么填充，逻辑与操作都为0，比如001000（8）~001111（15），8&15=8.
        // 所以可以据此对小数进行跨度迭代，每次加上的数为末尾‘1’位后面的‘0’位都填充为‘1’，比如例子8加到15，若15还是比大数小，则15+1，10000（16），在进行下一轮跨度迭代操作，知道超出大数。

        // 思路三解法（自解）
        int result = m;
        while (m > 0 && (m = result | (result - 1)) < n) {
            m++;
            result = result & m;
        }
        return result;
    }

    // 思路二解法（官解）
    /*public int rangeBitwiseAnd(int m, int n) {
        while (m < n) {
            // 抹去最右边的 1
            n = n & (n - 1);
        }
        return n;
    }*/
}
