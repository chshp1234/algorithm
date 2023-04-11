package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

//给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
//
//任何左括号 ( 必须有相应的右括号 )。
//任何右括号 ) 必须有相应的左括号 ( 。
//左括号 ( 必须在对应的右括号之前 )。
//* 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
//一个空字符串也被视为有效字符串。
//示例 1:
//
//输入: "()"
//输出: True
//示例 2:
//
//输入: "(*)"
//输出: True
//示例 3:
//
//输入: "(*))"
//输出: True
//注意:
//
//字符串大小将在 [1，100] 范围内。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/valid-parenthesis-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 有效的括号字符串 {
    @Test
    public void 有效的括号字符串() {

        System.out.println("有效的括号字符串" + checkValidString("((***((*"));
    }

    public boolean checkValidString(String s) {
        //栈
        //思路：一趟遍历去除所有')'。
        //1. 遇到'('时，将下标加入栈中；
        //2. 遇到')'时，利用贪心思想，优先排除'('（将栈顶弹出），其次排除'*'；
        //
        //最后如果栈为空，则true；否则，反向再遍历一遍，此时想象字符串只剩下'('和'*'，我们只需要匹配'*'和'('能否成对匹配即可。

        int len = s.length();

        int countStars = 0;
        Deque<Integer> deque = new LinkedList<>();

        //的一趟遍历，找出未匹配的'('.
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                //将其加入栈中
                deque.push(i);
            } else if (s.charAt(i) == ')') {
                if (deque.size() > 0) {
                    //优先匹配'('
                    deque.pop();
                } else if (countStars > 0) {
                    //其次考虑'*'
                    countStars--;
                } else {
                    //否则，此')'无法正常配对
                    return false;
                }
            } else {
                countStars++;
            }
        }

        //如果栈不为空
        if (!deque.isEmpty()) {
            countStars = 0;
            int brackets = deque.pop();
            for (int i = len - 1; i >= 0; i--) {

                if (i == brackets) {
                    //最右侧（栈顶）的'('和其右侧的'*'数量相比较
                    if (countStars == 0) {
                        return false;
                    }

                    if (deque.isEmpty()) {
                        //如果此时栈为空，则可全部匹配
                        return true;
                    }

                    //继续匹配下一个'('
                    brackets = deque.pop();
                    countStars--;
                } else if (s.charAt(i) == '*') {
                    //因为上一轮已经排除所有')'，所以这里只需要查找'*'即可
                    countStars++;
                }
            }
        }

        //第一轮就已遍历结束的情况
        return true;

    }
}
