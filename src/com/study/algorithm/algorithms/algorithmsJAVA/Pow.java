package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class Pow {

    @Test
    public void Pow() {
        System.out.println("Pow:" + myPow(2, 10));
    }

    // 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
    //
    // 示例 1:
    //
    // 输入: 2.00000, 10
    // 输出: 1024.00000
    // 示例 2:
    //
    // 输入: 2.10000, 3
    // 输出: 9.26100
    // 示例 3:
    //
    // 输入: 2.00000, -2
    // 输出: 0.25000
    // 解释: 2-2 = 1/22 = 1/4 = 0.25
    // 说明:
    //
    // -100.0 < x < 100.0
    // n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/powx-n
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public double myPow(double x, int n) {
        if (x == 0 || x == 1 || n == 1) {
            return x;
        }
        if (n == 0) {
            return 1;
        }
        long N = n;
        return n < 0 ? 1 / pow(x, -N) : pow(x, N);
    }

    // 1.先计算出小于当前数字的最大二次幂
    // 2.对当前x进行每次平方操作
    // 3.进一步计算，幂为Math.abs(n) - count
    // 例：2^7=2^4*2^3——>2^4*2^2*2^1
    public double pow(double x, long n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }

        // 保存历史
        double temp = x;
        long pow = n;

        long count = pow;

        // 计算小于当前数字的最大二次幂
        while (true) {
            count &= (count - 1);
            if (count > 0) {
                pow = count;
            } else {
                count = pow;
                break;
            }
        }

        while (pow > 1) {
            x *= x;
            pow = pow >> 1;
        }

        // 进一步计算，幂为Math.abs(n) - count
        x *= pow(temp, n - count);

        return x;
    }
}
