package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//给定一个字符串 s 表示一个整数嵌套列表，实现一个解析它的语法分析器并返回解析的结果 NestedInteger 。
//
//列表中的每个元素只可能是整数或整数嵌套列表
//
// 
//
//示例 1：
//
//输入：s = "324",
//输出：324
//解释：你应该返回一个 NestedInteger 对象，其中只包含整数值 324。
//示例 2：
//
//输入：s = "[123,[456,[789]]]",
//输出：[123,[456,[789]]]
//解释：返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：
//1. 一个 integer 包含值 123
//2. 一个包含两个元素的嵌套列表：
//    i.  一个 integer 包含值 456
//    ii. 一个包含一个元素的嵌套列表
//         a. 一个 integer 包含值 789
// 
//
//提示：
//
//1 <= s.length <= 5 * 104
//s 由数字、方括号 "[]"、负号 '-' 、逗号 ','组成
//用例保证 s 是可解析的 NestedInteger
//输入中的所有值的范围是 [-106, 106]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/mini-parser
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class NestedInteger {
    // Constructor initializes an empty nested list.
    public NestedInteger() {

    }

    // Constructor initializes a single integer.
    public NestedInteger(int value) {

    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger() {
        return true;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    Integer getInteger() {
        return 0;
    }

    // Set this NestedInteger to hold a single integer.
    void setInteger(int value) {

    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    void add(NestedInteger ni) {

    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    List<NestedInteger> getList() {
        return null;
    }
}

public class 迷你语法分析器 {
    public NestedInteger deserialize(String s) {
        //栈
        //字符串解析
        //遇到"["时，则表示待解析的是一个嵌套列表
        //遇到数字，说明当前待解析的是个单数字
        //遇到","或者"]"，说明一个对象解析完毕，将当前解析结果加入到上一个对象的列表中

        int num = Integer.MAX_VALUE;
        boolean isPos = false;

        if (s.charAt(0) == '-' || (s.charAt(0) >= '0' && s.charAt(0) <= '9')) {
            for (int i = 0, l = s.length(); i < l; i++) {
                char c = s.charAt(i);
                if (c == '-') {
                    isPos = true;
                } else {
                    if (num == Integer.MAX_VALUE) {
                        num = c - '0';
                    } else {
                        num = num * 10 + c - '0';
                    }
                }
            }

            return new NestedInteger(isPos ? -num : num);
        }

        Deque<NestedInteger> stack = new LinkedList<>();

        NestedInteger temp = new NestedInteger();

        for (int i = 1, l = s.length() - 1; i < l; i++) {
            char c = s.charAt(i);
            if (c == '[') {
                stack.push(temp);
                temp = new NestedInteger();
            } else if (c == ',') {
                if (num != Integer.MAX_VALUE) {
                    temp.add(new NestedInteger(isPos ? -num : num));
                    num = Integer.MAX_VALUE;
                    isPos = false;
                }
            } else if (c == '-') {
                isPos = true;
            } else if (c == ']') {
                if (num != Integer.MAX_VALUE) {
                    temp.add(new NestedInteger(isPos ? -num : num));
                    num = Integer.MAX_VALUE;
                    isPos = false;
                }
                NestedInteger last = stack.pop();
                last.add(temp);
                temp = last;
            } else {
                if (num == Integer.MAX_VALUE) {
                    num = c - '0';
                } else {
                    num = num * 10 + c - '0';
                }
            }
        }

        if (num != Integer.MAX_VALUE) {
            temp.add(new NestedInteger(isPos ? -num : num));
        }
        return temp;
    }
}
