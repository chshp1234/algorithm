package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

// 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
//
//
//
// 示例 1：
//
// 输入：s = "We are happy."
// 输出："We%20are%20happy."
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 替换空格 {
    @Test
    public void 替换空格() {

        System.out.println(
                "替换空格:"
                        + replaceSpace(
                                "  \u200B花花\u200B公子\u200B男士\u200B西服\u200B春秋\u200B季商\u200B务休\u200B闲小\u200B西装\u200B潮流\u200B上衣\u200B中年\u200B单西\u200B格子\u200B外套"));
    }

    public String replaceSpace(String s) {
        // 遍历，替换
        char[] chars = s.toCharArray();
        int len = chars.length;
        StringBuilder result = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            if (chars[i] == ' ') {
                result.append("%20");
            } else {
                result.append(chars[i]);
            }
        }

        return result.toString();
    }
}
