package com.study.algorithm.algorithms;

//复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
//
//实部 是一个整数，取值范围是 [-100, 100]
//虚部 也是一个整数，取值范围是 [-100, 100]
//i2 == -1
//给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
//
// 
//
//示例 1：
//
//输入：num1 = "1+1i", num2 = "1+1i"
//输出："0+2i"
//解释：(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
//示例 2：
//
//输入：num1 = "1+-1i", num2 = "1+-1i"
//输出："0+-2i"
//解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
// 
//
//提示：
//
//num1 和 num2 都是有效的复数表示。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/complex-number-multiplication
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 复数乘法 {
    public String complexNumberMultiply(String num1, String num2) {
        //模拟
        //模拟复数的运算即可
        int n1 = 0;
        int n1i = 0;
        int n2 = 0;
        int n2i = 0;

        int len1 = num1.length();
        int len2 = num2.length();

        int index1 = 0;
        int index2 = 0;

        StringBuilder intString = new StringBuilder();
        while (index1 < len1) {
            if (num1.charAt(index1) == '+') {
                index1++;
                break;
            }
            intString.append(num1.charAt(index1++));
        }
        n1 = Integer.parseInt(intString.toString());
        intString = new StringBuilder();
        while (index1 < len1) {
            if (num1.charAt(index1) == 'i') {
                break;
            }
            intString.append(num1.charAt(index1++));
        }
        n1i = Integer.parseInt(intString.toString());
        intString = new StringBuilder();
        while (index2 < len2) {
            if (num2.charAt(index2) == '+') {
                index2++;
                break;
            }
            intString.append(num2.charAt(index2++));
        }
        n2 = Integer.parseInt(intString.toString());
        intString = new StringBuilder();
        while (index2 < len2) {
            if (num2.charAt(index2) == 'i') {
                break;
            }
            intString.append(num2.charAt(index2++));
        }
        n2i = Integer.parseInt(intString.toString());
        intString = new StringBuilder();

        int r = n1 * n2 - n1i * n2i;
        int ri = n1 * n2i + n1i * n2;
        intString.append(r).append("+").append(ri).append("i");
        return intString.toString();
    }
}
