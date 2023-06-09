package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
//
// 
//
//示例 1：
//
//输入：00000000000000000000000000001011
//输出：3
//解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
//示例 2：
//
//输入：00000000000000000000000010000000
//输出：1
//解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
//示例 3：
//
//输入：11111111111111111111111111111101
//输出：31
//解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
// 
//
//提示：
//
//输入必须是长度为 32 的 二进制串 。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 二进制中1的个数 {
    @Test
    public void 二进制中1的个数() {
        System.out.println("二进制中1的个数:" + hammingWeight(0));
    }

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        //位运算
        //1.右移
        //若n不为0，判断最低位是否为1，再对n进行右移1位操作，重复判断。
        //2.与
        //若n不为0，对n = n & (n - 1)，因为n-1后，最低位1右边位全为1，最低位1变为0，两数与操作后就将消除最低位1
        //直到所有‘1’位消除，也就是值为0时，跳出循环
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }
}
