package com.study.algorithm.algorithmsKT

import org.junit.Test
import java.util.*

//给出一个字符串 s（仅含有小写英文字母和括号）。
//
//请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
//
//注意，您的结果中 不应 包含任何括号。
//
// 
//
//示例 1：
//
//输入：s = "(abcd)"
//输出："dcba"
//示例 2：
//
//输入：s = "(u(love)i)"
//输出："iloveu"
//示例 3：
//
//输入：s = "(ed(et(oc))el)"
//输出："leetcode"
//示例 4：
//
//输入：s = "a(bcdefghijkl(mno)p)q"
//输出："apmnolkjihgfedcbq"
// 
//
//提示：
//
//0 <= s.length <= 2000
//s 中只有小写英文字母和括号
//我们确保所有括号都是成对出现的
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 反转每对括号间的子串 {

    @Test
    fun 反转每对括号间的子串() {
        val s = "(ed(et(oc))el)"

        System.out.println("反转每对括号间的子串:$s->${reverseParentheses(s)}")
    }

    fun reverseParentheses(s: String): String {
        //栈
        //遇到‘(’时，入栈；遇到‘)’时出栈
        //并逐层翻转括号间的字符串
        val len = s.length
        val result = StringBuilder()
        val stack = LinkedList<String>()

        for (i in 0 until len) {
            if (s[i] == '(') {
                stack.push(s[i].toString())
            } else if (s[i] == ')') {
                val temp = StringBuilder()
                while (!stack.isEmpty()) {
                    val current = stack.pop()
                    if (current != '('.toString()) {
                        temp.append(current)
                    } else {
                        break
                    }
                }
                if (!stack.isEmpty()) {
                    stack.push(temp.reverse().toString())
                } else {
                    result.append(temp)
                }

            } else if (stack.isEmpty()) {
                result.append(s[i])
            } else {
                stack.push(s[i].toString())
            }
        }
        return result.toString()
    }
}