package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 平方数之和 {

    @Test
    public void 平方数之和() {
        System.out.println("是否是俩平方数之和：" + judgeSquareSumFermat(101));
    }

    // 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。
    //
    // 示例1:
    //
    // 输入: 5
    // 输出: True
    // 解释: 1 * 1 + 2 * 2 = 5
    //
    //
    // 示例2:
    //
    // 输入: 3
    // 输出: False
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/sum-of-square-numbers
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean judgeSquareSum(int count) {
        double temp  = Math.sqrt(count);
        int    first = (int) temp;
        if (first == temp) {
            return true;
        }
        double second;
        for (int i = 1; i <= first; i++) {
            second = Math.sqrt(count - i * i);
            if ((int) second == second) {
                return true;
            }
        }

        return false;
    }

    // 方法三：费马平方和定理
    // 费马平方和定理告诉我们：
    //
    // 一个非负整数 cc 能够表示为两个整数的平方和，当且仅当 cc 的所有形如 4k+34k+3 的质因子的幂次均为偶数。
    //
    // 证明方法可以见 这里。
    //
    // 因此我们对 cc 进行质因数分解，再判断形如 4k+34k+3 的质因子的幂次是否均为偶数即可。
    //
    // 作者：LeetCode
    // 链接：https://leetcode-cn.com/problems/sum-of-square-numbers/solution/ping-fang-shu-zhi-he-by-leetcode/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public boolean judgeSquareSumFermat(int c) {
        for (int i = 2; i * i <= c; i++) {
            int count = 0;
            if (c % i == 0) {
                while (c % i == 0) {
                    count++;
                    c /= i;
                }
                if (i % 4 == 3 && count % 2 != 0) return false;
            }
        }
        return c % 4 != 3;
    }

    public boolean judgeSquareSumByTwoPointer(int c) {
        //不失一般性，可以假设 a≤b。初始时 a=0，b = sqrt{c}，进行如下操作：
        //
        //如果 a^2 + b^2 = c，我们找到了题目要求的一个解，返回 true；
        //如果 a^2 + b^2 < c，此时需要将 a 的值加 1，继续查找；
        //如果 a^2 + b^2 > c，此时需要将 b 的值减 1，继续查找。
        //当 a=b 时，结束查找，此时如果仍然没有找到整数 a 和 b 满足 a^2 + b^2 = c，则说明不存在题目要求的解，返回 false。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/sum-of-square-numbers/solution/ping-fang-shu-zhi-he-by-leetcode-solutio-8ydl/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int left  = 0;
        int right = (int) Math.sqrt(c);
        while (left <= right) {
            int sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }

    public boolean judgeSquareSumByFM(int c) {
        //费马平方和定理告诉我们：
        //
        //一个非负整数 c 如果能够表示为两个整数的平方和，当且仅当 c 的所有形如 4k + 3 的质因子的幂均为偶数。
        //
        //证明请见 这里。
        //
        //因此我们需要对 c 进行质因数分解，再判断所有形如 4k + 3的质因子的幂是否均为偶数即可。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/sum-of-square-numbers/solution/ping-fang-shu-zhi-he-by-leetcode-solutio-8ydl/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        for (int base = 2; base * base <= c; base++) {
            // 如果不是因子，枚举下一个
            if (c % base != 0) {
                continue;
            }

            // 计算 base 的幂
            int exp = 0;
            while (c % base == 0) {
                c /= base;
                exp++;
            }

            // 根据 Sum of two squares theorem 验证
            if (base % 4 == 3 && exp % 2 != 0) {
                return false;
            }
        }

        // 例如 11 这样的用例，由于上面的 for 循环里 base * base <= c ，base == 11 的时候不会进入循环体
        // 因此在退出循环以后需要再做一次判断
        return c % 4 != 3;
    }
}
