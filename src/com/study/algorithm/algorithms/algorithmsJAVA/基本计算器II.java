package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
//
//整数除法仅保留整数部分。
//
// 
//
//示例 1：
//
//输入：s = "3+2*2"
//输出：7
//示例 2：
//
//输入：s = " 3/2 "
//输出：1
//示例 3：
//
//输入：s = " 3+5 / 2 "
//输出：5
// 
//
//提示：
//
//1 <= s.length <= 3 * 105
//s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
//s 表示一个 有效表达式
//表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
//题目数据保证答案是一个 32-bit 整数
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/basic-calculator-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 基本计算器II {
    @Test
    public void 基本计算器II() {

        System.out.println("基本计算器II：" + calculate("3 + 4 + 5*3/2"));
    }

    public int calculate(String s) {

        //是否加号
        boolean add = true;
        //表达式左边值
        int left = 0;
        //表达式右边值
        int right = 0;

        int    len   = s.length();
        char[] chars = s.toCharArray();

        int num = 0;
        //当前运算符号，规定‘0’为加减号，用于直接更新数字，‘1’为乘号，‘2’为除号
        int preSign = 0;
        for (int i = 0; i < len; i++) {
            if (chars[i] == ' ') {
                continue;
            }
            if (Character.isDigit(chars[i])) {
                //如果是数字，更新当前数字
                num = num * 10 + chars[i] - '0';
                continue;
            }
            //判断当前运算符，如果为‘*’和‘/’，则这部分需要优先计算
            if (preSign == 1) {
                right = right * num;
            } else if (preSign == 2) {
                right = right / num;
            } else {
                //不是乘除符号，可直接计算，所以这里直接更新右边算式值
                right = num;
            }
            //遇到‘+’和‘-’时，说明一个数字已遍历结束，则把左边的数字和右边数字根据之前的运算符号进行运算
            //再更新运算符号为当前遍历到的运算符号
            if (chars[i] == '+') {
                if (add) {
                    left = left + right;
                } else {
                    left = left - right;
                }
                add = true;
                preSign = 0;
            } else if (chars[i] == '-') {
                if (add) {
                    left = left + right;
                } else {
                    left = left - right;
                }
                add = false;
                preSign = 0;
            }
            //遇到‘*’和‘/’时，只需更新当前运算符号即可
            else if (chars[i] == '*') {
                preSign = 1;
            } else if (chars[i] == '/') {
                preSign = 2;
            }
            num = 0;
        }
        //遍历到结尾，最后遍历的‘num’还得加入最后的计算
        if (preSign == 1) {
            right = right * num;
        } else if (preSign == 2) {
            right = right / num;
        } else {
            right = num;
        }
        return add ? left + right : left - right;
    }
}
