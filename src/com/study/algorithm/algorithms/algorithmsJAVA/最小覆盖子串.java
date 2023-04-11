package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

public class 最小覆盖子串 {

    @Test
    public void 最大子序和() {

        System.out.println("最大子序和：" + minWindow("aa", "aa"));
    }

    // 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
    //
    // 示例：
    //
    // 输入: S = "ADOBECODEBANC", T = "ABC"
    // 输出: "BANC"
    // 说明：
    //
    // 如果 S 中不存这样的子串，则返回空字符串 ""。
    // 如果 S 中存在这样的子串，我们保证它是唯一的答案。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/minimum-window-substring
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public String minWindow(String s, String t) {

        /*todo 未完成t中有重复时无法进行*/

        int index = 0;
        int slen = s.length();
        int tlen = t.length();

        if (tlen > slen) {
            return "";
        }

        int low, high;

        LinkedHashMap<Character, Integer> saves = new LinkedHashMap<>(tlen);

        Queue<Character> queue = new LinkedList<>();

        Character character;
        for (; index < slen; index++) {
            character = s.charAt(index);
            if (t.contains(String.valueOf(character))) {

                if (queue.contains(character)) {
                    queue.remove();
                }
                queue.offer(character);
                saves.put(character, index);

                if (saves.size() == tlen) {
                    break;
                }
            }
        }

        Character headChar = queue.poll();

        if (headChar == null) {
            return "";
        }

        low = saves.get(headChar);
        high = index;

        ++index;

        int min = high - low + 1;

        for (; index < slen; index++) {
            character = s.charAt(index);
            if (headChar == character) {

                headChar = queue.poll();

                if (index - saves.get(headChar) + 1 < min) {
                    low = saves.get(headChar);
                    high = index;
                }

            } else if (t.contains(String.valueOf(character))) {
                queue.remove(character);
                queue.offer(character);
                saves.put(character, index);
            }
        }

        return s.substring(low, high + 1);
    }
}
