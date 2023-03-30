package com.study.algorithm.algorithms;

import java.util.HashSet;
import java.util.Set;

//给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
//
//元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
//
// 
//
//示例 1：
//
//输入：s = "hello"
//输出："holle"
//示例 2：
//
//输入：s = "leetcode"
//输出："leotcede"
// 
//
//提示：
//
//1 <= s.length <= 3 * 105
//s 由 可打印的 ASCII 字符组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reverse-vowels-of-a-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 反转字符串中的元音字母 {
    public String reverseVowels(String s) {
        //双指针，哈希表
        //既然要反正首尾，那么肯定使用双指针进行遍历，两个指针都遍历到元音字母时，交换俩字母
        //另：用一个哈希表记录所有元音字母
        char[] chars = s.toCharArray();

        Set<Character> vowelChar = new HashSet<>();
        vowelChar.add('a');
        vowelChar.add('e');
        vowelChar.add('i');
        vowelChar.add('o');
        vowelChar.add('u');
        vowelChar.add('A');
        vowelChar.add('E');
        vowelChar.add('I');
        vowelChar.add('O');
        vowelChar.add('U');

        int l = 0, r = chars.length - 1;

        while (l < r) {
            if (vowelChar.contains(chars[l])) {
                break;
            }
            l++;
        }

        while (l < r) {
            if (vowelChar.contains(chars[r])) {
                break;
            }
            r--;
        }


        while (l < r) {
            chars[l] ^= chars[r];
            chars[r] ^= chars[l];
            chars[l++] ^= chars[r--];
            while (l < r) {
                if (vowelChar.contains(chars[l])) {
                    break;
                }
                l++;
            }

            while (l < r) {
                if (vowelChar.contains(chars[r])) {
                    break;
                }
                r--;
            }
        }
        return new String(chars);
    }


}
