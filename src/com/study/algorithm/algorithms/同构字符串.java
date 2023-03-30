package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//给定两个字符串 s 和 t，判断它们是否是同构的。
//
//如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
//
//所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
//
//示例 1:
//
//输入: s = "egg", t = "add"
//输出: true
//示例 2:
//
//输入: s = "foo", t = "bar"
//输出: false
//示例 3:
//
//输入: s = "paper", t = "title"
//输出: true
//说明:
//你可以假设 s 和 t 具有相同的长度。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/isomorphic-strings
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 同构字符串 {
    @Test
    public void 同构字符串() {
        System.out.println("同构字符串:" + isIsomorphic("aabb", "bccc"));
    }

    public boolean isIsomorphic(String s, String t) {
        //哈希表
        //需要两个字符串的每个字符一一对应
        //给定两个哈希表，分别映射s中的字符到t，再映射t中的字符到s

        Map<Character, Character> sToT = new HashMap<>();
        Map<Character, Character> tToS = new HashMap<>();

        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            Character tChar = sToT.get(ss[i]);
            Character sChar = tToS.get(ts[i]);
            if (tChar == null && sChar == null) {
                sToT.put(ss[i], ts[i]);
                tToS.put(ts[i], ss[i]);
            } else if (tChar == null || sChar == null || tChar != ts[i] || sChar != ss[i]) {
                return false;

            }
        }

        return true;
    }
}
