package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test;

class 整数反转 {

    @Test
    fun 整数反转() {
        val num = -2147483647
        println("整数反转:原值=$num 翻转后=${reverse(num)}")
    }

    fun reverse(x: Int): Int {
        //注意int边界
        //翻转数字x，对于结果result，求num=x%10，result就为result*10+num
        //之后再把x=x/10，直到x为0为止
        if (x == 0 || x == Int.MIN_VALUE) {
            return 0
        }
        if (x < 0) {
            return -reverse(-x)
        }
        var num = x
        while (num % 10 == 0) {
            num /= 10
        }
        var result: Long = 0
        while (num != 0) {
            result = result * 10 + num % 10
            if (result > Int.MAX_VALUE) {
                return 0
            }
            num /= 10
        }
        return result.toInt()
    }

    /*public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }*/
}