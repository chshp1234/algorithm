package com.study.algorithm.algorithms;

import org.junit.Test;

public class 最长回文子串 {

    @Test
    public void 最长回文子串() {

        System.out.println("最长回文子串：" + longestPalindrome("cbbd"));
        //        System.out.println("有效括号的嵌套深度：" + calculateDepth(seq, seq.length()));
    }

    //    public String longestPalindrome(String s) {
    //        if (s == null || s.length() == 0) {
    //            return "";
    //        }
    //
    //        // 记录当前最大回文串低位和高位
    //        int low = 0, high = 0;
    //        int len = s.length();
    //
    //        // 标识字符串区间n~m是否为回文串
    //        boolean[][] isPalindrome = new boolean[len][len];
    //
    //        // 赋予初始值
    //        isPalindrome[0][0] = true;
    //        for (int i = 1; i < len; i++) {
    //            // 单个字符肯定为回文串
    //            isPalindrome[i][i] = true;
    //            // 方便后续计算，区间i~(i-1)也设为true
    //            isPalindrome[i][i - 1] = true;
    //        }
    //
    //        int temp;
    //        for (int i = 1; i < len; i++) {
    //
    //            for (int k = 0; k < i; k++) {
    //                // 若字串中位置k和i位置的字符相等，并且区间(k+1)~(i-1)为回文串，则区间k~i也为回文串
    //                if (s.charAt(k) == s.charAt(i) && isPalindrome[k + 1][i - 1]) {
    //                    isPalindrome[k][i] = true;
    //                    temp = i - k + 1;
    //                    if (temp > (high - low + 1)) {
    //                        low = k;
    //                        high = i;
    //                    }
    //                } else {
    //                    isPalindrome[k][i] = false;
    //                }
    //            }
    //        }
    //
    //        // 返回字符串区间low~（high+1）
    //        return s.substring(low, high + 1);
    //    }

    // 一维数组实现
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        // 记录当前最大回文串低位和高位
        int low = 0, high = 0;
        int len = s.length();

        // 标识字符串区间n~m是否为回文串
        boolean[] isPalindrome = new boolean[len];

        int temp;

        // 根据判断，当判断区间n~m是否为回文串时，只需要判断位置n和m的位置是否相等，并且区间(n+1)~(m-1)为回文串
        // 固可用一个一位数组isPalindrome[n+1]表示区间(n+1)~(m-1)是否为回文串
        // 每次循环判断时更新isPalindrome[n]的值，供下一次循环时的判断依据，而不用关系更早之前的状态
        for (int i = 1; i < len; i++) {

            for (int k = 0, kl = Math.min((2 * len - high + low - i - 2), i) /*剪枝，根据当前最大区间(根据图表规律发现)，下一次循环判断并不需要（和i相比）判断当前所有区间的回文*/; k < kl; k++) {
                // 若字串中位置k和i位置的字符相等，并且区间(k+1)~(i-1)为回文串，则区间k~i也为回文串
                if (s.charAt(k) == s.charAt(i)) {

                    // 若字符区间大小小于3，则当前为回文串
                    if (i - k <= 2 || isPalindrome[k + 1]) {
                        isPalindrome[k] = true;
                        temp = i - k + 1;
                        if (temp > (high - low + 1)) {
                            low = k;
                            high = i;
                        }
                    } else if (!isPalindrome[k + 1]) {
                        isPalindrome[k] = false;
                    }

                } else {
                    isPalindrome[k] = false;
                }
            }
        }

        // 返回字符串区间low~（high+1）
        return s.substring(low, high + 1);
    }
}
