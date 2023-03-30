package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

public class 回文子串 {
    @Test
    public void 回文子串() {
        System.out.println("回文子串" + countSubstrings("wgww"));
    }

    // 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
    //
    // 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
    //
    //
    //
    // 示例 1：
    //
    // 输入："abc"
    // 输出：3
    // 解释：三个回文子串: "a", "b", "c"
    // 示例 2：
    //
    // 输入："aaa"
    // 输出：6
    // 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
    //
    //
    // 提示：
    //
    // 输入的字符串长度不会超过 1000 。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/palindromic-substrings
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int countSubstrings(String s) {

        int len = s.length();

        // 一个二维数组（可以用满二维数组，代表位置第i~j时的子串是否是回文串，可优化），总共两层（滚动数组），分别表示前两个数为中间子串时是否是回文串：
        // 1.比如当前判断4位数的子串是否为回文串，需要知道中间的两个字符是否为回文串（数组第一层），再判断收尾两个字符是否相等
        // 2.判断结束，若此4位数的子串是回文串，则更新数组，此时第一层就为3位数时是否是回文串（原先的数组第二层），第二层就为此时（4位数时）是否是回文串
        boolean[][] dp = new boolean[2][len];

        // 单个字符肯定是回文串
        Arrays.fill(dp[0], true);

        int result = len;

        // 两个字符时只需要判断收尾字符是否相等
        for (int i = 0, l = len - 1; i < l; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[1][i] = true;
                result++;
                System.out.println("回文子串" + s.substring(i, i + 2));
            }
        }

        for (int l = 3; l <= len; l++) {
            for (int i = 0, il = len - l + 1; i < il; i++) {

                boolean temp = dp[1][i];

                // 判断此子串的中间子串是否是回文串，并且此子串收尾字符是否相等
                if (dp[0][i + 1] && s.charAt(i) == s.charAt(i + l - 1)) {
                    dp[1][i] = true;
                    result++;
                    System.out.println("回文子串" + s.substring(i, i + l));
                } else {
                    dp[1][i] = false;
                }

                dp[0][i] = temp;
            }
        }

        return result;
    }
}
