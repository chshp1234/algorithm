package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.LinkedList;

//给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
//
//注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
//
// 
//
//示例 1：
//
//输入：s = "bcabc"
//输出："abc"
//示例 2：
//
//输入：s = "cbacdcbc"
//输出："acdb"
// 
//
//提示：
//
//1 <= s.length <= 104
//s 由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/remove-duplicate-letters
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
@Deprecated
public class 去除重复字母 {
    @Test
    public void 去除重复字母() {
        System.out.println("去除重复字母:" + removeDuplicateLetters("efgdgaf"));
    }

    public String removeDuplicateLetters(String s) {

        LinkedList<Character> list = new LinkedList<>();
        int len = s.length();

        int bit = 0;
        LinkedList<Integer> canRemove = new LinkedList<>();

        list.add(s.charAt(0));
        int size = 0;

        for (int i = 1; i < len; i++) {
            char charAt = s.charAt(i);
            int bitCurrent = 1 << (charAt - 'a');
            if ((bit & bitCurrent) == 0) {
                if (charAt < list.get(size)) {
//                    int removeBit=
//                    canRemove.add()
                    for (int j=size-1;j>=0;j++){

                    }
                }
                bit |= bitCurrent;
                list.add(s.charAt(i));
                size++;
            } else {
//                if ()
            }
        }

        return "";
    }
}
