package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

public class 有效括号的嵌套深度 {
    @Test
    public void 有效括号的嵌套深度() {
        String seq = "()(())()";
        System.out.println("有效括号的嵌套深度：" + Arrays.toString(maxDepthAfterSplit(seq)));
    }
    /**
     * 有效括号字符串 仅由 "(" 和 ")" 构成，并符合下述几个条件之一：
     *
     * <p>空字符串 连接，可以记作 AB（A 与 B 连接），其中 A 和 B 都是有效括号字符串 嵌套，可以记作 (A)，其中 A 是有效括号字符串 类似地，我们可以定义任意有效括号字符串
     * s 的 嵌套深度 depth(S)：
     *
     * <p>s 为空时，depth("") = 0 s 为 A 与 B 连接时，depth(A + B) = max(depth(A), depth(B))，其中 A
     * 和 B 都是有效括号字符串 s 为嵌套情况，depth("(" + A + ")") = 1 + depth(A)，其中 A 是有效括号字符串
     * 例如：""，"()()"，和 "()(()())" 都是有效括号字符串，嵌套深度分别为 0，1，2，而 ")(" 和 "(()" 都不是有效括号字符串。
     *
     * <p>
     *
     * <p>给你一个有效括号字符串 seq，将其分成两个不相交的子序列 A 和 B，且 A 和 B 满足有效括号字符串的定义（注意：A.length + B.length =
     * seq.length）。
     *
     * <p>现在，你需要从中选出 任意 一组有效括号字符串 A 和 B，使 max(depth(A), depth(B)) 的可能取值最小。
     *
     * <p>返回长度为 seq.length 答案数组 answer ，选择 A 还是 B 的编码规则是：如果 seq[i] 是 A 的一部分，那么 answer[i] =
     * 0。否则，answer[i] = 1。即便有多个满足要求的答案存在，你也只需返回 一个。
     *
     * <p>来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] maxDepthAfterSplit(String seq) {
        int len = seq.length();
        int depth = calculateDepth(seq, len);
        if (depth == 0) {
            return new int[0];
        } else if (depth == 1) {
            return new int[len];
        } else {
            return maxDepthAfterSplit(seq, len, depth >> 1);
        }
    }
    /*public int[] maxDepthAfterSplit(String seq) {
        int[] ans = new int [seq.length()];
        int idx = 0;
        for(char c: seq.toCharArray()) {
            ans[idx++] = c == '(' ? idx & 1 : ((idx + 1) & 1);
        }
        return ans;
    }*/
    /** 计算有效括号字符串深度 */
    public int calculateDepth(String seq, int len) {
        if (seq == null || len <= 1) {
            return 0;
        }
        int leftCount = 0;
        int maxCount = 0;
        int currentCount = 0;

        for (int i = 0; i < len; i++) {
            if (seq.charAt(i) == '(') {
                currentCount++;
                leftCount++;
            } else {
                currentCount--;
            }
            maxCount = maxCount > currentCount ? maxCount : currentCount;
            if (leftCount == len >> 1) {
                break;
            }
        }

        return maxCount;
    }
    /** 分割有效括号字符串 */
    public int[] maxDepthAfterSplit(String seq, int len, int depth) {
        int ACount = 0;
        int BCount = 0;
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            if (seq.charAt(i) == '(') {
                if (ACount < depth) {
                    ACount++;
                    //                    result[i] = 0;
                } else {
                    BCount++;
                    result[i] = 1;
                }
            } else {
                if (BCount == 0) {
                    ACount--;
                    //                    result[i] = 0;
                } else {
                    BCount--;
                    result[i] = 1;
                }
            }
        }

        return result;
    }
}
