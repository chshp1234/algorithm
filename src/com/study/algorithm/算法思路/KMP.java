package com.study.algorithm.算法思路;

import org.junit.Test;

public class KMP {
    @Test
    public void KMP() {
        System.out.println("KMP：" + getNext("aaabbab".toCharArray()));
    }

    private int[] getNext(char[] target) {
        int len = target.length;
        if (len == 0) {
            return new int[]{-1};
        }

        int[] next = new int[target.length];

        // j表示当前位置，k表示子串需比较的第一位。
        int j = 0, k = -1;
        next[0] = -1;
        while (j < len - 1) {
            if (k == -1 || target[j] == target[k]) {
                j++;
                k++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }

        return next;
    }
}
