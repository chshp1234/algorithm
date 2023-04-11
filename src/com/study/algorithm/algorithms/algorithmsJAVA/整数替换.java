package com.study.algorithm.algorithms.algorithmsJAVA;

@Deprecated
public class 整数替换 {
    public int integerReplacement(int n) {
        int count = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n--;
            }
            count++;
        }
        return count;
    }
}
