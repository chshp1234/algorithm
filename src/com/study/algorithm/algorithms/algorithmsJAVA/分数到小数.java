package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
//
//如果小数部分为循环小数，则将循环的部分括在括号内。
//
//如果存在多个答案，只需返回 任意一个 。
//
//对于所有给定的输入，保证 答案字符串的长度小于 104 。
//
// 
//
//示例 1：
//
//输入：numerator = 1, denominator = 2
//输出："0.5"
//示例 2：
//
//输入：numerator = 2, denominator = 1
//输出："2"
//示例 3：
//
//输入：numerator = 2, denominator = 3
//输出："0.(6)"
//示例 4：
//
//输入：numerator = 4, denominator = 333
//输出："0.(012)"
//示例 5：
//
//输入：numerator = 1, denominator = 5
//输出："0.2"
// 
//
//提示：
//
//-231 <= numerator, denominator <= 231 - 1
//denominator != 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/fraction-to-recurring-decimal
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 分数到小数 {
    @Test
    public void 分数到小数() {
        System.out.println("分数到小数:" + fractionToDecimal(-1, -2147483648));
    }

    public String fractionToDecimal(int num, int den) {

        //长除法
        //1.首先，判断符号，后将两数都置为正数
        //2.计算整数部分，只有当分子大于等于分母时，才能计算整数部分
        //3.计算小数部分。将当前分子numerator以及对应计算的小数位数index加入哈希表中；
        // 用长除法，先将分子numerator=numerator*10，
        // 再numerator / denominator加入分子字符串中，
        // 最后另分子numerator=numerator % denominator
        //循环次步骤，直到分子为0，或者分子存在哈希表中为止。
        //若分子存在哈希表中，取出对应的小数位数，在此处加入"("，并在结尾加入")"。因为从这个小数位数开始，将进入了重复计算，并无限循环下去。

        if (num == 0) {
            return "0";
        }

        StringBuilder number = new StringBuilder();

        long numerator = num;
        long denominator = den;

        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
            number.append("-");
            numerator = Math.abs(numerator);
            denominator = Math.abs(denominator);
        } else if (numerator < 0) {
            numerator = Math.abs(numerator);
            denominator = Math.abs(denominator);
        }
        if (numerator < denominator) {
            number.append("0");
        } else {

            do {
                number.append(numerator / denominator);
                numerator = numerator % denominator;
            }
            while (numerator >= denominator);
        }
        if (numerator == 0) {
            return number.toString();
        }

        number.append(".");
        Map<Long, Integer> set = new HashMap<>();

        StringBuilder decimal = new StringBuilder();
        int index = 0;
        do {
            set.put(numerator, index++);
            numerator = numerator * 10;
            decimal.append(numerator / denominator);
            numerator = numerator % denominator;
            if (set.containsKey(numerator)) {
                decimal.insert(set.get(numerator), "(").append(")");
                return number.append(decimal).toString();
            }
        } while (numerator != 0);
        return number.append(decimal).toString();
    }
}
