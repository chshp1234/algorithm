package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

// 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
//
// 若可行，输出任意可行的结果。若不可行，返回空字符串。
//
// 示例 1:
//
// 输入: S = "aab"
// 输出: "aba"
// 示例 2:
//
// 输入: S = "aaab"
// 输出: ""
// 注意:
//
// S 只包含小写字母并且长度在[1, 500]区间内。
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/reorganize-string
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 重构字符串 {

    @Test
    public void 重构字符串() {
        System.out.println("重构字符串:" + reorganizeString("aabca"));
    }

    public String reorganizeString(String S) {
        // 因为只包含小写字母，所以用一个辅助数组，统计每个字母出现的次数。
        // 排序，使得出现次数最多的排在最前面。
        // 判断最大的频次有没有超过数组一半，(n+1)/2，若超过则返回空串。
        // 为使字母不相邻排列，交替排列每个字母。可先填充偶数下标，再填充奇数下标，即可使得每个字母都不相邻。

        // 进阶：可以不对第二步——排序。
        // 当 n 是奇数且出现最多的字母的出现次数是 (n+1)/2时，出现次数最多的字母必须全部放置在偶数下标，否则一定会出现相邻的字母相同的情况。
        // 其余情况下，每个字母放置在偶数下标或者奇数下标都是可行的。
        // 如：vvvlc，n是奇数5，可以重构成vlvcv。vvcl，v出现的次数也是(n+1)/2，但v可以放在奇数下标cvlv，也可放在偶数下标vcvl。
        // 其余，若，最大次数小于(n+1)/2时，那么其中每个字母放在奇数和偶数都不会使得相邻。
        // 所以，可以在统计辅助数组时，找到最大次数以及其字母，判断是否需要放在偶数位即可。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/reorganize-string/solution/zhong-gou-zi-fu-chuan-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int len = S.length();
        if (len == 1) {
            return S;
        }

        // 统计数组,[1]代表字母】，[0]代表此字母出现的次数
        char[][] res = new char[26][2];
        char[] result = new char[len];

        for (int i = 0; i < 26; i++) {
            res[i][1] = (char) i;
        }

        int maxNum = 0;
        for (int i = 0; i < len; i++) {
            int cu = S.charAt(i) - 'a';
            res[cu][0]++;
            maxNum = Math.max(maxNum, res[cu][0]);
        }

        // 判断最大的频次有没有超过数组一半
        if (maxNum > ((len + 1) >> 1)) {
            return "";
        }

        Arrays.sort(res, (o1, o2) -> o2[0] - o1[0]);

        int index = 0;

        // 先填充偶数下标
        for (int i = 0; i < len; i += 2) {
            result[i] = (char) (res[index][1] + 'a');
            res[index][0]--;
            if (res[index][0] == 0) {
                index++;
            }
        }

        // 再填充奇数下标
        for (int i = 1; i < len; i += 2) {
            result[i] = (char) (res[index][1] + 'a');
            res[index][0]--;
            if (res[index][0] == 0) {
                index++;
            }
        }

        return new String(result);
    }
}
