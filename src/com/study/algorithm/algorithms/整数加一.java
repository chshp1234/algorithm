package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

public class 整数加一 {
    @Test
    public void 加一() {
        System.out.println("整数数组：" + Arrays.toString(new int[] {9, 9, 9, 9, 9, 9}));
        System.out.println("整数加一：" + Arrays.toString(plusOne(new int[] {9, 9, 9, 9, 9, 9})));
    }
    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     *
     * <p>最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
     *
     * <p>你可以假设除了整数 0 之外，这个整数不会以零开头。
     */
    public int[] plusOne(int[] digits) {
        boolean plus = true;
        for (int n = digits.length - 1; n > -1; n--) {
            if (plus) {
                if (digits[n] == 9) {
                    digits[n] = 0;
                } else {
                    digits[n] = digits[n] + 1;
                    plus = false;
                }
            } else {
                break;
            }
        }
        if (plus) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            /*for (int i = 1, l = result.length; i < l; i++) {
                result[i] = 0;
            }*/
            return result;
        }
        return digits;
    }
}
