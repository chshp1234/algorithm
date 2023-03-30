package com.study.algorithm.algorithms;

import org.junit.Test;

public class x的平方根 {

    @Test
    public void x的平方根() {
        System.out.println("x的平方根:" + mySqrt(5));
    }

    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        long m = 0;

        int low = 1, high = x;
        long temp;
        while (low < high) {
            temp = low + ((high - low) >> 1);
            if (temp == m) {
                return (int) m;
            }
            m = temp;
            temp = m * m;
            if (temp == x) {
                return (int) m;
            } else if (temp < 0 || temp > x) {
                high = (int) m;
            } else {
                low = (int) m;
            }
        }

        return low;
    }

    // 袖珍计算器算法
    // 袖珍计算器算法」是一种用指数函数 exp 和对数函数 ln 代替平方根函数的方法。
    // 我们通过有限的可以使用的数学函数，得到我们想要计算的结果。
    public int mySqrt2(int x) {
        if (x == 0) {
            return 0;
        }
        int ans = (int) Math.exp(0.5 * Math.log(x));

        // 注意： 由于计算机无法存储浮点数的精确值（浮点数的存储方法可以参考 IEEE754，这里不再赘述），
        //而指数函数和对数函数的参数和返回值均为浮点数，因此运算过程中会存在误差。例如当x=2147395600时，e^1/2 lnx
        //
        //  的计算结果与正确值 46340 相差 10^{-11}
        // ，这样在对结果取整数部分时，会得到 46339 这个错误的结果。
        //
        // 因此在得到结果的整数部分 ans 后，我们应当找出 ans 与 ans+1 中哪一个是真正的答案。
        return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }

    // 牛顿迭代
    public int mySqrt3(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }
}
