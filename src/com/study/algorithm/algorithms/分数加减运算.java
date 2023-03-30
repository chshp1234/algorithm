package com.study.algorithm.algorithms;

//给定一个表示分数加减运算的字符串 expression ，你需要返回一个字符串形式的计算结果。 
//
//这个结果应该是不可约分的分数，即最简分数。 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为 2/1。
//
// 
//
//示例 1:
//
//输入: expression = "-1/2+1/2"
//输出: "0/1"
// 示例 2:
//
//输入: expression = "-1/2+1/2+1/3"
//输出: "1/3"
//示例 3:
//
//输入: expression = "1/3-1/2"
//输出: "-1/6"
// 
//
//提示:
//
//输入和输出字符串只包含 '0' 到 '9' 的数字，以及 '/', '+' 和 '-'。 
//输入和输出分数格式均为 ±分子/分母。如果输入的第一个分数或者输出的分数是正数，则 '+' 会被省略掉。
//输入只包含合法的最简分数，每个分数的分子与分母的范围是  [1,10]。 如果分母是1，意味着这个分数实际上是一个整数。
//输入的分数个数范围是 [1,10]。
//最终结果的分子与分母保证是 32 位整数范围内的有效整数。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/fraction-addition-and-subtraction
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import org.junit.Test;

public class 分数加减运算 {
    @Test
    public void 分数加减运算() {
        System.out.println("分数加减运算:" + fractionAddition("1/3-1/2"));
    }

    public static String fractionAddition(String expression) {
        //字符串遍历
        //带分子分母的计算，需要先求出最大公约数gcd，再将分母替换成最小公倍数后，分子就为分子乘上倍数
        //用辗转相除法求最大公约数
        //用一个int值记录当前遍历的数字，若下一个字符还是数字，那么int=int*10+char

        int buildNum = 0;
        long numeratorL = 0;
        long denominatorL = 1;
        long numeratorR = 1;
        long denominatorR = 1;
        char sign;
        if (expression.charAt(0) == '-') {
            sign = '-';
        } else {
            sign = '+';
        }

        int index = 0;
        if (expression.charAt(0) == '-' || expression.charAt(0) == '+') {
            index = 1;
        }

        while (index < expression.length()) {
            char c = expression.charAt(index);
            if (c == '-' || c == '+') {

                denominatorR = buildNum;
                buildNum = 0;

                long minCommonDivisor = commonDivisor(denominatorL, denominatorR);
                if (sign == '-') {
                    numeratorL =
                            ((denominatorR / minCommonDivisor) * numeratorL) -
                                    ((denominatorL / minCommonDivisor) * numeratorR);
                } else {
                    numeratorL =
                            ((denominatorR / minCommonDivisor) * numeratorL) +
                                    ((denominatorL / minCommonDivisor) * numeratorR);
                }
                denominatorL = (denominatorR / minCommonDivisor) * denominatorL;

                sign = c;
            } else if (c == '/') {
                numeratorR = buildNum;
                buildNum = 0;
            } else {
                buildNum = buildNum * 10 + c - '0';
            }
            index++;
        }

        denominatorR = buildNum;
        long minCommonDivisor = commonDivisor(denominatorL, denominatorR);
        if (sign == '-') {
            numeratorL =
                    ((denominatorR / minCommonDivisor) * numeratorL) -
                            ((denominatorL / minCommonDivisor) * numeratorR);
        } else {
            numeratorL =
                    ((denominatorR / minCommonDivisor) * numeratorL) +
                            ((denominatorL / minCommonDivisor) * numeratorR);
        }
        denominatorL = (denominatorR / minCommonDivisor) * denominatorL;
        if (numeratorL == 0) {
            return "0/1";
        }

        minCommonDivisor = commonDivisor(Math.abs(numeratorL), denominatorL);
        return numeratorL / minCommonDivisor + "/" + denominatorL / minCommonDivisor;
    }

    public static long commonDivisor(long a, long b) {
        if (a < b) {
            return commonDivisor(b, a);
        }
        long c = a % b;//使用辗转相除法
        while (c != 0) {
            a = b;
            b = c;
            c = a % b;
        }
        return b;
    }
}
