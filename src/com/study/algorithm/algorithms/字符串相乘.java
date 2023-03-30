package com.study.algorithm.algorithms;

import org.junit.Test;

public class 字符串相乘 {

    @Test
    public void 字符串相乘() {
        System.out.println("字符串相乘:" + multiply("123", "456"));
    }

    // 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
    //
    // 示例 1:
    //
    // 输入: num1 = "2", num2 = "3"
    // 输出: "6"
    // 示例 2:
    //
    // 输入: num1 = "123", num2 = "456"
    // 输出: "56088"
    // 说明：
    //
    // num1 和 num2 的长度小于110。
    // num1 和 num2 只包含数字 0-9。
    // num1 和 num2 均不以零开头，除非是数字 0 本身。
    // 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/multiply-strings
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public String multiply(String num1, String num2) {
        if (num1.length() > num2.length()) {
            return multiply(num2, num1);
        }

        int len1 = num1.length();
        int len2 = num2.length();

        char[] chars = new char[len1 + len2];

        //        Arrays.fill(chars, (char) 48);

        int carry = 0;

        int left, right, current;

        for (int i = 0; i < len1; i++) {
            left = num1.charAt(len1 - i - 1) - 48;

            for (int j = 0; j < len2; j++) {
                right = num2.charAt(len2 - j - 1) - 48;

                current = left * right + carry + chars[i + j];

                chars[i + j] = (char) (current % 10);
                carry = current / 10;
            }
            chars[i + len2] = (char) (carry);
            carry = 0;
        }

        int count = reverseAndParse(chars);

        /*StringBuilder stringBuilder = new StringBuilder(new String(chars)).reverse();

        while (stringBuilder.charAt(0) == '0' && stringBuilder.length() > 1) {
            stringBuilder.deleteCharAt(0);
        }*/

        return new String(chars, 0, count);
    }

    // 处理尾部'0'字符，并且翻转字符数组，并且每个字符+48转换成ascill码
    private int reverseAndParse(char[] chars) {

        int result = chars.length;

        int low = 0, high = result - 1;

        while (chars[high] == 0 && result > 1) {
            high--;
            result--;
        }

        while (low < high) {

            chars[low] ^= chars[high];
            chars[high] ^= chars[low];
            chars[low] ^= chars[high];

            chars[low] += 48;
            chars[high] += 48;

            low++;
            high--;
        }

        if (low == high) {
            chars[low] += 48;
        }
        return result;
    }
}
