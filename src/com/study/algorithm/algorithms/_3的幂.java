package com.study.algorithm.algorithms;

public class _3的幂 {
    public boolean isPowerOfThree(int n) {
        //将n递归的除以3，直到n=1，并且期间余数都为0，那么n就是3的幂，如果期间余数不为0，那么n就不是3的幂
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        return n % 3 == 0 && isPowerOfThree(n / 3);
    }

    public boolean isPowerOfThreeByLeetCode(int n) {
        //我们还可以使用一种较为取巧的做法。
        //
        //在题目给定的 32 位有符号整数的范围内，最大的 3 的幂为 3^{19} = 1162261467。我们只需要判断 n 是否是 3^{19}的约数即可。
        //
        //与方法一不同的是，这里需要特殊判断 n 是负数或 0 的情况。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/power-of-three/solution/3de-mi-by-leetcode-solution-hnap/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        return n > 0 && 1162261467 % n == 0;
    }

}
