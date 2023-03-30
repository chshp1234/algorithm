package com.study.algorithm.algorithms;

import org.junit.Test;

public class 验证回文串 {

    @Test
    public void 验证回文串() {
        System.out.println("验证回文串:" + isPalindrome("Marge, let's \"[went].\" I await {news} telegram."));
    }

    @Test
    public void 验证回文串二() {
        System.out.println("680验证回文串二:" + validPalindrome("ebcbbececabbacecbbcbe"));
    }

    // 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
    //
    // 说明：本题中，我们将空字符串定义为有效的回文串。
    //
    // 示例 1:
    //
    // 输入: "A man, a plan, a canal: Panama"
    // 输出: true
    // 示例 2:
    //
    // 输入: "race a car"
    // 输出: false
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/valid-palindrome
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean isPalindrome(String s) {

        char[] chars = s.toCharArray();

        // 双指针（高低位）依次判断
        int low = 0, high = chars.length - 1;

        while (low < high) {
            // 除了字母和数字以外
            if (chars[low] < 48
                    || (chars[low] > 57 && chars[low] < 65)
                    || (chars[low] > 90 && chars[low] < 97)
                    || chars[low] > 122) {
                ++low;
                continue;
            }
            // 除了字母和数字以外
            if (chars[high] < 48
                    || (chars[high] > 57 && chars[high] < 65)
                    || (chars[high] > 90 && chars[high] < 97)
                    || chars[high] > 122) {
                --high;
                continue;
            }
            // 如果首尾字母相等（忽略大小写）
            if (chars[low] == chars[high]
                    || (chars[low] >= 97 && (chars[low] - 32) == chars[high])
                    || (chars[high] >= 97 && (chars[high] - 32) == chars[low])) {
                ++low;
                --high;
            } else {
                return false;
            }
        }

        return true;
    }

    // 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
    //
    // 示例 1:
    //
    // 输入: "aba"
    // 输出: True
    // 示例 2:
    //
    // 输入: "abca"
    // 输出: True
    // 解释: 你可以删除c字符。
    // 注意:
    //
    // 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/valid-palindrome-ii
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean validPalindrome(String s) {
        char[] chars = s.toCharArray();

        // 高低双指针
        int[] pointer = {0, chars.length - 1};

        // 遇到当前高低字符不等
        // 此时可以判断若删除低位字符，剩余字符串是否回文串；或者删除高位字符后，剩余字符串是否为回文串
        if (!validPalindrome(chars, pointer)) {

            // 删除低位字符（低位指针+1）
            int low = pointer[0], high = pointer[1];

            pointer[0] = low + 1;

            if (!validPalindrome(chars, pointer)) {
                // 删除高位字符（高位指针-1）
                pointer[0] = low;
                pointer[1] = high - 1;

                return validPalindrome(chars, pointer);
            }
        }

        return true;
    }

    private boolean validPalindrome(char[] chars, int[] pointer) {

        // 高低指针判断是否回文串
        while (pointer[0] < pointer[1]) {

            if (chars[pointer[0]] == chars[pointer[1]]) {
                ++pointer[0];
                --pointer[1];
            } else {
                return false;
            }
        }
        return true;
    }
}
