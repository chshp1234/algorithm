package com.study.algorithm.algorithms;

//给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
//
// 
//
//示例 1:
//
//输入: num = 38
//输出: 2
//解释: 各位相加的过程为：
//38 --> 3 + 8 --> 11
//11 --> 1 + 1 --> 2
//由于 2 是一位数，所以返回 2。
//示例 1:
//
//输入: num = 0
//输出: 0
// 
//
//提示：
//
//0 <= num <= 231 - 1
// 
//
//进阶：你可以不使用循环或者递归，在 O(1) 时间复杂度内解决这个问题吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/add-digits
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 各位相加 {

    //前言
    //这道题的本质是计算自然数 num 的数根。
    //
    //数根又称数字根Digital，是自然数的一种性质，每个自然数都有一个数根。
    //对于给定的自然数，反复将各个位上的数字相加，直到结果为一位数，则该一位数即为原自然数的数根。
    //
    //计算数根的最直观的方法是模拟计算各位相加的过程，直到剩下的数字是一位数。利用自然数的性质，则能在 O(1) 的时间内计算数根。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/add-digits/solution/ge-wei-xiang-jia-by-leetcode-solution-u4kj/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int addDigits(int num) {
        //方法一：模拟
        //模拟每次每位数字相加，直到结果只剩一位数
        while (num >= 10) {
            int result = 0;
            do {
                result += num % 10;
                num = num / 10;
            } while (num > 0);
            num = result;
        }
        return num;
    }

    //public int addDigitsByLeetcode(int num) {
    //        return (num - 1) % 9 + 1;
    //    }
}
