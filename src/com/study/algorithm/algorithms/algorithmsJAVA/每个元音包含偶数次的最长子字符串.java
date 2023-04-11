package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 每个元音包含偶数次的最长子字符串 {

    @Test
    public void 每个元音包含偶数次的最长子字符串() {
        // 求 1+2+...+n
        System.out.println("每个元音包含偶数次的最长子字符串:" + findTheLongestSubstring("id"));
    }

    // 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
    //
    //
    //
    // 示例 1：
    //
    // 输入：s = "eleetminicoworoep"
    // 输出：13
    // 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
    // 示例 2：
    //
    // 输入：s = "leetcodeisgreat"
    // 输出：5
    // 解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。
    // 示例 3：
    //
    // 输入：s = "bcbcbc"
    // 输出：6
    // 解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u 都出现了 0 次。
    //
    //
    // 提示：
    //
    // 1 <= s.length <= 5 x 10^5
    // s 只包含小写英文字母。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/find-the-longest-substring-containing-vowels-in-even-counts
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int findTheLongestSubstring(String s) {
        /*Map<Character, Integer> map = new HashMap<>();
        map.put('a', 0x1);
        map.put('e', 0x2);
        map.put('i', 0x4);
        map.put('o', 0x8);
        map.put('u', 0x10);*/

        // tip1:Represent the counts (odd or even) of vowels with a bitmask.(使用二进制数记录元音的计数)
        // tip2:Precompute the prefix xor for the bitmask of vowels and then get the longest valid
        // substring
        // 计算字符的前缀异或值(int[] prefix_xor)，
        // 若字符串位置n~m处的异或结果(prefix_xor[m] ^ prefix_xor[n - 1])为零则代表此区间子串符合要求

        int len = s.length();
        int[] prefix_xor = new int[len + 1];

        int temp;
        for (int i = 1; i <= len; i++) {
            if (s.charAt(i - 1) == 'a') {
                temp = 0x1;
            } else if (s.charAt(i - 1) == 'e') {
                temp = 0x2;
            } else if (s.charAt(i - 1) == 'i') {
                temp = 0x4;
            } else if (s.charAt(i - 1) == 'o') {
                temp = 0x8;
            } else if (s.charAt(i - 1) == 'u') {
                temp = 0x10;
            } else {
                temp = 0;
            }
            prefix_xor[i] = prefix_xor[i - 1] ^ temp;
        }

        int max = 0;

        // 从两头开始计算当前区间的异或结果（为零则更新最大值）
        for (int i = 1; i <= len; i++) {
            for (int k = len; k > 0; k--) {
                if ((prefix_xor[k] ^ prefix_xor[i - 1]) == 0) {
                    temp = k - i + 1;

                    //提前跳出
                    if (max >= temp) {
                        break;
                    }

                    max = Math.max(temp, max);
                    break;
                }
            }

            //提前跳出
            if (max >= (len - i)) {
                return max;
            }
        }
        return max;
    }
}
