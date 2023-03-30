package com.study.algorithm.algorithms;

import org.junit.Test;

public class 二进制求和 {
    @Test
    public void 二进制求和() {
        System.out.println("二进制求和:" + addBinary("100", "110010"));
    }
    /**
     * 给定两个二进制字符串，返回他们的和（用二进制表示）。
     *
     * <p>输入为非空字符串且只包含数字 1 和 0。
     *
     * <p>示例 1:输入: a = "11", b = "1" 输出: "100"
     *
     * <p>示例 2:输入: a = "1010", b = "1011" 输出: "10101"
     */
    public String addBinary(String a, String b) {
        int aLength = a.length();
        int bLength = b.length();
        int maxCount = Math.max(aLength, bLength);
        int minCount = Math.min(aLength, bLength);
//        char[] newChars = new char[maxCount + 1];
        StringBuilder stringBuilder = new StringBuilder();
        boolean isUpgrade = false;
        for (int i = 0; i < maxCount; i++) {
            if (i < minCount) {

                if (a.charAt(aLength - 1 - i) == 49 && b.charAt(bLength - 1 - i) == 49) {
                    if (isUpgrade) {
                        stringBuilder.append("1");
//                        newChars[maxCount - i] = '1';
                    } else {
                        stringBuilder.append("0");
//                        newChars[maxCount - i] = '0';
                    }

                    isUpgrade = true;
                } else if (a.charAt(aLength - 1 - i) == 48 && b.charAt(bLength - 1 - i) == 48) {
                    if (isUpgrade) {
                        stringBuilder.append("1");
//                        newChars[maxCount - i] = '1';
                    } else {
                        stringBuilder.append("0");
//                        newChars[maxCount - i] = '0';
                    }
                    isUpgrade = false;
                } else {
                    if (isUpgrade) {
                        stringBuilder.append("0");
//                        newChars[maxCount - i] = '0';
                        isUpgrade = true;
                    } else {
                        stringBuilder.append("1");
//                        newChars[maxCount - i] = '1';
                        isUpgrade = false;
                    }
                }

            } else if (aLength >= bLength) {
                if (a.charAt(aLength - 1 - i) == 49) {
                    if (isUpgrade) {
                        stringBuilder.append("0");
//                        newChars[maxCount - i] = '0';
                        isUpgrade = true;
                    } else {
                        stringBuilder.append("1");
//                        newChars[maxCount - i] = '1';
                        isUpgrade = false;
                    }
                } else {
                    if (isUpgrade){
                        stringBuilder.append("1");
                    }else {
                        stringBuilder.append("0");
                    }
//                    newChars[maxCount - i] = '0';
                    isUpgrade = false;
                }
            } else {
                if (b.charAt(bLength - 1 - i) == 49) {
                    if (isUpgrade) {
                        stringBuilder.append("0");
//                        newChars[maxCount - i] = '0';
                        isUpgrade = true;
                    } else {
                        stringBuilder.append("1");
//                        newChars[maxCount - i] = '1';
                        isUpgrade = false;
                    }
                } else {
                    if (isUpgrade){
                        stringBuilder.append("1");
                    }else {
                        stringBuilder.append("0");
                    }
//                    newChars[maxCount - i] = '0';
                    isUpgrade = false;
                }
            }
        }
        if (isUpgrade) {
            stringBuilder.append("1");
//            newChars[0] = '1';
        }
        return stringBuilder.reverse().toString();
    }
}
