package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 回文数 {

    @Test
    public void 回文数() {
        int n = 1001;
        System.out.printf("%d是否为回文数:" + isPalindrome(n), n);
    }

    // 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
    //
    // 示例 1:
    //
    // 输入: 121
    // 输出: true
    // 示例 2:
    //
    // 输入: -121
    // 输出: false
    // 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
    // 示例 3:
    //
    // 输入: 10
    // 输出: false
    // 解释: 从右向左读, 为 01 。因此它不是一个回文数。
    // 进阶:
    //
    // 你能不将整数转为字符串来解决这个问题吗？
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/palindrome-number
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    /*public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        // 反转数字int，若一个数字反转后与其本身相等，则说明是回文数字。
        int reverse = 0;
        int temp = x;
        while (temp > 0) {
            // 反转的数字*10再加上原数除10取余
            reverse = reverse * 10 + temp % 10;
            temp = temp / 10;
        }
        return reverse == x;
    }*/

    /*public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int bit = 1;

        // 首先判断位数，
        // 然后判断数字最高位和最低位是否相等，判断结束后，位数-2，且原数 x = x % temp / 10（除去最高位和最低位），再继续进行判断
        while ((int) (x / Math.pow(10, bit)) > 0) {
            bit++;
        }

        --bit;
        int temp;
        while (bit > 0) {
            temp = (int) Math.pow(10, bit);
            if ((x / temp) != x % 10) {
                return false;
            }
            x = x % temp / 10;
            bit -= 2;
        }
        return true;
    }*/

    public boolean isPalindrome(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        // 将数字本身反转，然后将反转后的数字与原始数字进行比较，如果它们是相同的，那么这个数字就是回文。
        // 但是，如果反转后的数字大于 \text{int.MAX}int.MAX，我们将遇到整数溢出问题。
        //
        // 按照第二个想法，为了避免数字反转可能导致的溢出问题，为什么不考虑只反转 int
        // 数字的一半？毕竟，如果该数字是回文，其后半部分反转后应该与原始数字的前半部分相同。
        // 由于整个过程我们不断将原始数字除以 10，然后给反转后的数字乘上 10，
        // 所以，当原始数字小于或等于反转后的数字时，就意味着我们已经处理了一半位数的数字了。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/palindrome-number/solution/hui-wen-shu-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }
}
