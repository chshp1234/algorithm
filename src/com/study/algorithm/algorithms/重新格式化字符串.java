package com.study.algorithm.algorithms;

import java.util.ArrayList;
import java.util.List;

//给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。
//
//请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。
//
//请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。
//
// 
//
//示例 1：
//
//输入：s = "a0b1c2"
//输出："0a1b2c"
//解释："0a1b2c" 中任意两个相邻字符的类型都不同。 "a0b1c2", "0a1b2c", "0c2a1b" 也是满足题目要求的答案。
//示例 2：
//
//输入：s = "leetcode"
//输出：""
//解释："leetcode" 中只有字母，所以无法满足重新格式化的条件。
//示例 3：
//
//输入：s = "1229857369"
//输出：""
//解释："1229857369" 中只有数字，所以无法满足重新格式化的条件。
//示例 4：
//
//输入：s = "covid2019"
//输出："c2o0v1i9d"
//示例 5：
//
//输入：s = "ab123"
//输出："1a2b3"
// 
//
//提示：
//
//1 <= s.length <= 500
//s 仅由小写英文字母和/或数字组成。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/reformat-the-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 重新格式化字符串 {
    public String reformat(String s) {
        //用两个数组记录所有字母和数组
        //若字母数组和数字数组大小的差的绝对值大于1，说明没办法将字母和数字全部按交叉的方式进行格式化，返回空字符串
        //否则将较大容量的数组的字符放在左侧，较小的放右侧，按此顺序交叉排列字符，组成新的字符串
        List<Character> letter = new ArrayList<>();
        List<Character> num = new ArrayList<>();

        char[] chars = s.toCharArray();
        for (char c : chars) {
            if ('0' <= c && c <= '9') {
                num.add(c);
            } else {
                letter.add(c);
            }
        }

        if (Math.abs(num.size() - letter.size()) > 1) {
            return "";
        }

        if (letter.size() > num.size()) {
            return merge(letter, num, chars);
        } else {
            return merge(num, letter, chars);
        }
    }

    public String merge(List<Character> l, List<Character> r, char[] chars) {
        for (int i = 0; i < l.size(); i++) {
            chars[2 * i] = l.get(i);
        }

        for (int i = 0; i < r.size(); i++) {
            chars[2 * i + 1] = r.get(i);
        }

        return new String(chars);
    }
}
