package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

@Deprecated
public class 表示数值的字符串 {
    @Test
    public void 表示数值的字符串() {
        System.out.println("表示数值的字符串:" + isNumber("10101"));
    }

    // 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
    // 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，
    // 但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean isNumber(String s) {
        boolean decimals = false;
        boolean index = false;

        for (int i = 0, l = s.length(); i < l; i++) {
            if (decimals) {
                if (s.charAt(i) == '.') {
                    return false;
                }

                if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                    return false;
                }
            }
        }

        return false;
    }
}
