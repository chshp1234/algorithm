package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class 翻转字符串里的单词 {
    @Test
    public void 翻转字符串里的单词() {
        System.out.println("翻转字符串里的单词:" + reverseWord("a good   example"));
    }

    /**
     * 给定一个字符串，逐个翻转字符串中的每个单词。
     *
     * <p>
     *
     * <p>示例 1：
     *
     * <p>输入: "the sky is blue" 输出: "blue is sky the" 示例 2：
     *
     * <p>输入: "  hello world!  " 输出: "world! hello" 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 示例 3：
     *
     * <p>输入: "a good   example" 输出: "example good a" 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *
     * <p>说明：
     *
     * <p>无空格字符构成一个单词。 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *
     * <p>进阶：
     *
     * <p>请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public String reverseWords(String s) {
        //方法一：利用自带接口，按空格分隔字符串成不同单词，最后反向拼接
        //方法二：1.收尾双指针，反向重新排列字符串；2.双指针翻转字符串中每个单词，遇到空格开始翻转下一个单词
        StringBuilder stringBuilder = new StringBuilder();
        List<String>  list          = Arrays.asList(s.split(" "));
        Collections.reverse(list);
        list.forEach(
                new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        s = s.trim();
                        if (s.length() != 0) {
                            stringBuilder.append(s).append(" ");
                        }
                    }
                });
        return stringBuilder.toString().trim();

        /*s = s.trim();
        List<String> list = Arrays.asList(s.split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);*/
    }

    public String reverseWord(String s) {
        //双指针
        //先用高低指针，将整个字符串翻转
        //再遍历字符串，遇到空格时，反向遍历加入字符
        //最后还需对最后一个单词进行遍历
        char[] chars = s.trim().toCharArray();
        int    len   = chars.length;
        int    l     = 0;
        int    r     = len - 1;
        while (l < r) {
            chars[l] ^= chars[r];
            chars[r] ^= chars[l];
            chars[l] ^= chars[r];
            l++;
            r--;
        }
        StringBuilder result = new StringBuilder();

        /*r = len - 1;
        boolean lastSpace = false;
        while (r >= 0) {
            if (chars[r] == ' ') {
                if (!lastSpace) {
                    result.append(chars[r]);
                }
                lastSpace = true;
            } else {
                lastSpace = false;
                result.append(chars[r]);
            }
            r--;
        }*/

        l = r = 0;
        while (r < len) {
            if (chars[r] == ' ') {
                //遇到空格时，反向遍历加入字符，即可得到正向的单词
                for (int i = r - 1; i >= l; i--) {
                    result.append(chars[i]);
                }
                result.append(' ');
                r++;
                while (chars[r] == ' ') {
                    r++;
                }
                l = r;
            }
            r++;

        }

        //对最后一个单词进行遍历
        for (int i = r - 1; i >= l; i--) {
            result.append(chars[i]);
        }

        return result.toString();
    }
}
