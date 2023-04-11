package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class 快乐数 {

    @Test
    public void 快乐数() {
        System.out.println("快乐数：" + isHappy(3));
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();

        int result = n;

        while (true) {
            result = add(result);
            if (result == 1) {
                return true;
            } else if (!set.add(result)) {
                return false;
            }
        }
    }

    public int add(int n) {
        int result = 0;
        int temp;
        while (n > 0) {
            temp = n % 10;
            result += Math.pow(temp, 2);
            n = n / 10;
        }
        return result;
    }
}
