package com.study.algorithm.algorithms;

import org.junit.Test;

public class 字符串相加 {

    @Test
    public void 字符串相加() {
        System.out.println("字符串相加:" + addStrings("6994", "36"));
    }

    // 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
    //
    // 注意：
    //
    // num1 和num2 的长度都小于 5100.
    // num1 和num2 都只包含数字 0-9.
    // num1 和num2 都不包含任何前导零。
    // 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/add-strings
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public String addStrings(String num1, String num2) {
        if (num1.length() < num2.length()) {
            return addStrings(num2, num1);
        }

        // 本题我们只需要对两个大整数模拟「竖式加法」的过程。竖式加法就是我们平常学习生活中常用的对两个整数相加的方法，
        // 回想一下我们在纸上对两个整数相加的操作，是不是如下图将相同数位对齐，从低到高逐位相加，如果当前位和超过 1010，
        // 则向高位进一位？因此我们只要将这个过程用代码写出来即可。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/add-strings/solution/zi-fu-chuan-xiang-jia-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int len1 = num1.length(), len2 = num2.length();

        StringBuilder stringBuilder = new StringBuilder();

        int carry = 0;
        int char1 = 0, char2 = 0;
        int sum = 0;
        for (int i = 1; i <= len2; i++) {
            char1 = num1.charAt(len1 - i) - 48;
            char2 = num2.charAt(len2 - i) - 48;

            sum += carry + char1 + char2;

            if (sum > 9) {
                carry = 1;
                sum = sum - 10;
            } else {
                carry = 0;
            }

            stringBuilder.insert(0, sum);
            sum = 0;
        }

        int diff = len1 - len2 - 1;

        while (diff >= 0) {
            char1 = num1.charAt(diff) - 48;

            sum += carry + char1;

            if (sum > 9) {
                carry = 1;
                sum = sum - 10;
            } else {
                carry = 0;
            }

            stringBuilder.insert(0, sum);
            diff--;
            sum = 0;
        }

        if (carry > 0) {
            stringBuilder.insert(0, carry);
        }

        // 这里我们统一在指针当前下标处于负数的时候返回 0，等价于对位数较短的数字进行了补零操作，
        // 这样就可以除去两个数字位数不同情况的处理，
        /*while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }*/

        return stringBuilder.toString();
    }
}
