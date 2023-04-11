package com.study.algorithm.algorithms.algorithmsJAVA;

//给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
//
//() 得 1 分。
//AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
//(A) 得 2 * A 分，其中 A 是平衡括号字符串。
// 
//
//示例 1：
//
//输入： "()"
//输出： 1
//示例 2：
//
//输入： "(())"
//输出： 2
//示例 3：
//
//输入： "()()"
//输出： 2
//示例 4：
//
//输入： "(()(()))"
//输出： 6
// 
//
//提示：
//
//S 是平衡括号字符串，且只含有 ( 和 ) 。
//2 <= S.length <= 50
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/score-of-parentheses
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 括号的分数 {
    //栈(递归)
    //用递归的形式模拟"栈"的操作
    //1. 首先遇到'('时,进行递归(入栈)计算内部括号的分数,并加上其左侧同级的括号的分数
    //2. 其次遇到')'时,返回(出栈)当前括号对的分数.
    //
    //对于')':
    //- 如果其上一个字符是'(',说明这只有一对括号,那么返回分数1
    //- 如果其上一个字符不是'(',说明这一层括号对被包含在上一层括号中,返回这一层的括号对的得分*2
    int index = 0;

    public int scoreOfParentheses(String s) {
        //当前括号对的得分
        int score = 0;
        while (index < s.length()) {
            if (s.charAt(index) == '(') {
                index++;
                //计算内部括号的分数,并加上其左侧同级的括号的分数
                score += scoreOfParentheses(s);
            } else if (s.charAt(index - 1) == '(') {
                //因题目保证字符串是有效括号对,所以遇到')'时,必有有个'('与之对应
                index++;
                //只有一个括号
                return 1;
            } else {
                index++;
                //这一层括号对被包含在上一层括号中,返回这一层的括号对的得分*2
                return 2 * score;
            }

        }
        //遍历结束,返回结果
        return score;
    }
}
