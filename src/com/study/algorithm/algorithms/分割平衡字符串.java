package com.study.algorithm.algorithms;

import org.junit.Test;

//在一个 平衡字符串 中，'L' 和 'R' 字符的数量是相同的。
//
//给你一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
//
//注意：分割得到的每个字符串都必须是平衡字符串。
//
//返回可以通过分割得到的平衡字符串的 最大数量 。
//
// 
//
//示例 1：
//
//输入：s = "RLRRLLRLRL"
//输出：4
//解释：s 可以分割为 "RL"、"RRLL"、"RL"、"RL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
//示例 2：
//
//输入：s = "RLLLLRRRLR"
//输出：3
//解释：s 可以分割为 "RL"、"LLLRRR"、"LR" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
//示例 3：
//
//输入：s = "LLLLRRRR"
//输出：1
//解释：s 只能保持原样 "LLLLRRRR".
//示例 4：
//
//输入：s = "RLRRRLLRLL"
//输出：2
//解释：s 可以分割为 "RL"、"RRRLLRLL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
// 
//
//提示：
//
//1 <= s.length <= 1000
//s[i] = 'L' 或 'R'
//s 是一个 平衡 字符串
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/split-a-string-in-balanced-strings
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 分割平衡字符串 {

    public int balancedStringSplit(String s) {
        //贪心

        //原串是平衡字符串，则字符数量肯定是偶数，若要每个子串都是平衡字符串，那么每个字符都是偶数，
        //且每个子串中的L和R的元素数量相同，那么一个L将对应一个R。
        //我们可以将L视作-1，R视作+1，那么一个平衡字符串中元素总和将是0.
        //设一个计数counter，遍历的过程中，遇到L时counter-1，遇到R时，counter+1，当counter计算到0时，说明此子串是一个平衡字符串，那么平衡字符串的数量将+1.

        char[] chars = s.toCharArray();

        int counter = 0;

        int result = 0;

        for (char c : chars) {
            if (c == 'L') {
                counter--;
            } else {
                counter++;
            }

            if (counter == 0) {
                result++;
            }
        }

        return result;
    }
}
