package com.study.algorithm.algorithms;

//给你一个字符串 s ，根据下述规则反转字符串：
//
//所有非英文字母保留在原有位置。
//所有英文字母（小写或大写）位置反转。
//返回反转后的 s 。
//
// 
//
//示例 1：
//
//输入：s = "ab-cd"
//输出："dc-ba"
//示例 2：
//
//输入：s = "a-bC-dEf-ghIj"
//输出："j-Ih-gfE-dCba"
//示例 3：
//
//输入：s = "Test1ng-Leet=code-Q!"
//输出："Qedo1ct-eeLg=ntse-T!"
// 
//
//提示
//
//1 <= s.length <= 100
//s 仅由 ASCII 值在范围 [33, 122] 的字符组成
//s 不含 '\"' 或 '\\'
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reverse-only-letters
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 仅仅反转字母 {
    public String reverseOnlyLetters(String s) {
        int r = s.length() - 1;
        if (r == 0) {
            return s;
        }
        char[] chars = s.toCharArray();
        int l = 0;

        while (l < r) {
            if (!isLetter(chars[l])) {
                l++;
                continue;
            }
            if (!isLetter(chars[r])) {
                r++;
                continue;
            }
            chars[l] ^= chars[r];
            chars[r] ^= chars[l];
            chars[l] ^= chars[r];
            l++;
            r--;
        }
        return new String(chars);
    }

    public boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
