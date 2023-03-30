package com.study.algorithm.algorithms;

import org.junit.Test;

public class 汉明距离 {

    @Test
    public void 汉明距离() {
        int x = 1, y = 4;
        System.out.printf("%d和%d的汉明距离:" + hammingDistance(x, y), x, y);
    }

    // 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
    //
    // 给出两个整数 x 和 y，计算它们之间的汉明距离。
    //
    // 注意：
    // 0 ≤ x, y < 231.
    //
    // 示例:
    //
    // 输入: x = 1, y = 4
    //
    // 输出: 2
    //
    // 解释:
    // 1   (0 0 0 1)
    // 4   (0 1 0 0)
    //       ↑   ↑
    //
    // 上面的箭头指出了对应二进制位不同的位置。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/hamming-distance
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int hammingDistance(int x, int y) {
        // 换句话说，它就是将一个字符串变换成另外一个字符串所需要替换的字符个数。

        // 将两数异或（相同位为0，不同位为1），两数中不同位的结果为1
        int tem = x ^ y;

        // 只要结果中“1”出现的次数即为两数的汉明距离
        int count = 0;

        if (tem > 0) {
            count++;
        }

        while ((tem = (tem & (tem - 1))) != 0) {
            count++;
        }

        return count;
    }
}
