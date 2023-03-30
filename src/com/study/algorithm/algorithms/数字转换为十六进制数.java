package com.study.algorithm.algorithms;

import org.junit.Test;

public class 数字转换为十六进制数 {
    @Test
    public void 数字转换为十六进制数() {
        System.out.println("数字转换为十六进制数:" + toHex(26));
    }

    public String toHex(int num) {
        //位运算，十六进制
        //num&15(1111b),取出该数后4位数，再对该数转换成十六进制数后加入字符串中（注意需加入到首位）
        //再将num无符号右移4位，继续取出末尾的十六进制数，直至num为0为止

        if (num == 0) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();

        while (num != 0) {
            int left = num & 15;
            if (left >= 10) {
                stringBuilder.insert(0, (char) ('a' + left % 10));
            } else {
                stringBuilder.insert(0, left % 10);
            }
            num = num >>> 4;
        }
        return stringBuilder.toString();
    }
}
