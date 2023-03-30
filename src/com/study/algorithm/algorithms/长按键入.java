package com.study.algorithm.algorithms;

import org.junit.Test;

public class 长按键入 {

    @Test
    public void 长按键入() {
        System.out.println("长按键入：" + isLongPressedName("xnhtq", "xhhttqq"));
    }

    // 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
    //
    // 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
    //
    //
    //
    // 示例 1：
    //
    // 输入：name = "alex", typed = "aaleex"
    // 输出：true
    // 解释：'alex' 中的 'a' 和 'e' 被长按。
    // 示例 2：
    //
    // 输入：name = "saeed", typed = "ssaaedd"
    // 输出：false
    // 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
    // 示例 3：
    //
    // 输入：name = "leelee", typed = "lleeelee"
    // 输出：true
    // 示例 4：
    //
    // 输入：name = "laiden", typed = "laiden"
    // 输出：true
    // 解释：长按名字中的字符并不是必要的。
    //
    //
    // 提示：
    //
    // name.length <= 1000
    // typed.length <= 1000
    // name 和 typed 的字符都是小写字母。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/long-pressed-name
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean isLongPressedName(String name, String typed) {

        // 分别记录当前字符串中相同的字符以及连续出现的次数
        // 若输入串中获取的字符和名字串不同，则匹配失败
        // 若输入串中获取的连续相同的字符次数小于名字串，则匹配失败

        int lenName = name.length();
        int lenTyped = typed.length();

        if (lenTyped < lenName) {
            return false;
        }
        lenName--;
        lenTyped--;

        int nameCount = 0;
        int typedCount = 0;

        char nameChar = 0;
        char typedChar = 0;

        while (lenName >= 0 || lenTyped >= 0) {

            // 获取当前匹配的字符
            if (lenName >= 0) {
                nameChar = name.charAt(lenName);
                nameCount = 1;
                lenName--;
            }

            if (lenTyped >= 0) {
                typedChar = typed.charAt(lenTyped);
                typedCount = 1;
                lenTyped--;
            }

            // 若字符和上一个字符相同，则数量+1
            while (lenName >= 0 && name.charAt(lenName) == nameChar) {
                --lenName;
                nameCount++;
            }

            while (lenTyped >= 0 && typed.charAt(lenTyped) == typedChar) {
                --lenTyped;
                typedCount++;
            }

            // 若输入串中获取的字符和名字串不同，则匹配失败
            // 若输入串中获取的连续相同的字符次数小于名字串，则匹配失败
            if (nameChar != typedChar || typedCount < nameCount) {
                return false;
            }
        }

        return true;
    }
}
