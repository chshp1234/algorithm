package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 将数组拆分成斐波那契序列 {
    @Test
    public void 将数组拆分成斐波那契序列() {
        System.out.println("将数组拆分成斐波那契序列:" + splitIntoFibonacci("808816244064104168"));
    }

    List<Integer> result = new ArrayList<>();

    int len;

    public List<Integer> splitIntoFibonacci(String S) {

        // 回溯+剪枝
        // 使用回溯算法分割字符串，拆分成符合条件（斐波那契数列）的子串。
        // 其实回溯改变的只有前两个数字，因为当前两个数确定了，后面所有的数都将确定。
        // 两个数字相加，和的长度不会超过两个数中的最大长度+1。
        // 因为最少需要分割成3个子串，且两个数相加等于第三个数，所以一个子串最大长度不会超过(len - 1) / 2，且都是是int类型，不会超过10位。
        // 如果拆分时第一个数是‘0’，则只能有1位的长度。
        // 根据前面得到的“最大数字长度”，若字符串剩余字符大小小于最大数字的长度，则最后一个数字肯定不会由前面两个数相加得到。

        len = S.length();
        int maxLen = S.charAt(0) == '0' ? 1 : Math.min((len - 1) / 2, 10);
        StringBuilder s1 = new StringBuilder();
        int maxCount;
        for (int i = 1; i <= maxLen; i++) {
            s1.append(S.charAt(i - 1));
            long i1 = Long.parseLong(s1.toString());
            if (i1 > Integer.MAX_VALUE) {
                continue;
            }
            result.add((int) i1);
            maxCount = S.charAt(i) == '0' ? 1 : len - 2 * i < i ? len - 2 * i : (len - i) / 2;
            maxCount = Math.min(maxCount, 10);
            StringBuilder s2 = new StringBuilder();
            for (int j = 1; j <= maxCount; j++) {
                int index = i + j - 1;
                s2.append(S.charAt(index));
                long i2 = Long.parseLong(s2.toString());
                if (i2 > Integer.MAX_VALUE) {
                    continue;
                }
                result.add((int) i2);
                if (backTrack((int) i1, (int) i2, Math.max(i, j), 2, index + 1, S)) {
                    return result;
                }
                result.remove(1);
            }
            result.remove(0);
        }

        return result;
    }

    public boolean backTrack(int one, int two, int maxCount, int num, int index, String S) {
        if (index == len) {
            return true;
        }
        if (len - index < maxCount) {
            return false;
        }

        StringBuilder stringBuilder = new StringBuilder();

        int count = S.charAt(index) == '0' ? 1 : maxCount;

        for (int i = 0; i < count; i++) {
            stringBuilder.append(S.charAt(index + i));
        }
        long three = Long.parseLong(stringBuilder.toString());
        if (three > Integer.MAX_VALUE) {
            return false;
        }
        int preSum = one + two;
        if (preSum == three) {
            result.add((int) three);
            if (!backTrack(two, (int) three, maxCount, num + 1, index + maxCount, S)) {
                result.remove(num);
                return false;
            }
            return true;
        } else if (preSum < three) {
            return false;
        } else {
            if (maxCount == 10) {
                return false;
            }
            if (index + maxCount < len) {
                three = Long.parseLong(stringBuilder.append(S.charAt(index + maxCount)).toString());
                if (three > Integer.MAX_VALUE) {
                    return false;
                }
                if (preSum == three) {
                    result.add((int) three);
                    if (!backTrack(
                            two, (int) three, maxCount + 1, num + 1, index + maxCount + 1, S)) {
                        result.remove(num);
                        return false;
                    }
                    return true;
                }
            }
            return false;
        }
    }
}
