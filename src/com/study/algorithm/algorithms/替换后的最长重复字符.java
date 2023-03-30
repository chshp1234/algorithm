package com.study.algorithm.algorithms;

import org.junit.Test;

//给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
//
//注意：字符串长度 和 k 不会超过 104。
//
// 
//
//示例 1：
//
//输入：s = "ABAB", k = 2
//输出：4
//解释：用两个'A'替换为两个'B',反之亦然。
//示例 2：
//
//输入：s = "AABABBA", k = 1
//输出：4
//解释：
//将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
//子串 "BBBB" 有最长重复字母, 答案为 4。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-repeating-character-replacement
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 替换后的最长重复字符 {

    @Test
    public void 替换后的最长重复字符() {
        System.out.println(
                "替换后的最长重复字符:" + characterReplacement("AABABBA", 2));
    }

    public int characterReplacement(String s, int k) {
        //我们可以枚举字符串中的每一个位置作为右端点，然后找到其最远的左端点的位置，
        //满足该区间内除了出现次数最多的那一类字符之外，剩余的字符（即非最长重复字符）数量不超过 k 个。
        //
        //这样我们可以想到使用双指针维护这些区间，每次右指针右移，如果区间仍然满足条件，那么左指针不移动，
        //否则左指针至多右移一格，保证区间长度不减小。
        //
        //虽然这样的操作会导致部分区间不符合条件，即该区间内非最长重复字符超过了 k 个。
        //但是这样的区间也同样不可能对答案产生贡献。
        //当我们右指针移动到尽头，左右指针对应的区间的长度必然对应一个长度最大的符合条件的区间。
        //
        //实际代码中，由于字符串中仅包含大写字母，我们可以使用一个长度为 26 的数组维护每一个字符的出现次数。
        //每次区间右移，我们更新右移位置的字符出现的次数，然后尝试用它更新重复字符出现次数的历史最大值，
        //最后我们使用该最大值计算出区间内非最长重复字符的数量，以此判断左指针是否需要右移即可。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/longest-repeating-character-replacement/solution/ti-huan-hou-de-zui-chang-zhong-fu-zi-fu-n6aza/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        char[] chars = s.toCharArray();
        int len = chars.length;
        int left = 0;
        int right = 0;

        int[] counter = new int[26];

        //「左边界向右移动一个位置」的时候，maxCount 或者不变，或者值减 1。
        //maxCount 的值虽然不维护，但数组 freq 的值是被正确维护的；
        //当「左边界向右移动」之前：
        //如果有两种字符长度相等，左边界向右移动不改变 maxCount 的值。
        //例如 s = [AAABBB]、k = 2，左边界 A 移除以后，窗口内字符出现次数不变，依然为 3；
        //如果左边界移除以后，使得此时 maxCount 的值变小，又由于 我们要找的只是最长替换 k 次以后重复子串的长度。
        //接下来我们继续让右边界向右移动一格，有两种情况：
        //① 右边界如果读到了刚才移出左边界的字符，恰好 maxCount 的值被正确维护；
        //② 右边界如果读到了不是刚才移出左边界的字符，新的子串要想在符合题意的条件下变得更长，
        //maxCount 一定要比之前的值还要更多，因此不会错过更优的解。
        int maxCount = 0;

        // [left, right) 内最多替换 k 个字符可以得到只有一种字符的子串
        while (right < len) {
            counter[chars[right] - 'A']++;
            // 在这里维护 maxCount，因为每一次右边界读入一个字符，字符频数增加，才会使得 maxCount 增加
            maxCount = Math.max(maxCount, counter[chars[right] - 'A']);
            right++;
            if (right - left > maxCount + k) {
                // 说明此时 k 不够用
                // 把其它不是最多出现的字符替换以后，都不能填满这个滑动的窗口，这个时候须要考虑左边界向右移动
                // 移出滑动窗口的时候，频数数组须要相应地做减法
                counter[chars[left] - 'A']--;
                left++;
            }
        }

        //依然是 我们只关心最长替换 k 次以后重复子串的长度，并且 maxCount 只会增加不会减少。
        //在退出内层 if 语句的时候，区间 [left, right) 不一定是符合要求的子串，
        //但是子串的长度一定等于题目要求的替换 k 次以后字符全都相等的最长子串
        //（maxCount 的值不会变小，所以它会一直撑着滑动窗口的长度直到 right 遍历到字符串的末尾）。
        //这一点如果很难理解的话，我们建议大家使用小测试数据、跟踪代码进行理解。
        return right - left;

    }
}
