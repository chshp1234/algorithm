package com.study.algorithm.algorithms;

//有效数字（按顺序）可以分成以下几个部分：
//
//一个 小数 或者 整数
//（可选）一个 'e' 或 'E' ，后面跟着一个 整数
//小数（按顺序）可以分成以下几个部分：
//
//（可选）一个符号字符（'+' 或 '-'）
//下述格式之一：
//至少一位数字，后面跟着一个点 '.'
//至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
//一个点 '.' ，后面跟着至少一位数字
//整数（按顺序）可以分成以下几个部分：
//
//（可选）一个符号字符（'+' 或 '-'）
//至少一位数字
//部分有效数字列举如下：
//
//["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
//部分无效数字列举如下：
//
//["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
//给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
//
// 
//
//示例 1：
//
//输入：s = "0"
//输出：true
//示例 2：
//
//输入：s = "e"
//输出：false
//示例 3：
//
//输入：s = "."
//输出：false
//示例 4：
//
//输入：s = ".1"
//输出：true
// 
//
//提示：
//
//1 <= s.length <= 20
//s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/valid-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 有效数字 {

    //确定有限状态机
    //题目不难，但步骤较多，对于状态机，我们只需要枚举出所有状态，在根据当前状态和当前输入，得出输出状态即可

    /*确定的整数*/
    public static final int TYPE_INT_DEFINITE = 0;
    /*不确定的整数*/
    public static final int TYPE_INT_INDEFINITE = 1;
    /*确定的小数*/
    public static final int TYPE_DECIMAL_DEFINITE = 2;
    /*不确定的小数*/
    public static final int TYPE_DECIMAL_INDEFINITE = 3;
    /*有符号、确定的倍数*/
    public static final int TYPE_MULTIPLE_SIGNED_DEFINITE = 4;
    /*有符号、不确定的倍数*/
    public static final int TYPE_MULTIPLE_SIGNED_INDEFINITE = 5;
    /*无符号倍数*/
    public static final int TYPE_MULTIPLE_UNSIGNED = 6;
    /*拒绝状态*/
    public static final int TYPE_INVALID = -1;

    public boolean isNumber(String s) {
        char[] chars = s.toCharArray();

        int currentType;

        //确定初始状态
        if (isNum(chars[0])) {
            currentType = TYPE_INT_DEFINITE;
        } else if (isSign(chars[0])) {
            currentType = TYPE_INT_INDEFINITE;
        } else if (isSignPoint(chars[0])) {
            currentType = TYPE_DECIMAL_INDEFINITE;
        } else {
            return false;
        }

        for (int i = 1, l = chars.length; i < l; i++) {
            currentType = action(currentType, chars[i]);
            if (currentType == TYPE_INVALID) {
                return false;
            }
        }

        //对于结束状态，我们只接受三种状态：
        //确定的整数，确定的小数，有符号、确定的倍数
        return currentType == TYPE_INT_DEFINITE || currentType == TYPE_DECIMAL_DEFINITE || currentType == TYPE_MULTIPLE_SIGNED_DEFINITE;
    }

    //状态转移
    private int action(int currentType, char c) {
        switch (currentType) {
            case TYPE_INT_INDEFINITE:
                if (isNum(c)) {
                    return TYPE_INT_DEFINITE;
                } else if (isSignPoint(c)) {
                    return TYPE_DECIMAL_INDEFINITE;
                } else {
                    return TYPE_INVALID;
                }
            case TYPE_INT_DEFINITE:
                if (isNum(c)) {
                    return TYPE_INT_DEFINITE;
                } else if (isSignPoint(c)) {
                    return TYPE_DECIMAL_DEFINITE;
                } else if (isSignE(c)) {
                    return TYPE_MULTIPLE_UNSIGNED;
                } else {
                    return TYPE_INVALID;
                }
            case TYPE_DECIMAL_INDEFINITE:
                if (isNum(c)) {
                    return TYPE_DECIMAL_DEFINITE;
                } else {
                    return TYPE_INVALID;
                }
            case TYPE_DECIMAL_DEFINITE:
                if (isNum(c)) {
                    return TYPE_DECIMAL_DEFINITE;
                } else if (isSignE(c)) {
                    return TYPE_MULTIPLE_UNSIGNED;
                } else {
                    return TYPE_INVALID;
                }
            case TYPE_MULTIPLE_UNSIGNED:
                if (isNum(c)) {
                    return TYPE_MULTIPLE_SIGNED_DEFINITE;
                } else if (isSign(c)) {
                    return TYPE_MULTIPLE_SIGNED_INDEFINITE;
                } else {
                    return TYPE_INVALID;
                }
            case TYPE_MULTIPLE_SIGNED_INDEFINITE:
            case TYPE_MULTIPLE_SIGNED_DEFINITE:
                if (isNum(c)) {
                    return TYPE_MULTIPLE_SIGNED_DEFINITE;
                } else {
                    return TYPE_INVALID;
                }
            default:
                return TYPE_INVALID;
        }
    }

    public boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    public boolean isSign(char c) {
        return c == '-' || c == '+';
    }

    public boolean isSignE(char c) {
        return c == 'e' || c == 'E';
    }

    public boolean isSignPoint(char c) {
        return c == '.';
    }
}
