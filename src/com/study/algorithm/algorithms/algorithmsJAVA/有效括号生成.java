package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 有效括号生成 {
    @Test
    public void 括号生成() {
        generateParenthesisByDp(3).forEach(s -> System.out.println("括号生成:" + s));
    }

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     * <p>
     *
     * <p>示例：
     *
     * <p>输入：n = 3 输出：[ "((()))", "(()())", "(())()", "()(())", "()()()" ]
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/generate-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /*todo 未完成 */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();

        if (n == 0) {
            return list;
        }

        StringBuilder stringBuilder = new StringBuilder();
        comCH(n << 1, n, list, stringBuilder, false);

        return list;
    }

    public void comCH(
            int limit, int num, List<String> list, StringBuilder stringBuilder, boolean inner) {
        int start = stringBuilder.length();
        for (int i = 1; i <= num; i++) {
            stringBuilder.delete(start, stringBuilder.length());

            LCH(limit, i, list, stringBuilder);
            RCH(limit, num - i, list, stringBuilder);

            if (inner) {
                stringBuilder.append(")");
            }

            if (stringBuilder.length() == limit) {
                list.add(stringBuilder.toString());
                stringBuilder.delete(start, stringBuilder.length());
            }
        }
    }

    public void LCH(int limit, int num, List<String> list, StringBuilder stringBuilder) {
        stringBuilder.append("(");
        if (num > 1) {
            comCH(limit, num - 1, list, stringBuilder, true);
        } else {
            stringBuilder.append(")");
        }
    }

    public void RCH(int limit, int num, List<String> list, StringBuilder stringBuilder) {
        if (num != 0) {
            comCH(limit, num, list, stringBuilder, false);
        }
    }

    //todo 通过
    public List<String> generateParenthesisByDp(int n) {
        //动态规划
        //当前括号对数n，dp[n]=dp[x]+dp[y](x=1..n-1,y=n-x)
        //也就是对于括号对数n，是由左边括号对数x的结果和右边括号对数进行笛卡尔积，注意需要去重
        //那么就可以使用动态规划，计算dp[1]...dp[n]即可一层层的得出有n对括号时，总共的结果
        Set<String>[] dp = new HashSet[n];

        Set<String> set = new HashSet<>();
        set.add("()");
        dp[0] = set;

        for (int i = 2; i <= n; i++) {
            set = new HashSet<>();
            Set<String> last = dp[i - 2];
            for (String ls : last) {
                set.add("(" + ls + ")");
            }
            for (int left = 1; left < i; left++) {
                int right = i - left;
                Set<String> lSet = dp[left - 1];
                Set<String> rSet = dp[right - 1];

                for (String ls : lSet) {
                    for (String rs : rSet) {
                        set.add(ls + rs);
                    }
                }
            }
            dp[i - 1] = set;
        }

        List<String> result = new ArrayList<>();
        result.addAll(dp[n - 1]);

        return result;
    }

    List<String> result;

    public List<String> generateParenthesisByBackTrack(int n) {
        result = new ArrayList<>();
        backTrack(n, new StringBuilder());
        return result;
    }

    public void backTrack(int left, StringBuilder stringBuilder) {
        if (left == 0) {
            result.add(stringBuilder.toString());
        }

        for (int i = 1; i <= left; i++) {
            stringBuilder.insert(0, "(");
            stringBuilder.append(")");
            backTrack(left - i, stringBuilder);
        }
    }
}
