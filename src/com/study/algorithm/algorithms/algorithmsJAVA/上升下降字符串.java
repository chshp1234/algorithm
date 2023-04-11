package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

// 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
//
// 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
// 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
// 重复步骤 2 ，直到你没法从 s 中选择字符。
// 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
// 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
// 重复步骤 5 ，直到你没法从 s 中选择字符。
// 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
// 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
//
// 请你返回将 s 中字符重新排序后的 结果字符串 。
//
//
//
// 示例 1：
//
// 输入：s = "aaaabbbbcccc"
// 输出："abccbaabccba"
// 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
// 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
// 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
// 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
// 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
// 示例 2：
//
// 输入：s = "rat"
// 输出："art"
// 解释：单词 "rat" 在上述算法重排序以后变成 "art"
// 示例 3：
//
// 输入：s = "leetcode"
// 输出："cdelotee"
// 示例 4：
//
// 输入：s = "ggggggg"
// 输出："ggggggg"
// 示例 5：
//
// 输入：s = "spo"
// 输出："ops"
//
//
// 提示：
//
// 1 <= s.length <= 500
// s 只包含小写英文字母。
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/increasing-decreasing-string
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 上升下降字符串 {
    @Test
    public void 上升下降字符串() {
        String in = "aaaabbbbcccc";
        System.out.println("输入:" + in);
        System.out.println("输出:" + sortStringTwo(in));
    }

    public String sortString(String s) {

        // 方法一：排序
        // 排序后，按字符升序，将字符添加进列表，并记录每个字符出现的频次。
        // 之后遍历列表，从头到尾、从为到头的顺序。
        // 每次遍历一个字符时，相对应的频次-1，若频次为0，则移出该字符。
        // 直到列表没剩余字符为止。

        char[] src = s.toCharArray();
        // 字符列表，其中int[0]代表该字符，int[1]代表该字符出现的频次
        List<int[]> counter = new ArrayList<>(26);
        int len = src.length;
        StringBuilder result = new StringBuilder(len);

        Arrays.sort(src);

        int[] temp = new int[2];
        temp[0] = src[0];
        temp[1] = 1;
        counter.add(temp);
        for (int i = 1; i < len; i++) {
            if (src[i] != src[i - 1]) {
                temp = new int[2];
                temp[0] = src[i];
                temp[1] = 1;
                counter.add(temp);
            } else {
                temp[1]++;
            }
        }

        len = counter.size();
        // 双向迭代器，可同时向前和向后进行迭代
        ListIterator<int[]> iterator = counter.listIterator();
        while (len > 0) {

            // 从头到尾进行遍历（字符串升序）
            while (iterator.hasNext()) {
                temp = iterator.next();
                result.append((char) temp[0]);
                temp[1]--;
                // 若频次为0，则移除该字符
                if (temp[1] == 0) {
                    iterator.remove();
                }
            }

            // 从尾到头进行遍历（字符串降序）
            while (iterator.hasPrevious()) {
                temp = iterator.previous();
                result.append((char) temp[0]);
                temp[1]--;
                // 若频次为0，则移除该字符
                if (temp[1] == 0) {
                    iterator.remove();
                }
            }
            len = counter.size();
        }

        return result.toString();
    }

    public String sortStringTwo(String s) {
        // 桶计数
        // 注意到在构造时我们只关注字符本身，而不关注字符在原字符串中的位置。
        // 因此我们可以直接创建一个大小为 26的桶，记录每种字符的数量。每次提取最长的上升或下降字符串时，我们直接顺序或逆序遍历这个桶。
        //
        // 具体地，在遍历桶的过程中，如果当前桶的计数值不为零，那么将当前桶对应的字符加入到答案中，并将当前桶的计数值减一即可。
        // 我们重复这一过程，直到答案字符串的长度与传入的字符串的长度相等。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/increasing-decreasing-string/solution/shang-sheng-xia-jiang-zi-fu-chuan-by-leetcode-solu/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        char[] src = s.toCharArray();
        char[] counter = new char[26];
        int len = src.length;
        StringBuilder result = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            // 计算每个字符出现的次数
            counter[src[i] - 'a']++;
        }

        while (result.length() < len) {
            // 从头到尾进行遍历（字符串升序）
            for (int i = 0; i < 26; i++) {
                if (counter[i] != 0) {
                    counter[i]--;
                    result.append((char) (i + 'a'));
                }
            }

            // 从尾到头进行遍历（字符串降序）
            for (int i = 25; i >= 0; i--) {
                if (counter[i] != 0) {
                    counter[i]--;
                    result.append((char) (i + 'a'));
                }
            }
        }
        return result.toString();
    }
}
