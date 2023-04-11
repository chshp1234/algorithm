package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class 有效的括号 {

    @Test
    public void 有效的括号() {
        String seq = "([)]";
        System.out.println("有效的括号：" + isValid(seq));
    }

    // 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
    //
    // 有效字符串需满足：
    //
    // 左括号必须用相同类型的右括号闭合。
    // 左括号必须以正确的顺序闭合。
    // 注意空字符串可被认为是有效字符串。
    //
    // 示例 1:
    //
    // 输入: "()"
    // 输出: true
    // 示例 2:
    //
    // 输入: "()[]{}"
    // 输出: true
    // 示例 3:
    //
    // 输入: "(]"
    // 输出: false
    // 示例 4:
    //
    // 输入: "([)]"
    // 输出: false
    // 示例 5:
    //
    // 输入: "{[]}"
    // 输出: true
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/valid-parentheses
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean isValid(String s) {
        if (s.length() == 0) {
            return true;
        }

        // 经典栈题：当遇到左括号"("、"["、"{"时入栈，当遇到右括号时出栈，并把右括号和左括号进行匹配，看是否是一对有效的括号即可。
        // 看一对括号是否匹配，可用map做映射，当然此题可用取巧的办法，因为右括号比左括号大，)比(大1，}比{大2，]比[大2，且此3对括号ascill码相差较大
        // 固可判断匹配的右括号是否比左括号大1或者大2来判断次括号对是否有效

        Deque<Character> deque = new LinkedList<>();

        deque.push(s.charAt(0));

        for (int i = 1, l = s.length(); i < l; i++) {
            char c = s.charAt(i);

            // 遇到左括号就入栈
            if (c == '(' || c == '[' || c == '{') {
                deque.push(c);
            }
            // 如果遇到右括号，但此时栈中为空（没有左括号与之配对），则返回false
            else if (deque.isEmpty()) {
                return false;
            }
            // 判断此对括号是否有效
            else {
                Character pop = deque.pop();
                if (pop != c - 1 && pop != c - 2) {
                    return false;
                }
            }
        }

        // 如果遍历结束判断栈中是否还有数据（如果栈中还有左括号，则返回false）
        return deque.isEmpty();
    }
}
