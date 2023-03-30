package com.study.algorithm.algorithms;

import org.junit.Test;

//字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
//
// 
//
//示例 1:
//
//输入:
//first = "pale"
//second = "ple"
//输出: True
// 
//
//示例 2:
//
//输入:
//first = "pales"
//second = "pal"
//输出: False
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/one-away-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 一次编辑 {
    @Test
    public void 一次编辑() {
        System.out.println(
                "一次编辑:" + oneEditAway("pale",
                        "ple"));
    }

    public boolean oneEditAway(String first, String second) {
        //如果两个字符串长度相差大于1，则返回false
        //以较短的字符串下标开始遍历，如果遇到不同的字符，则说明需要增加一个，长串的下标+1
        //并记录改变次数+1，若改变次数>1，返回false
        int lenF = first.length();
        int lenS = second.length();
        if (Math.abs(lenF - lenS) > 1) {
            return false;
        }

        int indexF = 0;
        int indexS = 0;
        if (lenF > lenS) {
            int count = 0;
            while (indexS < lenS) {
                if (first.charAt(indexF) != second.charAt(indexS)) {
                    if (count == 1) {
                        return false;
                    }
                    count++;
                } else {
                    indexS++;
                }
                indexF++;
            }
        } else if (lenS > lenF) {
            int count = 0;
            while (indexF < lenF) {
                if (first.charAt(indexF) != second.charAt(indexS)) {
                    if (count == 1) {
                        return false;
                    }
                    count++;
                } else {
                    indexF++;
                }
                indexS++;
            }
        } else {
            int count = 0;
            while (indexF < lenF) {
                if (first.charAt(indexF) != second.charAt(indexS)) {
                    if (count == 1) {
                        return false;
                    }
                    count++;
                }
                indexF++;
                indexS++;
            }
        }

        return true;
    }
}
