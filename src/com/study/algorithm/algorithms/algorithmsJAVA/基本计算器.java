package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

//实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
//
// 
//
//示例 1：
//
//输入：s = "1 + 1"
//输出：2
//示例 2：
//
//输入：s = " 2-1 + 2 "
//输出：3
//示例 3：
//
//输入：s = "(1+(4+5+2)-3)+(6+8)"
//输出：23
// 
//
//提示：
//
//1 <= s.length <= 3 * 105
//s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
//s 表示一个有效的表达式
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/basic-calculator
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 基本计算器 {
    @Test
    public void 基本计算器() {

        System.out.println("基本计算器：" + calculate("- (3 + (4 + 5))"));
    }

    public int calculate(String s) {

        //数学+栈
        //遍历字符串，对字符串进行解析，使用基本的数学方法对解析后的数字进行加减运算
        //由于这里有‘()’括号存在，当有括号时，需要优先计算括号里的内容:
        //1.我们遇到'('时，使用栈保存'('前的运算结果和运算符号
        //2.遇到')'时说明一个括号内的内容已运输结束，把栈顶数字和运算符号取出，和当前括号中运算结果进行运算，并更新为当前的运算结果

        boolean plus    = true;
        int     last    = 0;
        int     current = 0;

        int    len   = s.length();
        char[] chars = s.toCharArray();

        //两个栈，分别存储左括号前的运算的数字和运算符号
        Deque<Integer> deque     = new LinkedList<>();
        Deque<Boolean> dequePlus = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            if (chars[i] == ' ') {
                continue;
            }
            //遇到‘+’和‘-’时，说明一个数字已遍历结束，则把之前的数字和当前数字根据之前的运算符号进行运算
            //再更新运算符号为当前遍历到的运算符号
            if (chars[i] == '+') {
                if (plus) {
                    last += current;
                } else {
                    last -= current;
                }
                plus = true;
                current = 0;
            } else if (chars[i] == '-') {
                if (plus) {
                    last += current;
                } else {
                    last -= current;
                }
                plus = false;
                current = 0;
            }
            //遇到‘(’时，将之前运算结果和运算符号入栈
            else if (chars[i] == '(') {
                deque.push(last);
                dequePlus.push(plus);
                last = 0;
                plus = true;
            }
            //遇到‘)’时，弹出栈顶元素，将之前运算数字根据之前运算符号和当前运算结果进行运算
            else if (chars[i] == ')') {
                if (plus) {
                    last += current;
                } else {
                    last -= current;
                }
                plus = dequePlus.pop();
                current = last;
                last = deque.pop();
            } else {
                //如果是数字，更新当前数字
                current = current * 10 + chars[i] - '0';
            }
        }

        return plus ? last + current : last - current;
    }
}
